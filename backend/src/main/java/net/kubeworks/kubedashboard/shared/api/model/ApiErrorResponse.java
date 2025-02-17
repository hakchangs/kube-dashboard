package net.kubeworks.kubedashboard.shared.api.model;

import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import net.kubeworks.kubedashboard.shared.exception.model.ErrorCode;

public record ApiErrorResponse<T>(
        ErrorCode code,
        String message,
        T data
) {

    public static <T> ApiErrorResponse<T> from(BusinessException e) {
        return new ApiErrorResponse<>(e.getCode(), e.getMessage(), null);
    }
}
