package br.com.imaginer.anagram.domain.model.exceptions;

public class AnagramException extends RuntimeException {
  public AnagramException(String message) {
    super(message);
  }
}
