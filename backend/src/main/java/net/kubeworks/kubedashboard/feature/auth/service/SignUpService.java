package net.kubeworks.kubedashboard.feature.auth.service;

import net.kubeworks.kubedashboard.domain.account.model.Account;
import net.kubeworks.kubedashboard.domain.account.model.AddAccount;
import net.kubeworks.kubedashboard.domain.account.service.AccountService;
import net.kubeworks.kubedashboard.feature.auth.model.AdminSignUpForm;
import net.kubeworks.kubedashboard.feature.auth.model.SelfSignUpForm;
import net.kubeworks.kubedashboard.feature.auth.model.SignUpResult;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final AccountService accountService;

    public SignUpService(AccountService accountService) {
        this.accountService = accountService;
    }

    public SignUpResult signUpBySelf(SelfSignUpForm form) {
        Account account = accountService.add(new AddAccount(form.username(), form.password(), true));
        return new SignUpResult(account.getUsername(), account.getCreatedAt(), account.getModifiedAt());
    }

    public SignUpResult signUpByAdmin(AdminSignUpForm form) {
        Account entity = accountService.add(new AddAccount(form.username(), form.password(), true));
        return new SignUpResult(entity.getUsername(), entity.getCreatedAt(), entity.getModifiedAt());
    }

}
