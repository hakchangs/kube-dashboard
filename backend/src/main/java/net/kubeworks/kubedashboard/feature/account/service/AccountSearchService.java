package net.kubeworks.kubedashboard.feature.account.service;

import net.kubeworks.kubedashboard.domain.account.model.Account;
import net.kubeworks.kubedashboard.domain.account.service.AccountService;
import net.kubeworks.kubedashboard.feature.account.model.AccountSearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountSearchService {

    private final AccountService accountService;

    public AccountSearchService(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<AccountSearchResult> searchAll() {
        List<Account> found = accountService.searchAll();
        return found.stream().map(entity -> new AccountSearchResult(
                entity.getUsername(), entity.getCreatedAt(), entity.getModifiedAt()
        )).toList();
    }
}
