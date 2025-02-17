package net.kubeworks.kubedashboard.shared.api.model;

import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import net.kubeworks.kubedashboard.shared.exception.model.ErrorType;
import net.kubeworks.kubedashboard.shared.exception.model.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse<?>> handleBusinessException(BusinessException e) {

        ErrorCode code = e.getCode();

        if (ErrorType.ILLEGAL_ARGUMENT == code.getType()) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.from(e));
        }

        if (ErrorType.NOT_FOUND == code.getType()) {
            return ResponseEntity.notFound().build();
        }

        if (ErrorType.INTERNAL_SERVER_ERROR == code.getType()) {
            return ResponseEntity.status(500).body(ApiErrorResponse.from(e));
        }

        return ResponseEntity.internalServerError().build();
    }

}
