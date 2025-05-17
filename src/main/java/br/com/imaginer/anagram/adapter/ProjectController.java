package br.com.imaginer.anagram.adapter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@Tag(name = "Project")
@RestController
@RequestMapping("/")
public class ProjectController {

  @Value("${spring.application.name}")
  private String applicationName;

  public static record InfoResponse(String developerName, String linkedin, String applicationName) {}

  @Operation(description = "Service info", summary = "Service info")
  @GetMapping
  public InfoResponse index() {
    return new InfoResponse(
      "Rodrigo Mendes Brocchi",
      "https://www.linkedin.com/in/rodrigobrocchi/",
      applicationName
    );
  }

}
