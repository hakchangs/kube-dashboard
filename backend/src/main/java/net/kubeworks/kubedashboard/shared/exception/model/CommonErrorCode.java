package net.kubeworks.kubedashboard.shared.exception.model;

public enum CommonErrorCode implements ErrorCode {

    NOT_FOUND(ErrorType.NOT_FOUND),
    INTERNAL_SERVER_ERROR(ErrorType.INTERNAL_SERVER_ERROR),
    ;

    private final ErrorType type;
    private final String code;

    CommonErrorCode(ErrorType type) {
        this.type = type;
        this.code = name();
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
