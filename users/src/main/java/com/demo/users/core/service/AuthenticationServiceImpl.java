package com.demo.users.core.service;

import com.demo.users.core.dao.UserFacadeDao;
import com.demo.users.core.dao.entity.User;
import com.demo.users.dto.AuthRequest;
import com.demo.users.error.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
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
  public User login(AuthRequest authRequest) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authRequest.getUsername(),
        authRequest.getPassword()
      )
    );
    Optional<User> userOptional = userDao.findUserByEmail(authRequest.getUsername());
    if(userOptional.isEmpty()) {
      throw new AuthenticationException("El username o password es incorrecto.");
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
