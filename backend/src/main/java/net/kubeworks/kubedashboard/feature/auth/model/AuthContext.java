package net.kubeworks.kubedashboard.feature.auth.model;

import java.time.ZonedDateTime;

public record AuthContext(
        boolean isAuthenticated,
        Long id,
        String uid,
        String username,
        ZonedDateTime lastLoginedAt
) {
}
