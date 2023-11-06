package com.demo.users.integration.mapper;

import com.demo.users.core.dao.entity.User;
import com.demo.users.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserToEntityMapper implements Mapper<User, UserDto>{
  @Override
  public User toMap(UserDto dto) {
    User user = new User();
    BeanUtils.copyProperties(dto, user);
    return user;
  }

}
