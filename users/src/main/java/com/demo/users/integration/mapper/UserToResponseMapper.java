package com.demo.users.integration.mapper;

import com.demo.users.core.dao.entity.User;
import com.demo.users.integration.dto.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserToResponseMapper implements Mapper<UserResponse, User>{
  @Override
  public UserResponse toMap(User user) {
    UserResponse response = new UserResponse();
    BeanUtils.copyProperties(user, response);
    return response;
  }
}
