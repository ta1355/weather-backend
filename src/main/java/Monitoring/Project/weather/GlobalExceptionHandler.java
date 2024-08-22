package Monitoring.Project.weather;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 사용자 정의 메시지를 변환
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,      // 필드명
                        error -> error.getDefaultMessage() // 오류 메시지
                ));

        // 오류가 하나일 경우 메시지만 반환
        if (errors.size() == 1) {
            String message = errors.values().iterator().next();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        // 여러 개의 오류가 있을 때의 처리 (선택 사항)
        String responseMessage = "다수의 유효성 검사 오류가 발생했습니다.";
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
}