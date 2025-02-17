package net.kubeworks.kubedashboard.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import net.kubeworks.kubedashboard.feature.auth.service.JwtService;

@OpenAPIDefinition(
        info = @Info(title = "kube-dashboard", version = "1.0",
        description = "k8s 대시보드", contact = @Contact(name = "hakchangs")),
        security = {@SecurityRequirement(name = "cookieAuth")})
@SecuritySchemes({
        @SecurityScheme(name = "cookieAuth", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.COOKIE, paramName = JwtService.NAME),
})
public class SwaggerConfig {

    //Swagger 속성: https://springdoc.org/properties.html

}
