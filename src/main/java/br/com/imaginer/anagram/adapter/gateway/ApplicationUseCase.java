package br.com.imaginer.anagram.adapter.gateway;

public interface ApplicationUseCase {
  InfoResponse execute();

  record InfoResponse(String developerName,
                      String linkedin,
                      String applicationName) {}
}
