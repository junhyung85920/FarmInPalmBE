package gdg.farm_in_palm.domain.monitor.controller;

import gdg.farm_in_palm.domain.common.dto.SuccessResDTO;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoReqDTO;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoResDTO;
import gdg.farm_in_palm.domain.monitor.service.MonitorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monitor")
public class MonitorController {

    private final MonitorService monitorService;

    // 모든 Monitor 조회
    @GetMapping
    public List<MonitorInfoResDTO> getAllMonitors() {
        return monitorService.getAllMonitors();
    }

    // Monitor 생성
    @PostMapping
    public MonitorInfoResDTO createMonitor(MonitorInfoReqDTO monitorInfoReqDTO) {
        return monitorService.createMonitor(monitorInfoReqDTO);
    }

    // Monitor 삭제
    @DeleteMapping("/{monitorId}")
    public SuccessResDTO deleteMonitor(@PathVariable("monitorId") Long monitorId) {
        return monitorService.deleteMonitor(monitorId);
    }
}
