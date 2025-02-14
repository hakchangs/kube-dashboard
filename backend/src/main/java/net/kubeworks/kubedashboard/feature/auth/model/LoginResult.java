package net.kubeworks.kubedashboard.feature.auth.model;

public record LoginResult(
        String accessToken,
        long expiresIn
) {
}
