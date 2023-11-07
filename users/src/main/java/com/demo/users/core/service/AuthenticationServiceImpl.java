package com.demo.users.core.service;

import com.demo.users.core.dao.UserFacadeDao;
import com.demo.users.core.dao.entity.User;
import com.demo.users.dto.AuthRequest;
import com.demo.users.error.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

  private final JwtService jwtService;
  private final UserFacadeDao userDao;
  private final AuthenticationManager authenticationManager;

  public AuthenticationServiceImpl(JwtService jwtService, UserFacadeDao userDao, AuthenticationManager authenticationManager) {
    this.jwtService = jwtService;
    this.userDao = userDao;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public User signIn(AuthRequest authRequest) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authRequest.getUsername(),
        authRequest.getPassword()
      )
    );
    Optional<User> userOptional = userDao.findUserByEmail(authRequest.getUsername());
    if(userOptional.isEmpty()) {
      throw new AuthenticationException("Algo salió mal, espere un momento por favor.");
    }
    User user = userOptional.get();
    user.setLastLoginAt(LocalDateTime.now());
    user.setToken(jwtService.generateToken(user));
    Optional<User> userUpdateOptional = userDao.saveUser(user);
    if(userUpdateOptional.isEmpty()) {
      throw new AuthenticationException("Algo salio mal al actualizar el último login.");
    }
    return userUpdateOptional.get();
  }
}
