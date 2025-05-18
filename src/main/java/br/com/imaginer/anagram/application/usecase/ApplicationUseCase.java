package br.com.imaginer.anagram.application.usecase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUseCase {

  @Value("${spring.application.name}")
  private String applicationName;

  public static record InfoResponse(String developerName, String linkedin, String applicationName) {}

  public InfoResponse execute() {
    return new ApplicationUseCase.InfoResponse(
        "Rodrigo Mendes Brocchi",
        "https://www.linkedin.com/in/rodrigobrocchi/",
        applicationName
    );
  }
}
