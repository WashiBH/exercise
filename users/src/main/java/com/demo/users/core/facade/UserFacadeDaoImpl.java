package com.demo.users.core.facade;

import com.demo.users.core.dao.entity.User;
import com.demo.users.core.dao.http.PhoneFeignClient;
import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;
import com.demo.users.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@Slf4j
public class UserFacadeDaoImpl implements UserFacadeDao {
  private final UserRepository userRepository;
  private final PhoneFeignClient phoneFeignClient;

  public UserFacadeDaoImpl(UserRepository userRepository, PhoneFeignClient phoneFeignClient) {
    this.userRepository = userRepository;
    this.phoneFeignClient = phoneFeignClient;
  }

  @Override
  public Optional<User> saveUser(User user) {
    return Optional.of(userRepository.save(user));
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
