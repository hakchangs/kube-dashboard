package net.kubeworks.kubedashboard.feature.auth.model;

import net.kubeworks.kubedashboard.shared.exception.model.ResultCode;
import net.kubeworks.kubedashboard.shared.exception.model.ResultType;

public enum AuthErrorCode implements ResultCode {

    EMPTY_USERNAME(ResultType.ILLEGAL_ARGUMENT, "Empty username"),
    EMPTY_PASSWORD(ResultType.ILLEGAL_ARGUMENT, "Empty password"),
    NOT_EXISTS_ACCOUNT(ResultType.ILLEGAL_ARGUMENT, "not exists account"),
    INCORRECT_PASSWORD(ResultType.ILLEGAL_ARGUMENT, "incorrect password"),
    SIGN_FAILED(ResultType.INTERNAL_SERVER_ERROR, "E0001"),
    VERIFY_FAILED(ResultType.INTERNAL_SERVER_ERROR, "E0002"),
    PARSE_FAILED(ResultType.INTERNAL_SERVER_ERROR, "E0003"),
    ;

    private final ResultType type;
    private final String code;

    AuthErrorCode(ResultType type, String code) {
        this.type = type;
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
