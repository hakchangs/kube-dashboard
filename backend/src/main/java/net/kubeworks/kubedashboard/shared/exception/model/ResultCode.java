package net.kubeworks.kubedashboard.shared.exception.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "결과코드", type = "string")
public interface ResultCode {

    @Schema(hidden = true)
    String getCode();

    @Schema(hidden = true)
    ResultType getType();

}
