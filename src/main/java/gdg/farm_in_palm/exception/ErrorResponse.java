package gdg.farm_in_palm.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private final LocalDateTime time = LocalDateTime.now();
    private final int status;
    private final String title;
    private final String message;
    private final String error;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus().value())
                        .title(errorCode.name())
                        .message(errorCode.getMessage())
                        .error(errorCode.getStatus().getReasonPhrase())
                        .build());
    }

}