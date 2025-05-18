package br.com.imaginer.anagram.application.usecase;

import br.com.imaginer.anagram.adapter.gateway.ApplicationUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUseCaseImpl implements ApplicationUseCase {

  @Value("${spring.application.name}")
  private String applicationName;

  @Override
  public InfoResponse execute() {
    return new InfoResponse(
        "Rodrigo Mendes Brocchi",
        "https://www.linkedin.com/in/rodrigobrocchi/",
        applicationName
    );
  }
}
