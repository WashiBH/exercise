package com.demo.users.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
  private String number;
  private String cityCode;
  private String countryCode;
}
