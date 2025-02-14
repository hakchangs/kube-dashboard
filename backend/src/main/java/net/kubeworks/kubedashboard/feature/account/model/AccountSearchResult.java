package net.kubeworks.kubedashboard.feature.account.model;

import java.time.ZonedDateTime;

public record AccountSearchResult(
        String username,
        ZonedDateTime createdAt,
        ZonedDateTime modifiedAt
) {
}
