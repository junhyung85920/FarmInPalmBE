package gdg.farm_in_palm.domain.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CalculateElapsedTime {

    private static final DateTimeFormatter ISO_8601_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // 시간 문자열을 LocalTime 객체로 변환하는 메서드
    private static LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(timeString, formatter);
    }

    // 시간 문자열을 LocalDateTime 객체로 변환하는 메서드
    private static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, ISO_8601_FORMATTER);
    }

    // 두 시간 문자열의 차이를 계산하는 메서드
    public static String calculateElapsedTime(String dateTime1, String dateTime2) {
        LocalDateTime dt1 = parseDateTime(dateTime1);
        LocalDateTime dt2 = parseDateTime(dateTime2);

        Duration duration = Duration.between(dt1, dt2);

        // 차이를 양수로 만들기 위해 절대값을 취함
        long seconds = Math.abs(duration.getSeconds());

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    // 두 시간 문자열을 더하고 결과를 문자열로 반환하는 메서드
    public static String addTimes(String time1, String time2) {
        LocalTime t1 = parseTime(time1);
        LocalTime t2 = parseTime(time2);

        // LocalTime 객체는 하루(24시간)를 기준으로 작동하므로 시간 합계를 직접 계산
        int totalSeconds = t1.toSecondOfDay() + t2.toSecondOfDay();

        // totalSeconds를 LocalTime 객체로 변환 (24시간을 초과할 경우를 고려하여 86400으로 나눈 나머지 사용)
        LocalTime resultTime = LocalTime.ofSecondOfDay(totalSeconds % 86400);

        // 결과를 문자열로 변환
        return resultTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    // 두 시간 문자열을 빼고 결과를 문자열로 반환하는 메서드
    public static String subtractTimes(String dateTime1, String dateTime2) {
        LocalDateTime dt1 = parseDateTime(dateTime1);
        LocalDateTime dt2 = parseDateTime(dateTime2);

        // dt1과 dt2를 비교하여 더 큰 값에서 더 작은 값을 뺌
        Duration duration;
        if (dt1.isAfter(dt2)) duration = Duration.between(dt2, dt1);
        else duration = Duration.between(dt1, dt2);

        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}
