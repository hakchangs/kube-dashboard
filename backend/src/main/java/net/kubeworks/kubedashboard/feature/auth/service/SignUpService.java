package net.kubeworks.kubedashboard.feature.auth.service;

import net.kubeworks.kubedashboard.domain.account.model.AccountEntity;
import net.kubeworks.kubedashboard.domain.account.model.AddAccount;
import net.kubeworks.kubedashboard.domain.account.service.AccountService;
import net.kubeworks.kubedashboard.feature.auth.model.AdminSignUpForm;
import net.kubeworks.kubedashboard.feature.auth.model.SelfSignUpForm;
import net.kubeworks.kubedashboard.feature.auth.model.SignUpResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    public SignUpResult signUpBySelf(SelfSignUpForm form) {
        String encryptedPassword = passwordEncoder.encode(form.password());
        AccountEntity entity = accountService.add(new AddAccount(form.username(), encryptedPassword, true));
        return new SignUpResult(entity.getUsername(), entity.getCreatedAt(), entity.getModifiedAt());
    }

    public SignUpResult signUpByAdmin(AdminSignUpForm form) {
        AccountEntity entity = accountService.add(new AddAccount(form.username(), form.password(), true));
        return new SignUpResult(entity.getUsername(), entity.getCreatedAt(), entity.getModifiedAt());
    }

}
