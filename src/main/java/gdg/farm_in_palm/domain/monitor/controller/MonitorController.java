package gdg.farm_in_palm.domain.monitor.controller;

import gdg.farm_in_palm.domain.common.dto.SuccessResDTO;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoReqDTO;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoResDTO;
import gdg.farm_in_palm.domain.monitor.service.MonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;

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

    // 특정 Monitor 조회
    @GetMapping("/detail/{monitorId}")
    public MonitorInfoResDTO getMonitorById(@PathVariable("monitorId") Long monitorId) {
        return monitorService.getMonitorById(monitorId);
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

    // streaming (static folder에 저장된 video stream)
    @RequestMapping(value = "/stream", method = RequestMethod.GET)
    public ResponseEntity<ResourceRegion> streamVideo(@RequestHeader HttpHeaders headers) throws Exception {
        return monitorService.streamVideo(headers);
    }

}
