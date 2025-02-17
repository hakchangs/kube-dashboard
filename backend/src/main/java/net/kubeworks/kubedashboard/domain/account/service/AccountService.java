package net.kubeworks.kubedashboard.domain.account.service;

import net.kubeworks.kubedashboard.domain.account.model.Account;
import net.kubeworks.kubedashboard.domain.account.model.AccountErrorCode;
import net.kubeworks.kubedashboard.domain.account.model.AddAccount;
import net.kubeworks.kubedashboard.domain.account.repository.AccountRepository;
import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @ErrorCode
     * <pre>
     * ACCOUNT_EMPTY_USERNAME
     * ACCOUNT_EMPTY_PASSWORD
     * ACCOUNT_DUPLICATE_USERNAME
     * </pre>
     */
    public Account add(AddAccount form) {

        if (!isUsernameAvailable(form.username())) {
            throw new BusinessException(AccountErrorCode.DUPLICATE_USERNAME, "duplicate username");
        }
        Account entity = form.toEntity();
        String encryptedPassword = passwordEncoder.encode(form.password());
        entity.changePassword(encryptedPassword);

        return accountRepository.save(entity);
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public List<Account> searchAll() {
        return accountRepository.findAll();
    }

    public boolean isUsernameAvailable(String username) {
        return !accountRepository.existsByUsername(username);
    }

    public void markLogin(Account account) {
        account.changeLastLoginedAt(ZonedDateTime.now());
        accountRepository.save(account);
    }

}
