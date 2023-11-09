package com.demo.users.core.service;

import com.demo.users.core.dao.entity.User;
import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;
import com.demo.users.core.facade.UserFacadeDao;
import com.demo.users.error.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

  private final UserFacadeDao userDao;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  @Override
  public User saveUser(User user) {
    LocalDateTime currentTime = LocalDateTime.now();
    user.setCreatedAt(currentTime);
    user.setLastLoginAt(currentTime);
    user.setIsActive(true);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setToken(jwtService.generateToken(user));

    Optional<User> userOptional = userDao.saveUser(user);

    if (userOptional.isEmpty()){
      throw new UserException("Algo salió mal, espere un momento por favor.");
    }

    return userOptional.get();
  }

  @Override
  public User updateUser(String userId, User user) {
    Optional<User> userOptional = userDao.findUserById(userId);
    if(userOptional.isEmpty()){
      throw new UserNotFoundException("El usuario con ID "+userId+" no fue encontrado.");
    }

    User userUpdate = userOptional.get();
    userUpdate.setName(user.getName());
    userUpdate.setModifiedAt(LocalDateTime.now());

    userOptional = userDao.saveUser(userUpdate);

    if (userOptional.isEmpty()){
      throw new UserException("Algo salió mal, espere un momento por favor.");
    }

    return userOptional.get();
  }

  @Override
  public User getUserById(String userId) {
    Optional<User> userOptional = userDao.findUserById(userId);
    if (userOptional.isEmpty()){
      throw new UserNotFoundException("El usuario con ID "+userId+" no fue encontrado.");
    }
    return userOptional.get();
  }

  @Override
  public void deleteUser(String userId) {
    Optional<User> userOptional = userDao.findUserById(userId);
    if(userOptional.isEmpty()){
      throw new UserNotFoundException("El usuario con ID "+userId+" no fue encontrado.");
    }
    User userDelete = userOptional.get();
    userDelete.setModifiedAt(LocalDateTime.now());
    userDelete.setIsActive(false);
    userOptional = userDao.saveUser(userDelete);
    if(userOptional.isEmpty()){
      throw new UserException("Algo salió mal, espere un momento por favor.");
    }
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

  @Override
  public List<PhoneResponse> getAllPhonesForUser(String userId) {
    return userDao.getAllPhonesForUser(userId).orElse(List.of());
  }

  private PhoneResponse fallBackSavePhone(PhoneException e) {
    log.info(e.getMessage());
    return new PhoneResponse();
  }
}
