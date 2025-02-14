package net.kubeworks.kubedashboard.feature.auth.model;

public record JwtGenerationForm(
        String username,
        long expirationMillis
) {
}
