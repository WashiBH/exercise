package com.demo.users.integration.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class UserResponse {
  private String id;
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private LocalDateTime lastLoginAt;
  private String token;
  private Boolean isActive;
}
