package gdg.farm_in_palm.domain.monitor.service;

import gdg.farm_in_palm.domain.common.dto.SuccessResDTO;
import gdg.farm_in_palm.domain.monitor.Monitor;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoReqDTO;
import gdg.farm_in_palm.domain.monitor.dto.MonitorInfoResDTO;
import gdg.farm_in_palm.domain.monitor.repository.MonitorRepository;
import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonitorService {

    private final MonitorRepository monitorRepository;

    // 모든 Monitor 조회
    public List<MonitorInfoResDTO> getAllMonitors() {
        List<Monitor> monitors = monitorRepository.findAll();
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
}
