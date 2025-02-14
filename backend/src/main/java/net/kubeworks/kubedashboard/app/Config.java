package net.kubeworks.kubedashboard.app;

import net.kubeworks.kubedashboard.app.config.SecurityConfig;
import net.kubeworks.kubedashboard.app.config.SwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        SecurityConfig.class,
        SwaggerConfig.class,
})
public class Config {
}
