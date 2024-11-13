package gdg.farm_in_palm.domain.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SuccessResDTO {
    private boolean success;

    @Builder
    public SuccessResDTO(boolean success) {
        this.success = success;
    }
}
