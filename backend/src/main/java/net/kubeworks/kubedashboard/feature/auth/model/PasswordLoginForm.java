package net.kubeworks.kubedashboard.feature.auth.model;

import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;

public record PasswordLoginForm(
        String username, String password
) {
    public PasswordLoginForm {
        if (username == null || username.isEmpty()) {
            throw new BusinessException(AuthErrorCode.EMPTY_USERNAME, "Empty username");
        }
        if (password == null || password.isEmpty()) {
            throw new BusinessException(AuthErrorCode.EMPTY_PASSWORD, "Empty password");
        }
    }
}
