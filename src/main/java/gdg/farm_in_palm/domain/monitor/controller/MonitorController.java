package gdg.farm_in_palm.domain.monitor.controller;

import gdg.farm_in_palm.domain.common.dto.SuccessResDTO;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoReqDTO;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoResDTO;
import gdg.farm_in_palm.domain.monitor.service.MonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monitor")
public class MonitorController {

    private final MonitorService monitorService;

    // 모든 Monitor 조회
    @GetMapping("/{userId}")
    public List<MonitorInfoResDTO> getAllMonitorsByUserId(@PathVariable("userId") Long userId) {
        return monitorService.getAllMonitorsByUserId(userId);
    }

    // Monitor 생성
    @PostMapping("/detail")
    public MonitorInfoResDTO createMonitor(@RequestBody MonitorInfoReqDTO monitorInfoReqDTO) {
        return monitorService.createMonitor(monitorInfoReqDTO);
    }

    // Monitor 삭제
    @DeleteMapping("/detail/{monitorId}")
    public SuccessResDTO deleteMonitor(@PathVariable("monitorId") Long monitorId) {
        return monitorService.deleteMonitor(monitorId);
    }

    // streaming
    @GetMapping(value = "/stream/{videoName}", produces = "video/mp4")
    public ResponseEntity<FileSystemResource> streamVideo(
            @PathVariable String videoName, @RequestHeader HttpHeaders headers) throws IOException {
        return monitorService.streamVideo(videoName, headers);
    }

}
