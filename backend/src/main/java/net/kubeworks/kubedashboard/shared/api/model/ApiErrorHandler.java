package net.kubeworks.kubedashboard.shared.api.model;

import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import net.kubeworks.kubedashboard.shared.exception.model.ResultType;
import net.kubeworks.kubedashboard.shared.exception.model.ResultCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponseBody<?>> handleBusinessException(BusinessException e) {

        ResultCode code = e.getCode();

        if (ResultType.ILLEGAL_ARGUMENT == code.getType()) {
            return ResponseEntity.badRequest().body(ApiResponseBody.fail(e));
        }

        return ResponseEntity.internalServerError().build();
    }

}
