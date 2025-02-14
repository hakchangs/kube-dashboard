package net.kubeworks.kubedashboard.feature.auth.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private final String principal;
    private final Object credentials;

    public JwtAuthentication(String principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getToken() {
        return principal;
    }

    public void setAuthContext(AuthContext authContext) {
        super.setDetails(authContext);
    }

    public AuthContext getAuthContext() {
        return (AuthContext) getDetails();
    }
}
