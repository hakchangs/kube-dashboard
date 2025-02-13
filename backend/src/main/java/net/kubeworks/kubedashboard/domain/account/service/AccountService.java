package net.kubeworks.kubedashboard.domain.account.service;

import net.kubeworks.kubedashboard.domain.account.model.AccountEntity;
import net.kubeworks.kubedashboard.domain.account.model.AddAccount;
import net.kubeworks.kubedashboard.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity add(AddAccount addAccount) {

        AccountEntity newEntity = new AccountEntity();
        newEntity.setUsername(addAccount.username());
        newEntity.setPassword(addAccount.password());
        newEntity.setCreated(LocalDateTime.now());
        newEntity.setModified(LocalDateTime.now());

        return accountRepository.save(newEntity);
    }

    public List<AccountEntity> searchAll() {
        return accountRepository.findAll();
    }

}
