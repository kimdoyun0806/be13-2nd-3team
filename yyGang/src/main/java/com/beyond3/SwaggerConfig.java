package com.beyond3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.ErrorResponse;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "yyGang Project",
                description = "yyGang Project Service API",
                version = "v1.0.0"
        )
)
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components());
    }

}