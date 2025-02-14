package net.kubeworks.kubedashboard.feature.auth.model;

import java.time.ZonedDateTime;

public record SignUpResult(
        String username, ZonedDateTime createdAt, ZonedDateTime modifiedAt
) {
}
