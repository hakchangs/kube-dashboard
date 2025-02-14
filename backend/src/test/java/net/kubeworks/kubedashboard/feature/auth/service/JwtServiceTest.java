package net.kubeworks.kubedashboard.feature.auth.service;

import com.nimbusds.jose.JOSEException;
import net.kubeworks.kubedashboard.feature.auth.model.JwtGenerationForm;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    @Test
    void generateAndCheck() throws JOSEException, ParseException {

        JwtService jwtService = new JwtService();

        String token = jwtService.generate(new JwtGenerationForm("hakchangs", Duration.ofMinutes(10).toMillis()));
        System.out.println("token: " + token);
        assertNotNull(token);

        boolean checked = jwtService.check(token);
        System.out.println("checked: " + checked);
        assertTrue(checked);
    }

}