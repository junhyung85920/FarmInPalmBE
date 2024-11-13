package gdg.farm_in_palm.security.service;

import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.domain.common.util.Iso8601Formatter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ExceptionHandlingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            // 적절한 HTTP 응답 코드 설정
            LocalDateTime now = LocalDateTime.now();  // 에러 발생 시간 캡처
            String formattedTime = Iso8601Formatter.toIso8601String(now); // ISO 8601 포맷

            HttpStatus status = e.getErrorCode().getStatus();
            String errorTitle = e.getErrorCode().name();
            String errorMessage = e.getErrorCode().getMessage();
            String errorReason = status.getReasonPhrase();

            response.setStatus(status.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String jsonResponse = String.format(
                    "{\"time\": \"%s\",\"status\": \"%d\", \"title\": \"%s\", \"message\": \"%s\", \"error\": \"%s\"}",
                    formattedTime, status.value(), errorTitle, errorMessage, errorReason
            );
            response.getWriter().write(jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
