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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonitorService {

    private final MonitorRepository monitorRepository;

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

    // streaming
    public ResponseEntity<FileSystemResource> streamVideo(String videoName, HttpHeaders headers) throws IOException {

        File videoFile = new File("/Users/junhyung85920/Downloads/" + videoName);
        long fileSize = videoFile.length();
        List<HttpRange> ranges = headers.getRange();

        if (!ranges.isEmpty()) {
            HttpRange range = ranges.get(0);
            long start = range.getRangeStart(fileSize);
            long end = range.getRangeEnd(fileSize);
            FileSystemResource resource = new FileSystemResource(videoFile);

            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .header(HttpHeaders.CONTENT_RANGE, "bytes " + start + "-" + end + "/" + fileSize)
                    .contentLength(end - start + 1)
                    .body(resource);
        } else {
            return ResponseEntity.ok()
                    .contentLength(fileSize)
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(new FileSystemResource(videoFile));
        }
    }
}
