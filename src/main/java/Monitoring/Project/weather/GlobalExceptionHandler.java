package Monitoring.Project.weather;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 오류 메시지를 사용자 정의 메시지로 변환
        Map<String, String> errors = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        error -> {
                            String defaultMessage = error.getDefaultMessage();
                            // 필요에 따라 여기서 메시지를 변환하거나 추가적으로 처리할 수 있습니다
                            return defaultMessage;
                        }
                ));

        Map<String, String> response = new HashMap<>();
        response.put("오류", errors.toString()); // 또는 원하는 포맷으로 변환

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}