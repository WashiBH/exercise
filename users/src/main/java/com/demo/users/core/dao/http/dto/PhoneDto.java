package com.demo.users.core.dao.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
  private String userId;
  private String number;
  private String cityCode;
  private String countryCode;
}
