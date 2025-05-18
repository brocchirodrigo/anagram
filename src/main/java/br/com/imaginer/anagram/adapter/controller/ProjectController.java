package br.com.imaginer.anagram.adapter.controller;

import br.com.imaginer.anagram.application.usecase.ApplicationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Project")
@RestController
@RequestMapping("/")
public class ProjectController {

  private final ApplicationUseCase applicationUseCase;

  public ProjectController(ApplicationUseCase applicationUseCase) {
      this.applicationUseCase = applicationUseCase;
  }

  @GetMapping
  public ResponseEntity<ApplicationUseCase.InfoResponse> project() {
      return ResponseEntity.ok(applicationUseCase.execute());
  }

}
