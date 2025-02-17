package net.kubeworks.kubedashboard.app.api.web.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import net.kubeworks.kubedashboard.feature.auth.model.LoginResult;
import net.kubeworks.kubedashboard.feature.auth.model.PasswordLoginForm;
import net.kubeworks.kubedashboard.feature.auth.service.JwtService;
import net.kubeworks.kubedashboard.feature.auth.service.PasswordLoginService;
import net.kubeworks.kubedashboard.shared.api.model.ApiErrorResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final PasswordLoginService passwordLoginService;

    public LoginController(PasswordLoginService passwordLoginService) {
        this.passwordLoginService = passwordLoginService;
    }

    @PostMapping("/auth/login")
    @Tag(name = "auth")
    @Operation(summary = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력값", content = @Content(schemaProperties = {
                    @SchemaProperty(name = "code", schema = @Schema(allowableValues = {
                            "AUTH_EMPTY_USERNAME",
                            "AUTH_EMPTY_PASSWORD",
                            "AUTH_NOT_EXISTS_ACCOUNT",
                            "AUTH_INCORRECT_PASSWORD"
                    })),
                    @SchemaProperty(name = "message", schema = @Schema(implementation = String.class)),
                    @SchemaProperty(name = "data", schema = @Schema(implementation = Object.class)),
            }, schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content(schema = @Schema())),
    })
    public LoginResult login(@RequestBody PasswordLoginForm form, HttpServletResponse response) {

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

        return result;
    }
}
