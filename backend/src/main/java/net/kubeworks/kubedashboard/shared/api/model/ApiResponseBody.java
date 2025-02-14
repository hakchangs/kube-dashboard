package net.kubeworks.kubedashboard.shared.api.model;

import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import net.kubeworks.kubedashboard.shared.exception.model.ResultCode;
import net.kubeworks.kubedashboard.shared.exception.model.ResultType;

public record ApiResponseBody<T>(
        ResultCode code,
        String message,
        T data
) {

    public static <T> ApiResponseBody<T> success(T data, String message) {
        return new ApiResponseBody<>(SuccessCode.SUCCESS, message, data);
    }

    public static <T> ApiResponseBody<T> success(T data) {
        return success(data, null);
    }

    public static <T> ApiResponseBody<T> fail(BusinessException e) {
        return new ApiResponseBody<>(e.getCode(), e.getMessage(), null);
    }

    public enum SuccessCode implements ResultCode {
        SUCCESS("S0000"),;

        private final ResultType type;
        private final String code;

        SuccessCode(String code) {
            this.type = ResultType.SUCCESS;
            this.code = code;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public ResultType getType() {
            return type;
        }
    }

}
