package net.kubeworks.kubedashboard.app.api.web;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.kubeworks.kubedashboard.feature.account.model.SelfSignUpForm;
import net.kubeworks.kubedashboard.feature.account.model.SignUpErrorCode;
import net.kubeworks.kubedashboard.feature.account.model.SignUpResult;
import net.kubeworks.kubedashboard.feature.account.service.SignUpService;
import net.kubeworks.kubedashboard.shared.api.model.ApiResponseBody;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/signup")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력값", content = @Content(schemaProperties = {
                    @SchemaProperty(name = "code", schema = @Schema(implementation = SignUpErrorCode.class)),
                    @SchemaProperty(name = "message", schema = @Schema(implementation = String.class)),
                    @SchemaProperty(name = "data", schema = @Schema(implementation = Object.class)),
            })),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content(schema = @Schema(implementation = ApiResponseBody.class))),
    })
    public ApiResponseBody<SignUpResult> signUpBySelf(@RequestBody SelfSignUpForm form) {
        SignUpResult data = signUpService.signUpBySelf(form);
        return ApiResponseBody.success(data);
    }

}
