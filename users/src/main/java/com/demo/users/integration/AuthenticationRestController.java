package com.demo.users.integration;

import com.demo.users.core.dao.entity.User;
import com.demo.users.core.service.AuthenticationService;
import com.demo.users.integration.dto.AuthRequest;
import com.demo.users.integration.mapper.UserToResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthenticationRestController {

  private final AuthenticationService authenticationService;
  private final UserToResponseMapper userToResponseMapper;

  @PostMapping("/auth/sign-in")
  public ResponseEntity<Map<String, Object>> signIn(@Valid @RequestBody AuthRequest authRequest) {
    Map<String, Object> response = new HashMap<>();
    User user = authenticationService.signIn(authRequest);
    response.put("user", userToResponseMapper.toMap(user));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
