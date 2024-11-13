package gdg.farm_in_palm.domain.stock.controller;

import gdg.farm_in_palm.domain.common.dto.SuccessResDTO;
import gdg.farm_in_palm.domain.stock.dto.StockInfoReqDTO;
import gdg.farm_in_palm.domain.stock.dto.StockInfoResDTO;
import gdg.farm_in_palm.domain.stock.service.StockService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    // 모든 Stock 조회
    @GetMapping
    public List<StockInfoResDTO> getAllStocks() {
        return stockService.getAllStocks();
    }

    // Stock 생성
    @PostMapping
    public StockInfoResDTO createStock(StockInfoReqDTO stockInfoReqDTO) {
        return stockService.createStock(stockInfoReqDTO);
    }

    // Stock 수정
    @PutMapping("/{stockId}")
    public StockInfoResDTO updateStock(@PathVariable("stockId") Long stockId, StockInfoReqDTO stockInfoReqDTO) {
        return stockService.updateStock(stockId, stockInfoReqDTO);
    }

    // Stock 삭제
    @DeleteMapping("/{stockId}")
    public SuccessResDTO deleteStock(@PathVariable("stockId") Long stockId) {
        return stockService.deleteStock(stockId);
    }
}
