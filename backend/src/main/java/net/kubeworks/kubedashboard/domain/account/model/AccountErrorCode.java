package net.kubeworks.kubedashboard.domain.account.model;

import net.kubeworks.kubedashboard.shared.exception.model.ErrorCode;
import net.kubeworks.kubedashboard.shared.exception.model.ErrorType;

public enum AccountErrorCode implements ErrorCode {

    EMPTY_USERNAME(ErrorType.ILLEGAL_ARGUMENT),
    EMPTY_PASSWORD(ErrorType.ILLEGAL_ARGUMENT),
    DUPLICATE_USERNAME(ErrorType.ILLEGAL_ARGUMENT),
    ;

    private final ErrorType type;
    private final String code;

    AccountErrorCode(ErrorType type) {
        this.type = type;
        this.code = "ACCOUNT_" + name();
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
