package gdg.farm_in_palm.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginReqDTO {

    private String loginId;
    private String password;
}
