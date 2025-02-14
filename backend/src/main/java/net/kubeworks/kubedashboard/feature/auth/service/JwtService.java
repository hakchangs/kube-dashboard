package net.kubeworks.kubedashboard.feature.auth.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import net.kubeworks.kubedashboard.feature.auth.model.AuthErrorCode;
import net.kubeworks.kubedashboard.feature.auth.model.JwtGenerationForm;
import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    public static final String NAME = "JWT";

    private final String secret = """
            secret-secret-secret-secret-secret-secret-secret-secret-secret-secret-secret
            secret-secret-secret-secret-secret-secret-secret-secret-secret-secret-secret
            secret-secret-secret-secret-secret-secret-secret-secret-secret-secret-secret
            """;
    private final JWSSigner signer;
    private final JWSVerifier verifier;

    public JwtService() throws JOSEException {
        this.signer = new MACSigner(secret);
        this.verifier = new MACVerifier(secret);
    }

    public String generate(JwtGenerationForm form) {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(form.username())
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + form.expirationMillis() - 1))
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        try {
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            log.error("failed to sign jwt", e);
            throw new BusinessException(AuthErrorCode.SIGN_FAILED, e.getMessage());
        }
        return signedJWT.serialize();
    }

    public boolean verify(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.verify(verifier);
        } catch (JOSEException e) {
            log.error("failed to verify jwt", e);
            throw new BusinessException(AuthErrorCode.VERIFY_FAILED, e.getMessage());
        } catch (ParseException e) {
            log.error("failed to parse token", e);
            throw new BusinessException(AuthErrorCode.PARSE_FAILED, e.getMessage());
        }
    }

    public boolean check(String token) {
        try {

            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expirationTime.before(new Date())) {
                return false;
            }
            return verify(token);

        } catch (ParseException e) {
            log.error("failed to parse token", e);
            throw new BusinessException(AuthErrorCode.PARSE_FAILED, e.getMessage());
        }
    }

    public String extractSub(String token) {
        try {

            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();

        } catch (ParseException e) {
            log.error("failed to parse token", e);
            throw new BusinessException(AuthErrorCode.PARSE_FAILED, e.getMessage());
        }
    }
}
