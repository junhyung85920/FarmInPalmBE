package gdg.farm_in_palm.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /*
     * 400 BAD_REQUEST: 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),  // 예시
    ID_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 ID입니다."),
    ID_NOT_EXIST(HttpStatus.BAD_REQUEST, "존재하지 않는 ID입니다."),
    ID_NOT_MATCH(HttpStatus.BAD_REQUEST, "ID가 일치하지 않습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    LOGOUT_FAILED(HttpStatus.BAD_REQUEST, "로그아웃에 실패했습니다."),
    NOT_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 아닙니다."),
    NOT_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "액세스 토큰이 아닙니다."),

    /*
     * 401 UNAUTHORIZED: 권한 없음
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),  // 예시
    NOT_LOGGED_IN(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    LOGOUT(HttpStatus.UNAUTHORIZED, "로그아웃 상태입니다."),


    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    POSTS_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글 정보를 찾을 수 없습니다."),    // 예시
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "기사 정보를 찾을 수 없습니다."),
    MONITOR_NOT_FOUND(HttpStatus.NOT_FOUND, "모니터 정보를 찾을 수 없습니다."),
    STOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "재고 정보를 찾을 수 없습니다."),
    WEATHER_NOT_FOUND(HttpStatus.NOT_FOUND, "날씨 정보를 찾을 수 없습니다."),
    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "이벤트 정보를 찾을 수 없습니다."),


    /*
     * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),   // 예시

    /*
     * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),    // 예시
    USER_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "사용자 정보를 찾을 수 없습니다."),
    INVALID_STATUS(HttpStatus.INTERNAL_SERVER_ERROR, "유효하지 않은 상태입니다."),
    JSON_CONVERT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "JSON 변환에 실패했습니다."),
    SSE_EMITTER_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "SSE 이벤트 전송에 실패했습니다."),
    FILTER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "필터 오류입니다.");


    private final HttpStatus status;
    private final String message;
}
