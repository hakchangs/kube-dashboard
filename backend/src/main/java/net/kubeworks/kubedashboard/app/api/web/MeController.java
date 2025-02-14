package net.kubeworks.kubedashboard.app.api.web;

import net.kubeworks.kubedashboard.domain.account.model.AccountEntity;
import net.kubeworks.kubedashboard.domain.account.service.AccountService;
import net.kubeworks.kubedashboard.feature.auth.model.AuthContext;
import net.kubeworks.kubedashboard.feature.auth.model.AuthErrorCode;
import net.kubeworks.kubedashboard.feature.auth.service.AuthContextService;
import net.kubeworks.kubedashboard.shared.api.model.ApiResponseBody;
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
    public ApiResponseBody<MeResponse> me() {

        //TODO: SecurityContext 로드 어노테이션 추가 (ArgumentResolver, Annotation)

        AuthContext authContext = authContextService.loadAuthContext();
        AccountEntity account = accountService.findByUsername(authContext.username())
                .orElseThrow(() -> new BusinessException(AuthErrorCode.NOT_EXISTS_ACCOUNT, "not found account"));

        return ApiResponseBody.success(new MeResponse(
                account.getUid(), account.getUsername(), account.getCreatedAt(),
                account.getModifiedAt(), account.getLastLoginedAt()));
    }

    public record MeResponse(
            String uid,
            String username,
            ZonedDateTime createdAt,
            ZonedDateTime modifiedAt,
            ZonedDateTime lastLoginedAt
    ) {}

}
