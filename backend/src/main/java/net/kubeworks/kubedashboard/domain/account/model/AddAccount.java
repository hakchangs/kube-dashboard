package net.kubeworks.kubedashboard.domain.account.model;

import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;

import java.time.ZonedDateTime;
import java.util.UUID;

public record AddAccount(
        String username, String password, boolean enabled
) {

    public AddAccount {
        if (username == null || username.isEmpty()) {
            throw new BusinessException(AccountErrorCode.EMPTY_USERNAME, "'username' cannot be empty");
        }
        if (password == null || password.isEmpty()) {
            throw new BusinessException(AccountErrorCode.EMPTY_PASSWORD, "'password' cannot be empty");
        }
    }

    public Account toEntity() {
        Account entity = new Account();
        entity.uid = UUID.randomUUID().toString();
        entity.username = username;
        entity.password = password;
        entity.enabled = enabled;
        entity.createdAt = ZonedDateTime.now();
        entity.modifiedAt = ZonedDateTime.now();
        return entity;
    }

}
