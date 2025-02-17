package net.kubeworks.kubedashboard.shared.exception.model;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode code;
    private final Object data;

    public BusinessException(ErrorCode code, String message) {
        this(code, message, null);
    }

    public BusinessException(ErrorCode code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

}
