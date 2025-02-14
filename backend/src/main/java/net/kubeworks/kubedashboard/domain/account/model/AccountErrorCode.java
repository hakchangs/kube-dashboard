package net.kubeworks.kubedashboard.domain.account.model;

import net.kubeworks.kubedashboard.shared.exception.model.ResultCode;
import net.kubeworks.kubedashboard.shared.exception.model.ResultType;

public enum AccountErrorCode implements ResultCode {

    DUPLICATE_USERNAME(ResultType.ILLEGAL_ARGUMENT, "E0001"),
    ;

    private final ResultType type;
    private final String code;

    AccountErrorCode(ResultType type, String code) {
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
