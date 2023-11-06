package com.demo.users.error;

public class UserEmailFoundException extends RuntimeException{
  public UserEmailFoundException(String message) {
    super(message);
  }

}
