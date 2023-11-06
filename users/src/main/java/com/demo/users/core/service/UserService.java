package com.demo.users.core.service;

import com.demo.users.core.dao.entity.User;
import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;
public interface UserService {
  public User saveUser(User user);

  public PhoneResponse savePhone(PhoneDto phoneDto);
}
