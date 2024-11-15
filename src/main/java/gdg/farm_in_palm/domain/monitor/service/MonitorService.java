package gdg.farm_in_palm.domain.monitor.service;

import gdg.farm_in_palm.domain.common.dto.SuccessResDTO;
import gdg.farm_in_palm.domain.monitor.Monitor;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoReqDTO;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoResDTO;
import gdg.farm_in_palm.domain.monitor.repository.MonitorRepository;
import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonitorService {

    private final MonitorRepository monitorRepository;
    private final String URL = "src/main/resources/static";

    // 모든 Monitor 조회
    public List<MonitorInfoResDTO> getAllMonitorsByUserId(Long userId) {
        List<Monitor> monitors = monitorRepository.findByUserId(userId);
        List<MonitorInfoResDTO> monitorInfoResDTOs = new ArrayList<>();

        for (Monitor monitor : monitors) {
            MonitorInfoResDTO monitorInfoResDTO = MonitorInfoResDTO.builder()
                    .monitorId(monitor.getId())
                    .monitorName(monitor.getMonitorName())
                    .temperature(monitor.getTemperature())
                    .humidity(monitor.getHumidity())
                    .groundTemperature(monitor.getGroundTemperature())
                    .groundHumidity(monitor.getGroundHumidity())
                    .co2Concentration(monitor.getCo2Concentration())
                    .build();

            monitorInfoResDTOs.add(monitorInfoResDTO);
        }

        return monitorInfoResDTOs;
    }

    // Monitor 생성
    @Transactional
    public MonitorInfoResDTO createMonitor(MonitorInfoReqDTO monitorInfoReqDTO) {
        Monitor monitor = Monitor.builder()
                .monitorName(monitorInfoReqDTO.getMonitorName())
                .temperature(monitorInfoReqDTO.getTemperature())
                .humidity(monitorInfoReqDTO.getHumidity())
                .groundTemperature(monitorInfoReqDTO.getGroundTemperature())
                .groundHumidity(monitorInfoReqDTO.getGroundHumidity())
                .co2Concentration(monitorInfoReqDTO.getCo2Concentration())
                .build();

        monitorRepository.save(monitor);

        return MonitorInfoResDTO.builder()
                .monitorId(monitor.getId())
                .monitorName(monitor.getMonitorName())
                .temperature(monitor.getTemperature())
                .humidity(monitor.getHumidity())
                .groundTemperature(monitor.getGroundTemperature())
                .groundHumidity(monitor.getGroundHumidity())
                .co2Concentration(monitor.getCo2Concentration())
                .build();
    }

    // Monitor 삭제
    @Transactional
    public SuccessResDTO deleteMonitor(Long monitorId) {
        Monitor monitor = monitorRepository.findById(monitorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MONITOR_NOT_FOUND));

        monitorRepository.delete(monitor);

        return SuccessResDTO.builder()
                .success(true)
                .build();
    }

    // streaming (static folder에 저장된 video stream)
    public ResponseEntity<ResourceRegion> streamVideo(HttpHeaders headers) throws IOException {

        String path = URL + "/test.mp4";

        ResourceRegion region;
        Resource resource = new FileSystemResource(path);

        long chunkSize = 1024 * 1024;
        long contentLength = resource.contentLength();


        try {
            HttpRange httpRange = headers.getRange().stream().findFirst().get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end -start + 1);

            region = new ResourceRegion(resource, start, rangeLength);
        } catch (Exception e) {
            long rangeLength = Long.min(chunkSize, contentLength);
            region = new ResourceRegion(resource, 0, rangeLength);
        }

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .header("Accept-Ranges", "bytes")
                .eTag(path)
                .body(region);

    }
}
