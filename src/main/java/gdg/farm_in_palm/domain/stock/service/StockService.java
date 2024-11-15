package gdg.farm_in_palm.domain.stock.service;

import gdg.farm_in_palm.domain.common.dto.SuccessResDTO;
import gdg.farm_in_palm.domain.stock.Stock;
import gdg.farm_in_palm.domain.stock.dto.StockInfoReqDTO;
import gdg.farm_in_palm.domain.stock.dto.StockInfoResDTO;
import gdg.farm_in_palm.domain.stock.repository.StockRepository;
import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {

    private final StockRepository stockRepository;

    // 모든 Stock 조회
    public List<StockInfoResDTO> getAllStocksByUserId(Long userId) {
        List<Stock> stocks = stockRepository.findByUserId(userId);
        List<StockInfoResDTO> stockInfoResDTOs = new ArrayList<>();

        for (Stock stock : stocks) {
            StockInfoResDTO stockInfoResDTO = StockInfoResDTO.builder()
                    .stockId(stock.getId())
                    .stockName(stock.getStockName())
                    .stockQuantity(stock.getStockQuantity())
                    .stockUnit(stock.getStockUnit())
                    .build();

            stockInfoResDTOs.add(stockInfoResDTO);
        }

        return stockInfoResDTOs;
    }

    // Stock 조회
    public StockInfoResDTO getStockById(Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new CustomException(ErrorCode.STOCK_NOT_FOUND));

        return StockInfoResDTO.builder()
                .stockId(stock.getId())
                .stockName(stock.getStockName())
                .stockQuantity(stock.getStockQuantity())
                .stockUnit(stock.getStockUnit())
                .build();
    }

    // Stock 생성
    @Transactional
    public StockInfoResDTO createStock(StockInfoReqDTO stockInfoReqDTO) {
        Stock stock = Stock.builder()
                .stockName(stockInfoReqDTO.getStockName())
                .stockQuantity(stockInfoReqDTO.getStockQuantity())
                .stockUnit(stockInfoReqDTO.getStockUnit())
                .build();

        stockRepository.save(stock);

        return StockInfoResDTO.builder()
                .stockId(stock.getId())
                .stockName(stock.getStockName())
                .stockQuantity(stock.getStockQuantity())
                .stockUnit(stock.getStockUnit())
                .build();
    }

    // Stock 수정
    @Transactional
    public StockInfoResDTO updateStock(Long stockId, StockInfoReqDTO stockInfoReqDTO) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new CustomException(ErrorCode.STOCK_NOT_FOUND));

        stock.setStockName(stockInfoReqDTO.getStockName());
        stock.setStockQuantity(stockInfoReqDTO.getStockQuantity());
        stock.setStockUnit(stockInfoReqDTO.getStockUnit());

        stockRepository.save(stock);

        return StockInfoResDTO.builder()
                .stockId(stock.getId())
                .stockName(stock.getStockName())
                .stockQuantity(stock.getStockQuantity())
                .stockUnit(stock.getStockUnit())
                .build();
    }

    // Stock 삭제
    @Transactional
    public SuccessResDTO deleteStock(Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new CustomException(ErrorCode.STOCK_NOT_FOUND));

        stockRepository.delete(stock);

        return SuccessResDTO.builder()
                .success(true)
                .build();
    }
}
