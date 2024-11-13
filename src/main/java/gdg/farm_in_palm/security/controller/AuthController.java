package gdg.farm_in_palm.security.controller;

import gdg.farm_in_palm.security.service.*;
import gdg.farm_in_palm.security.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/register")
    public boolean register(@RequestBody RegisterReqDTO registerReqDTO) {
        return authService.register(registerReqDTO);
    }

    // 로그인
    @PostMapping("/login")
    public LoginResDTO login(@RequestBody LoginReqDTO loginReqDTO) {
        return authService.login(loginReqDTO);
    }

    // Access token 재발급
    @PostMapping("/refresh")
    public LoginResDTO refresh(@RequestBody RefreshReqDTO refreshReqDTO) {
        return authService.refresh(refreshReqDTO);
    }

    // 로그아웃
    @PostMapping("/logout")
    public boolean logout(@RequestBody LogoutReqDTO logoutReqDTO) {
        return authService.logout(logoutReqDTO);
    }
}
