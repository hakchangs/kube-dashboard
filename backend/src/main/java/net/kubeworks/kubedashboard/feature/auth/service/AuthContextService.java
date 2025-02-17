package net.kubeworks.kubedashboard.feature.auth.service;

import lombok.extern.slf4j.Slf4j;
import net.kubeworks.kubedashboard.domain.account.model.Account;
import net.kubeworks.kubedashboard.domain.account.service.AccountService;
import net.kubeworks.kubedashboard.feature.auth.model.AuthContext;
import net.kubeworks.kubedashboard.feature.auth.model.JwtAuthentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthContextService {

    private final JwtService jwtService;
    private final AccountService accountService;

    public AuthContextService(JwtService jwtService, AccountService accountService) {
        this.jwtService = jwtService;
        this.accountService = accountService;
    }

    public AuthContext loadAuthContext() {

        // SecurityContext 에서 인증정보 획득
        SecurityContext securityContext = SecurityContextHolder.getContext();
        JwtAuthentication authentication = (JwtAuthentication) securityContext.getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null || !authentication.isAuthenticated()) {
            return new AuthContext(false, null, null, null, null);
        }

        // AuthContext 로드된적 있다면 그대로 사용
        if (authentication.getAuthContext() != null) {
            return authentication.getAuthContext();
        }

        String username = jwtService.extractSub(authentication.getToken());
        Optional<Account> found = accountService.findByUsername(username);
        if (found.isEmpty()) {
            return new AuthContext(false, null, null, null, null);
        }
        Account account = found.get();
        AuthContext authContext = new AuthContext(true, account.getId(), account.getUid(), account.getUsername(), account.getLastLoginedAt());
        authentication.setAuthContext(authContext);
        return authContext;
    }

}
