package gdg.farm_in_palm.domain.stock.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StockInfoResDTO {
    private Long stockId;
    private String stockName;
    private Integer stockQuantity;

    @Builder
    public StockInfoResDTO(Long stockId, String stockName, Integer stockQuantity) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.stockQuantity = stockQuantity;
    }

}
