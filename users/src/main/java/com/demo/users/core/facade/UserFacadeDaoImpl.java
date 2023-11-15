package com.demo.users.core.facade;

import com.demo.users.core.dao.entity.User;
import com.demo.users.core.dao.http.PhoneFeignClient;
import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;
import com.demo.users.core.repository.UserRepository;
import com.demo.users.error.UniqueConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Repository
public class UserFacadeDaoImpl implements UserFacadeDao {
  private final UserRepository userRepository;
  private final PhoneFeignClient phoneFeignClient;

  @Override
  public Optional<User> saveUser(User user) {
    try {
      return Optional.of(userRepository.save(user));
    } catch (DataIntegrityViolationException ex) {
      throw new UniqueConstraintViolationException("El email ya está en uso.");
    }
  }

  @Override
  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Optional<User> findUserById(String userId) {
    return userRepository.findById(userId);
  }

  @Override
  public Optional<PhoneResponse> savePhone(PhoneDto phoneDto) {

    return Optional.of(phoneFeignClient.savePhone(phoneDto));
  }

  @Override
  public Optional<List<PhoneResponse>> getAllPhonesForUser(String userId) {
    return Optional.of(phoneFeignClient.getAllPhonesForUser(userId));
  }
}
