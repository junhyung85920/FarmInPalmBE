package gdg.farm_in_palm.security.service;

import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class AuthUtil {

    // JWT Access Token 발급
    public static String createAccessToken(String loginId, String key, long expireTimeMs) {
        // Claim = Jwt Token에 들어갈 정보
        // Claim에 loginId를 넣어 줌으로써 나중에 loginId를 꺼낼 수 있음
        Claims claims = Jwts.claims();
        claims.put("loginId", loginId);
        claims.put("type", "access");

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    // JWT Refresh Token 발급
    public static String createRefreshToken(String loginId, String key, long refreshExpireTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("loginId", loginId);
        claims.put("type", "refresh");

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    // JWT type 확인
    public static String getTokenType(String token, String secretKey) {
        try {
            Claims claims = extractClaims(token, secretKey);
            return claims.get("type", String.class);
        } catch (Exception e) {
            // 예외 처리
            System.out.println("Error getting token type: " + e.getMessage());
            return null;
        }
    }

    // Claims에서 loginId 꺼내기 + 예외 처리
    public static String getLoginId(String token, String secretKey) {
        try {
            Claims claims = extractClaims(token, secretKey);
            return claims.get("loginId", String.class);
        } catch (Exception e) {
            // 예외 처리
            System.out.println("Error getting loginId: " + e.getMessage());
            return null;
        }

    }

    // 발급된 Token이 만료 시간이 지났는지 체크
    public static boolean isExpired(String token, String secretKey) {
        try {
            Claims claims = extractClaims(token, secretKey);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date(System.currentTimeMillis()));
        } catch (ExpiredJwtException e) {
            // 토큰 만료 예외 처리
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            // 기타 예외 처리
            System.out.println("Error checking token expiration: " + e.getMessage());
            return true;
        }
    }

    // SecretKey를 사용해 Token Parsing
    private static Claims extractClaims(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // access token에서 추출한 loginId와 refresh token에서 추출한 loginId가 일치하는지 확인
    public static boolean isSameLoginId(String accessToken, String refreshToken, String secretKey) {
        String loginIdFromAccessToken = getLoginId(accessToken, secretKey);
        String loginIdFromRefreshToken = getLoginId(refreshToken, secretKey);
        return loginIdFromAccessToken.equals(loginIdFromRefreshToken);
    }
}
