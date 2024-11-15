package gdg.farm_in_palm.domain.stock.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StockInfoResDTO {
    private Long stockId;
    private String stockName;
    private Float stockQuantity;
    private String stockUnit;

    @Builder
    public StockInfoResDTO(Long stockId, String stockName, Float stockQuantity, String stockUnit) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.stockQuantity = stockQuantity;
        this.stockUnit = stockUnit;
    }
}
