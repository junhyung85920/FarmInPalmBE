package gdg.farm_in_palm.domain.stock.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StockInfoReqDTO {
    private String stockName;
    private Integer stockQuantity;
}
