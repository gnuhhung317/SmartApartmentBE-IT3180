package com.hust.smart_apartment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://127.0.0.1:8080");
        devServer.setDescription("Server URL in Development environment");

        Info info = new Info()
                .title("API của anh Hùng dz")
                .version("1.0")
                .description("This API exposes endpoints to manage demo.").termsOfService("https://www.pasanabeysekara.com");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
