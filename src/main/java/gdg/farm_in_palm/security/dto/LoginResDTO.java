package gdg.farm_in_palm.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResDTO {
    private String loginId;
    private String name;
    private String accessToken;
    private String refreshToken;
    private String accessTokenExpiredAt;

    @Builder
    public LoginResDTO(String loginId, String name, String accessToken, String refreshToken, String accessTokenExpiredAt) {
        this.loginId = loginId;
        this.name = name;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiredAt = accessTokenExpiredAt;
    }
}
