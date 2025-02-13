package net.kubeworks.kubedashboard.feature.account.service;

import net.kubeworks.kubedashboard.domain.account.model.AccountEntity;
import net.kubeworks.kubedashboard.domain.account.model.AddAccount;
import net.kubeworks.kubedashboard.domain.account.service.AccountService;
import net.kubeworks.kubedashboard.feature.account.model.AdminSignUpForm;
import net.kubeworks.kubedashboard.feature.account.model.SelfSignUpForm;
import net.kubeworks.kubedashboard.feature.account.model.SignUpResult;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final AccountService accountService;

    public SignUpService(AccountService accountService) {
        this.accountService = accountService;
    }

    public SignUpResult signUpBySelf(SelfSignUpForm form) {
        AccountEntity entity = accountService.add(new AddAccount(form.username(), form.password()));
        return new SignUpResult(entity.getUsername(), entity.getCreated(), entity.getModified());

    }

    public SignUpResult signUpByAdmin(AdminSignUpForm form) {
        AccountEntity entity = accountService.add(new AddAccount(form.username(), form.password()));
        return new SignUpResult(entity.getUsername(), entity.getCreated(), entity.getModified());
    }

}
