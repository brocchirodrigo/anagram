package br.com.imaginer.anagram.domain.exceptions;

public class AnagramException extends RuntimeException {
  public AnagramException(String message) {
    super(message);
  }
}
