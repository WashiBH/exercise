package com.demo.users.core.service;

import com.demo.users.core.dao.UserFacadeDao;
import com.demo.users.core.dao.entity.User;
import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;
import com.demo.users.error.PhoneException;
import com.demo.users.error.UserEmailFoundException;
import com.demo.users.error.UserException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

  private final UserFacadeDao userDao;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserFacadeDao userDao, PasswordEncoder passwordEncoder ) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User saveUser(User user) {
    if(userDao.findUserByEmail(user.getEmail()).isPresent()){
      throw new UserEmailFoundException("El correo ya está registrado.");
    }
    user.setCreatedAt(LocalDateTime.now());
    user.setLastLoginAt(user.getCreatedAt());
    user.setIsActive(true);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Optional<User> userOptional = userDao.saveUser(user);
    if (userOptional.isEmpty()){
      throw new UserException("Algo salió mal, espere un momento por favor.");
    }
    user = userOptional.get();
    return user;
  }

  @CircuitBreaker(name = "backendPhones", fallbackMethod = "fallBackSavePhone")
  @Override
  public PhoneResponse savePhone(PhoneDto phoneDto) {
    Optional<PhoneResponse> phoneOptional = userDao.savePhone(phoneDto);
    if (phoneOptional.isEmpty()){
      throw new PhoneException("Algo salió mal, espere un momento por favor.");
    }
    return phoneOptional.get();
  }

  private PhoneResponse fallBackSavePhone(PhoneException e) {
    log.info(e.getMessage());
    return new PhoneResponse();
  }
}
