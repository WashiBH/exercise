package com.demo.users.integration;

import com.demo.users.api.AuthApi;
import com.demo.users.core.dao.entity.User;
import com.demo.users.core.service.AuthenticationService;
import com.demo.users.dto.AuthRequest;
import com.demo.users.integration.mapper.UserToResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class AuthenticationRestController implements AuthApi {

  private final AuthenticationService authenticationService;
  private final UserToResponseMapper userToResponseMapper;

  public AuthenticationRestController(AuthenticationService authenticationService, UserToResponseMapper userToResponseMapper) {
    this.authenticationService = authenticationService;
    this.userToResponseMapper = userToResponseMapper;
  }

  @Override
  public ResponseEntity<Map<String, Object>> signIn(AuthRequest authRequest) {
    Map<String, Object> response = new HashMap<>();
    User user = authenticationService.signIn(authRequest);
    response.put("user", userToResponseMapper.toMap(user));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
