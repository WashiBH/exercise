package com.demo.users.integration;

import com.demo.users.core.dao.entity.User;
import com.demo.users.core.service.UserService;
import com.demo.users.integration.dto.PhoneDto;
import com.demo.users.integration.dto.UserDto;
import com.demo.users.integration.mapper.PhoneResponseToDtoMapper;
import com.demo.users.integration.mapper.PhoneToDtoMapper;
import com.demo.users.integration.mapper.UserToDtoMapper;
import com.demo.users.integration.mapper.UserToEntityMapper;
import com.demo.users.integration.mapper.UserToResponseMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * Clase REST que contiene todos los metodos HTTP.
 */
@RequiredArgsConstructor
@RestController
public class UserRestController {

  private final UserService userService;
  private final UserToEntityMapper userToEntityMapper;
  private final UserToDtoMapper userToDtoMapper;
  private final UserToResponseMapper userToResponseMapper;
  private final PhoneToDtoMapper phoneToDtoMapper;
  private final PhoneResponseToDtoMapper phoneResponseToDtoMapper;

  @PostMapping("/users")
  public ResponseEntity<Map<String, Object>> addUser( @Valid @RequestBody UserDto userDto) {
    /*ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();


    Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
    if (!violations.isEmpty()) {
      System.out.println("Hay errores");
    }*/

    Map<String, Object> response = new HashMap<>();
    User user = userService.saveUser(userToEntityMapper.toMap(userDto));
    this.saveAllPhones(user.getId(), userDto.getPhones());
    response.put("user", userToResponseMapper.toMap(user));
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @DeleteMapping("/users/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
    userService.deleteUser(userId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String userId) {
    Map<String, Object> response = new HashMap<>();
    User user = userService.getUserById(userId);
    UserDto userDto = userToDtoMapper.toMap(user);
    userDto.setPhones(userService.getAllPhonesForUser(userId).stream()
        .map(phoneResponseToDtoMapper::toMap).toList()
    );
    response.put("user", userDto);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/users/{userId}")
  public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String userId, @Valid @RequestBody UserDto userDto) {
    Map<String, Object> response = new HashMap<>();
    User user = userService.updateUser(userId, userToEntityMapper.toMap(userDto));
    this.saveAllPhones(user.getId(), userDto.getPhones());
    response.put("user", userToResponseMapper.toMap(user));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  private void saveAllPhones(String userId, List<PhoneDto> phones) {
    phones.stream()
        .map(phoneToDtoMapper::toMap)
        .forEach(phone -> {
          phone.setUserId(userId);
          userService.savePhone(phone);
        });
  }

}
