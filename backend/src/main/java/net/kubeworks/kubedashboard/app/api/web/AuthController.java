package net.kubeworks.kubedashboard.app.api.web;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import net.kubeworks.kubedashboard.feature.auth.model.*;
import net.kubeworks.kubedashboard.feature.auth.service.SignUpService;
import net.kubeworks.kubedashboard.feature.auth.service.JwtService;
import net.kubeworks.kubedashboard.feature.auth.service.PasswordLoginService;
import net.kubeworks.kubedashboard.shared.api.model.ApiResponseBody;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SignUpService signUpService;
    private final PasswordLoginService passwordLoginService;

    public AuthController(SignUpService signUpService, PasswordLoginService passwordLoginService) {
        this.signUpService = signUpService;
        this.passwordLoginService = passwordLoginService;
    }

    @PostMapping("/signup")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력값", content = @Content(schemaProperties = {
                    @SchemaProperty(name = "code", schema = @Schema(implementation = AuthErrorCode.class)),
                    @SchemaProperty(name = "message", schema = @Schema(implementation = String.class)),
                    @SchemaProperty(name = "data", schema = @Schema(implementation = Object.class)),
            })),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content(schema = @Schema(implementation = ApiResponseBody.class))),
    })
    public ApiResponseBody<SignUpResult> signUpBySelf(@RequestBody SelfSignUpForm form) {
        SignUpResult data = signUpService.signUpBySelf(form);
        return ApiResponseBody.success(data);
    }

    @PostMapping("/login")
    public ApiResponseBody<LoginResult> login(@RequestBody PasswordLoginForm form, HttpServletResponse response) {

        LoginResult result = passwordLoginService.login(form);
        long expirationSeconds = result.expiresIn();

        ResponseCookie cookie = ResponseCookie.from(JwtService.NAME, result.accessToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(expirationSeconds)
                .sameSite("None")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        return ApiResponseBody.success(result);
    }

    @PostMapping("/logout")
    public ApiResponseBody<Void> logout(HttpServletResponse response) {

        ResponseCookie cookie = ResponseCookie.from(JwtService.NAME, "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0) // 즉시 만료
                .sameSite("None")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        return ApiResponseBody.success(null, "logout success!");
    }

}
