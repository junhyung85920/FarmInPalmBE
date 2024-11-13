package gdg.farm_in_palm.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshReqDTO {

    private String loginId;
    private String refreshToken;

}
