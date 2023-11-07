package com.demo.users.integration;

import com.demo.users.api.UsersApi;
import com.demo.users.core.dao.entity.User;
import com.demo.users.core.service.JwtService;
import com.demo.users.core.service.UserService;
import com.demo.users.dto.UserDto;
import com.demo.users.integration.dto.UserResponse;
import com.demo.users.integration.mapper.PhoneToDtoMapper;
import com.demo.users.integration.mapper.UserToEntityMapper;
import com.demo.users.integration.mapper.UserToResponseMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRestController implements UsersApi {

  private final UserService userService;
  private final UserToEntityMapper userToEntityMapper;
  private final UserToResponseMapper userToResponseMapper;
  private final PhoneToDtoMapper phoneToDtoMapper;

  public UserRestController(UserService userService, UserToEntityMapper userToEntityMapper, UserToResponseMapper userToResponseMapper, PhoneToDtoMapper phoneToDtoMapper) {
    this.userService = userService;
    this.userToEntityMapper = userToEntityMapper;
    this.userToResponseMapper = userToResponseMapper;
    this.phoneToDtoMapper = phoneToDtoMapper;
  }


  @Override
  public ResponseEntity<Map<String, Object>> addUser(@Valid UserDto userDto) {
    Map<String, Object> response = new HashMap<>();
    User user = userService.saveUser(userToEntityMapper.toMap(userDto));
    userDto.getPhones().stream()
      .map(phoneToDtoMapper::toMap)
      .forEach(phone -> {
        phone.setUserId(user.getId());
        userService.savePhone(phone);
      });
    response.put("user", userToResponseMapper.toMap(user));
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @Override
  public ResponseEntity<Void> deleteUser(String userId) {
    userService.deleteUser(userId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<Map<String, Object>> getUserById(String userId) {
    return UsersApi.super.getUserById(userId);
  }

  @Override
  public ResponseEntity<Map<String, Object>> updateUser(String userId, UserDto userDto) {
    Map<String, Object> response = new HashMap<>();
    User user = userService.updateUser(userId, userToEntityMapper.toMap(userDto));
    userDto.getPhones().stream()
      .map(phoneToDtoMapper::toMap)
      .forEach(phone -> {
        phone.setUserId(user.getId());
        userService.savePhone(phone);
      });
    response.put("user", userToResponseMapper.toMap(user));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }


}
