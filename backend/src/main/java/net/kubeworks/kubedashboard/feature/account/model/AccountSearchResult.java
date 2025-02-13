package net.kubeworks.kubedashboard.feature.account.model;

import java.time.LocalDateTime;

public record AccountSearchResult(
        String username,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
