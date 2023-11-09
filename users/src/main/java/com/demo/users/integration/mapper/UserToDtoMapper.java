package com.demo.users.integration.mapper;

import com.demo.users.core.dao.entity.User;
import com.demo.users.integration.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
@Component
public class UserToDtoMapper implements Mapper<UserDto, User>{
  @Override
  public UserDto toMap(User in) {
    UserDto dto = new UserDto();
    BeanUtils.copyProperties(in, dto);
    return dto;
  }
}
