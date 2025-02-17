package net.kubeworks.kubedashboard.feature.auth.model;

import net.kubeworks.kubedashboard.shared.exception.model.ErrorCode;
import net.kubeworks.kubedashboard.shared.exception.model.ErrorType;

public enum AuthErrorCode implements ErrorCode {

    EMPTY_USERNAME(ErrorType.ILLEGAL_ARGUMENT),
    EMPTY_PASSWORD(ErrorType.ILLEGAL_ARGUMENT),
    NOT_EXISTS_ACCOUNT(ErrorType.ILLEGAL_ARGUMENT),
    INCORRECT_PASSWORD(ErrorType.ILLEGAL_ARGUMENT),
    SIGN_FAILED(ErrorType.INTERNAL_SERVER_ERROR),
    VERIFY_FAILED(ErrorType.INTERNAL_SERVER_ERROR),
    PARSE_FAILED(ErrorType.INTERNAL_SERVER_ERROR),
    ;

    private final ErrorType type;
    private final String code;

    AuthErrorCode(ErrorType type) {
        this.type = type;
        this.code = "AUTH_" + name();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ErrorType getType() {
        return type;
    }

}
