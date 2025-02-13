package net.kubeworks.kubedashboard.shared.exception.model;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ResultCode code;

    public BusinessException(ResultCode code, String message) {
        super(message);
        this.code = code;
    }

}
