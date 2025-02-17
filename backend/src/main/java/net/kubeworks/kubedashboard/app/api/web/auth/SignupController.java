package net.kubeworks.kubedashboard.app.api.web.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.kubeworks.kubedashboard.feature.auth.model.*;
import net.kubeworks.kubedashboard.feature.auth.service.SignUpService;
import net.kubeworks.kubedashboard.shared.api.model.ApiErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    private final SignUpService signUpService;

    public SignupController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/auth/signup")
    @Tag(name = "auth", description = "인증")
    @Operation(summary = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력값", content = @Content(schemaProperties = {
                    @SchemaProperty(name = "code", schema = @Schema(allowableValues = {
                            "ACCOUNT_EMPTY_USERNAME",
                            "ACCOUNT_EMPTY_PASSWORD",
                            "ACCOUNT_DUPLICATE_USERNAME"
                    })),
                    @SchemaProperty(name = "message", schema = @Schema(implementation = String.class)),
                    @SchemaProperty(name = "data", schema = @Schema(implementation = Object.class)),
            }, schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content(schema = @Schema())),
    })
    public SignUpResult signUpBySelf(@RequestBody SelfSignUpForm form) {
        return signUpService.signUpBySelf(form);
    }
}
