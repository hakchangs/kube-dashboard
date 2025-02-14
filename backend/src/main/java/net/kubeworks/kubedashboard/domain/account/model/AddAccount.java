package net.kubeworks.kubedashboard.domain.account.model;

import java.time.ZonedDateTime;
import java.util.UUID;

public record AddAccount(
        String username, String password, boolean enabled
) {

    public AccountEntity toEntity() {
        AccountEntity entity = new AccountEntity();
        entity.uid = UUID.randomUUID().toString();
        entity.username = username;
        entity.password = password;
        entity.enabled = enabled;
        entity.createdAt = ZonedDateTime.now();
        entity.modifiedAt = ZonedDateTime.now();
        return entity;
    }

}
