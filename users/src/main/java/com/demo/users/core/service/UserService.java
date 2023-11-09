package com.demo.users.core.service;

import com.demo.users.core.dao.entity.User;
import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;

import java.util.List;
public interface UserService {
  public User saveUser(User user);
  public User updateUser(String userId, User user);
  public User getUserById(String userId);
  public void deleteUser(String userId);
  public PhoneResponse savePhone(PhoneDto phoneDto);
  public List<PhoneResponse> getAllPhonesForUser(String userId);
}
