package com.demo.users.core.service;

import com.demo.users.core.dao.entity.User;
import com.demo.users.dto.AuthRequest;
public interface AuthenticationService {
  public User signIn(AuthRequest authRequest);
}
