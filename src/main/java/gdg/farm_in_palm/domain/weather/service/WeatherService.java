package gdg.farm_in_palm.domain.weather.service;

import gdg.farm_in_palm.domain.weather.Weather;
import gdg.farm_in_palm.domain.weather.dto.WeatherInfoResDTO;
import gdg.farm_in_palm.domain.weather.repository.WeatherRepository;
import gdg.farm_in_palm.domain.common.util.Iso8601Formatter;
import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeatherService {

    private final WeatherRepository weatherRepository;

    // Weather 조회
    public WeatherInfoResDTO getWeatherById(Long weatherId) {
        Weather weather = weatherRepository.findById(weatherId)
                .orElseThrow(() -> new CustomException(ErrorCode.WEATHER_NOT_FOUND));

        return WeatherInfoResDTO.builder()
                .weatherId(weather.getId())
                .weatherDate(weather.getWeatherDate())
                .temperature(weather.getTemperature())
                .humidity(weather.getHumidity())
                .groundTemperature(weather.getGroundTemperature())
                .groundHumidity(weather.getGroundHumidity())
                .rainGauge(weather.getRainGauge())
                .rainAmount(weather.getRainAmount())
                .build();
    }


    @Transactional
    public WeatherInfoResDTO getWeather() throws IOException, JSONException {

        String nx = "89"; //복현동 x값
        String ny = "91"; //복현동 y값

        String current = Iso8601Formatter.getIso8601String();
        // 날짜 부분 추출 (yyyyMMdd)
        String baseDate = current.substring(0, 4)         // yyyy
                + current.substring(5, 7)       // MM
                + current.substring(8, 10);     // dd

        // 시간 부분 추출 (HHmm)
        String baseTime = current.substring(11, 13)       // HH
                + current.substring(14, 16);    // mm

        String type = "json"; //json으로 조회
        String apiURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        String serviceKey = "HaC3vUu7uwsq%2Bw%2BTBhipMFDUl1sa8YWIsb6uFH%2B9%2B7vp7iMzec3MdivYYnC2HAhw2bwBJmhyUrkHE6gGB1F23g%3D%3D";

        int id_cnt = 1;

        StringBuilder urlbuilder = new StringBuilder(apiURL);
        urlbuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
        urlbuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + nx);
        urlbuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + ny);
        urlbuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + baseDate);
        urlbuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + baseTime);
        urlbuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + type);
        //get 방식 전송, 파라미터 받아오기
        URL url = new URL(urlbuilder.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code : " + connection.getResponseCode());

        BufferedReader reader;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        connection.disconnect();
        String result = builder.toString();
        //JSON 파싱
        JSONObject jsonObject = new JSONObject(result);
        JSONArray items = jsonObject.getJSONObject("response")
                .getJSONObject("body")
                .getJSONObject("items")
                .getJSONArray("item");

        //int israin = 0; //강수형태
        float temp = 0.0f; //온도
        float humidity = 0.0f; //습도
        int rainamount = 0; //강수량

        for(int i = 0; i <  items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String category = item.getString("category"); //날씨 카테고리

            //PTY 강수형태 0:없음 1:비 2:비/눈 3:눈 5:빗방울 6:빗방울눈날림 7:눈날림
            //if(category.equals("PTY")){
            //  israin = item.getInt("obsrValue");
            //}
            // T1H 기온
            if(category.equals("T1H")) {
                temp = item.getFloat("obsrValue");
            }
            //RN1 1시간 강수량(mm)
            if(category.equals("RN1")){
                rainamount = item.getInt("obsrValue");
            }
            //REH 습도
            if(category.equals("REH")){
                humidity = (float) item.getInt("obsrValue");
            }
        }

        Weather weather = Weather.builder()
                .weatherDate(current)
                .temperature(temp)
                .humidity(humidity)
                .groundTemperature(7.5f)
                .groundHumidity(.3f)
                .rainGauge(20)
                .rainAmount(rainamount)
                .co2Concentration(400.6f)
                .build();

        weatherRepository.save(weather);

        return WeatherInfoResDTO.builder()
                .weatherId(weather.getId())
                .weatherDate(weather.getWeatherDate())
                .temperature(weather.getTemperature())
                .humidity(weather.getHumidity())
                .groundTemperature(weather.getGroundTemperature())
                .groundHumidity(weather.getGroundHumidity())
                .rainGauge(weather.getRainGauge())
                .rainAmount(weather.getRainAmount())
                .build();
    }
}