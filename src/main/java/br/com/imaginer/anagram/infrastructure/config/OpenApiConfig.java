package br.com.imaginer.anagram.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info()
            .title("Anagram")
            .description("Anagram API Service")
            .version("1.0.0"))
        .servers(List.of(
            new Server().url("http://localhost:8080").description("Localhost (Dev)")
        ));
  }

}
