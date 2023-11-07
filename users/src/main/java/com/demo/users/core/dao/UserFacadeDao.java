package com.demo.users.core.dao;

import com.demo.users.core.dao.entity.User;
import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;

import java.util.List;
import java.util.Optional;
public interface UserFacadeDao {
  public Optional<User> saveUser(User user);
  public Optional<User> findUserByEmail(String email);
  public Optional<User> findUserById(String userId);
  public Optional<PhoneResponse> savePhone(PhoneDto phoneDto);

  public Optional<List<PhoneResponse>> findUserPhones(String userId);
}
