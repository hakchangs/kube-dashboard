package net.kubeworks.kubedashboard.domain.account.service;

import net.kubeworks.kubedashboard.domain.account.model.AccountEntity;
import net.kubeworks.kubedashboard.domain.account.model.AccountErrorCode;
import net.kubeworks.kubedashboard.domain.account.model.AddAccount;
import net.kubeworks.kubedashboard.domain.account.repository.AccountRepository;
import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity add(AddAccount form) {
        if (!isUsernameAvailable(form.username())) {
            throw new BusinessException(AccountErrorCode.DUPLICATE_USERNAME ,"duplicate username");
        }
        return accountRepository.save(form.toEntity());
    }

    public Optional<AccountEntity> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public List<AccountEntity> searchAll() {
        return accountRepository.findAll();
    }

    public boolean isUsernameAvailable(String username) {
        return !accountRepository.existsByUsername(username);
    }

    public void markLogin(AccountEntity account) {
        account.changeLastLoginedAt(ZonedDateTime.now());
        accountRepository.save(account);
    }

}
