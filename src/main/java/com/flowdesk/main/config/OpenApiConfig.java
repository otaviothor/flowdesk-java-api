package com.flowdesk.main.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI flowdeskOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Flowdesk API")
                        .description("Backend-first issue tracking system for teams")
                        .version("1.0.0"));
    }
}
