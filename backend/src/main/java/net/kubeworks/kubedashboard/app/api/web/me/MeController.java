package net.kubeworks.kubedashboard.app.api.web.me;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.kubeworks.kubedashboard.domain.account.model.Account;
import net.kubeworks.kubedashboard.domain.account.service.AccountService;
import net.kubeworks.kubedashboard.feature.auth.model.AuthContext;
import net.kubeworks.kubedashboard.feature.auth.model.AuthErrorCode;
import net.kubeworks.kubedashboard.feature.auth.service.AuthContextService;
import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class MeController {

    private final AccountService accountService;
    private final AuthContextService authContextService;

    public MeController(AccountService accountService, AuthContextService authContextService) {
        this.accountService = accountService;
        this.authContextService = authContextService;
    }

    @GetMapping("/me")
    @Tag(name = "auth")
    @Operation(summary = "내 정보")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "권한없음", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(schema = @Schema())),
    })
    public MeResponse me() {

        //TODO: SecurityContext 로드 어노테이션 추가 (ArgumentResolver, Annotation)

        AuthContext authContext = authContextService.loadAuthContext();
        Account account = accountService.findByUsername(authContext.username())
                .orElseThrow(() -> new BusinessException(AuthErrorCode.NOT_EXISTS_ACCOUNT, "not found account"));

        return new MeResponse(
                account.getUid(), account.getUsername(), account.getCreatedAt(),
                account.getModifiedAt(), account.getLastLoginedAt());
    }

    public record MeResponse(
            String uid,
            String username,
            ZonedDateTime createdAt,
            ZonedDateTime modifiedAt,
            ZonedDateTime lastLoginedAt
    ) {}

}
