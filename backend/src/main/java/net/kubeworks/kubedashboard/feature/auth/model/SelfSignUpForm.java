package net.kubeworks.kubedashboard.feature.auth.model;

import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;

public record SelfSignUpForm(
        String username, String password
) {

    public SelfSignUpForm {
        if (username == null || username.isEmpty()) {
            throw new BusinessException(AuthErrorCode.EMPTY_USERNAME, "'username' cannot be empty");
        }
        if (password == null || password.isEmpty()) {
            throw new BusinessException(AuthErrorCode.EMPTY_PASSWORD, "'password' cannot be empty");
        }
    }

}
