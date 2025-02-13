package net.kubeworks.kubedashboard.feature.account.model;

import java.time.LocalDateTime;

public record SignUpResult(
        String username, LocalDateTime createdAt, LocalDateTime modifiedAt
) {
}
