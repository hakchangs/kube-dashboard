package net.kubeworks.kubedashboard.feature.account.model;

import io.swagger.v3.oas.annotations.media.Schema;
import net.kubeworks.kubedashboard.shared.exception.model.ResultCode;
import net.kubeworks.kubedashboard.shared.exception.model.ResultType;

@Schema(description = "회원가입 실패코드")
public enum SignUpErrorCode implements ResultCode {

    @Schema(description = "username 누락")
    EMPTY_USERNAME(ResultType.ILLEGAL_ARGUMENT, "E0001"),
    EMPTY_PASSWORD(ResultType.ILLEGAL_ARGUMENT, "E0002"),
    ;

    private final ResultType type;
    private final String code;

    SignUpErrorCode(ResultType type, String code) {
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
