package gdg.farm_in_palm.security.service;

import gdg.farm_in_palm.domain.common.util.Iso8601Formatter;
import gdg.farm_in_palm.domain.user.User;
import gdg.farm_in_palm.security.dto.*;
import gdg.farm_in_palm.domain.user.repository.UserRepository;
import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret}") String secretKey;  // application.properties에 설정한 jwt.secret 값 가져오기

    long expireTimeMs = 1000 * 60 * 60 * 12;     // Token 유효 시간 = 1시간 (임시로 12시간으로 설정)
    long refreshExpireTimeMs = 1000 * 60 * 60 * 24 * 14; // Refresh Token 갱신 유효 시간 = 14일
    Date millisToDate;
    String accessTokenExpiredAt;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    // loginId 중복 체크
    public boolean checkLoginIdDuplicate(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    // 회원가입
    public boolean register(RegisterReqDTO registerReqDTO) {

        // loginId 중복 체크
        if(checkLoginIdDuplicate(registerReqDTO.getLoginId())) {
            throw new CustomException(ErrorCode.ID_EXIST);
        }

        userRepository.save(registerReqDTO.toEntity(encoder.encode(registerReqDTO.getPassword())));

        return true;
    }

    // 로그인
    public LoginResDTO login(LoginReqDTO loginReqDTO) {
        Optional<User> optionalUser = userRepository.findByLoginId(loginReqDTO.getLoginId());

        // loginId와 일치하는 User가 없으면 예외 처리
        if(optionalUser.isEmpty()) {
            throw new CustomException(ErrorCode.ID_NOT_EXIST);
        }

        User user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 예외 처리
        if(!encoder.matches(loginReqDTO.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        LoginResDTO loginResDTO;

        // 로그인 성공 => Jwt Token 발급
        String accessToken = AuthUtil.createAccessToken(user.getLoginId(), secretKey, expireTimeMs);
        String refreshToken = AuthUtil.createRefreshToken(user.getLoginId(), secretKey, refreshExpireTimeMs);

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        // 만료 예정 시각 = 현재 시각 + expireTimeMs
        millisToDate = new Date(System.currentTimeMillis() + expireTimeMs);
        accessTokenExpiredAt = Iso8601Formatter.toIso8601String(LocalDateTime.parse(sdf.format(millisToDate)));

        loginResDTO = LoginResDTO.builder()
                .loginId(user.getLoginId())
                .name(user.getName())
                .accessToken(accessToken)
                .refreshToken(user.getRefreshToken())
                .accessTokenExpiredAt(accessTokenExpiredAt)
                .build();

        return loginResDTO;
    }

    // 로그인한 사용자 정보 조회
    public User getLoginUserByLoginId(String loginId) {

        Optional<User> user = userRepository.findByLoginId(loginId);

        return user
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_LOGGED_IN));

    }

    // Access Token 재발급
    public LoginResDTO refresh(RefreshReqDTO refreshReqDTO) {

        // refresh token인지 확인
        if(!AuthUtil.getTokenType(refreshReqDTO.getRefreshToken(), secretKey).equals("refresh")) {
            throw new CustomException(ErrorCode.NOT_REFRESH_TOKEN);
        }

        // Refresh Token이 만료되었는지 체크
        if(AuthUtil.isExpired(refreshReqDTO.getRefreshToken(), secretKey)) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        }

        // Refresh Token에서 loginId 추출
        String loginId = AuthUtil.getLoginId(refreshReqDTO.getRefreshToken(), secretKey);

        // Refresh Token의 loginId와 요청한 loginId가 일치하는지 체크
        if(!loginId.equals(refreshReqDTO.getLoginId())){
            throw new CustomException(ErrorCode.ID_NOT_MATCH);
        }

        // loginId로 User 찾기
        Optional<User> optionalUser = userRepository.findByLoginId(loginId);

        // User가 없으면 예외 처리
        if(optionalUser.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        User user = optionalUser.get();

        if(user.getRefreshToken() == null) {
            throw new CustomException(ErrorCode.LOGOUT);
        }

        // Access Token 재발급
        String newAccessToken = AuthUtil.createAccessToken(user.getLoginId(), secretKey, expireTimeMs);
        String newRefreshToken = AuthUtil.createRefreshToken(user.getLoginId(), secretKey, refreshExpireTimeMs);

        user.setRefreshToken(newRefreshToken);
        userRepository.save(user);

        // 만료 예정 시각 = 현재 시각 + expireTimeMs
        millisToDate = new Date(System.currentTimeMillis() + expireTimeMs);
        accessTokenExpiredAt = Iso8601Formatter.toIso8601String(LocalDateTime.parse(sdf.format(millisToDate)));

        return LoginResDTO.builder()
                .loginId(user.getLoginId())
                .name(user.getName())
                .accessToken(newAccessToken)
                .refreshToken(user.getRefreshToken())
                .accessTokenExpiredAt(accessTokenExpiredAt)
                .build();
    }

    // 로그아웃 (Access Token 만료)
    public boolean logout(LogoutReqDTO logoutReqDTO) {

        // access token인지 확인
        if(!AuthUtil.getTokenType(logoutReqDTO.getAccessToken(), secretKey).equals("access")) {
            throw new CustomException(ErrorCode.NOT_ACCESS_TOKEN);
        }

        User user = userRepository.findByLoginId((logoutReqDTO.getLoginId()))
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(AuthUtil.isSameLoginId(logoutReqDTO.getAccessToken(),user.getRefreshToken(), secretKey)) {
            user.setRefreshToken(null);
            userRepository.save(user);
            return true;
        }

        throw new CustomException(ErrorCode.LOGOUT_FAILED);
    }
}
