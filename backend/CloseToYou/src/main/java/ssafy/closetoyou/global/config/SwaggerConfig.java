package ssafy.closetoyou.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    private static final String SERVICE_NAME = "Close To You";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = "시각장애인을 위한 스마트 옷장 서비스 API TEST";
    private static final String jwt = "JWT";

    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );

        Server server = new Server();
        server.setUrl("https://i11b201.p.ssafy.io");

        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .servers(List.of(server))
                .addSecurityItem(securityRequirement)
                .components(components);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api/swagger-ui", "/api/swagger-ui.html");
        registry.addRedirectViewController("/api/swagger-ui/", "/api/swagger-ui.html");
    }

    private Info apiInfo() {
        return new Info()
                .version(API_VERSION)
                .description(API_DESCRIPTION)
                .title(SERVICE_NAME);
    }
}