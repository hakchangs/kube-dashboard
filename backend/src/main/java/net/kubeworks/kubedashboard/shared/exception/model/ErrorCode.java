package net.kubeworks.kubedashboard.shared.exception.model;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "결과코드", type = "string")
public interface ErrorCode {

    @JsonValue
    @Schema(hidden = true)
    String getCode();

    @Schema(hidden = true)
    ErrorType getType();

}
