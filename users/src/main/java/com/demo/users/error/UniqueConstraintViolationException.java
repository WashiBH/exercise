package com.demo.users.error;

public class UniqueConstraintViolationException extends RuntimeException {
  public UniqueConstraintViolationException(String message) {
    super(message);
  }
}
