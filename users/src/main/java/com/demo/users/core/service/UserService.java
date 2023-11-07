package com.demo.users.core.service;

import com.demo.users.core.dao.entity.User;
import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;
public interface UserService {
  public User saveUser(User user);
  public User updateUser(String userId, User user);
  public void deleteUser(String userId);

  public PhoneResponse savePhone(PhoneDto phoneDto);
}
