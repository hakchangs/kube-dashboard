package net.kubeworks.kubedashboard.feature.auth.service;

import net.kubeworks.kubedashboard.domain.account.model.Account;
import net.kubeworks.kubedashboard.domain.account.service.AccountService;
import net.kubeworks.kubedashboard.feature.auth.model.JwtGenerationForm;
import net.kubeworks.kubedashboard.feature.auth.model.AuthErrorCode;
import net.kubeworks.kubedashboard.feature.auth.model.PasswordLoginForm;
import net.kubeworks.kubedashboard.feature.auth.model.LoginResult;
import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class PasswordLoginService {

    private final Duration expiration = Duration.ofMinutes(10);
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    public PasswordLoginService(JwtService jwtService, PasswordEncoder passwordEncoder, AccountService accountService) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    /**
     * @ErrorCode
     * <pre>
     * ACCOUNT_EMPTY_USERNAME
     * ACCOUNT_EMPTY_PASSWORD
     * ACCOUNT_NOT_EXISTS_ACCOUNT
     * ACCOUNT_INCORRECT_PASSWORD
     * </pre>
     */
    public LoginResult login(PasswordLoginForm form) {

        // 계정 존재여부 확인
        Optional<Account> found = accountService.findByUsername(form.username());
        if (found.isEmpty()) {
            throw new BusinessException(AuthErrorCode.NOT_EXISTS_ACCOUNT, "Account does not exist");
        }

        // 패스워드 검증
        Account account = found.get();
        if (!passwordEncoder.matches(form.password(), account.getPassword())) {
            throw new BusinessException(AuthErrorCode.INCORRECT_PASSWORD, "Incorrect password");
        }

        String token = jwtService.generate(new JwtGenerationForm(form.username(), expiration.toMillis()));
        accountService.markLogin(account);
        return new LoginResult(token, expiration.toSeconds());
    }

}
