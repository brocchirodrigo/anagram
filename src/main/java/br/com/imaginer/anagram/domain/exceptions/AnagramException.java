package br.com.imaginer.anagram.domain.exceptions;

public class AnagramException extends IllegalArgumentException {
  public AnagramException(String message) {
    super(message);
  }
}
