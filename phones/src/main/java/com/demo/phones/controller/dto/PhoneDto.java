package com.demo.phones.controller.dto;

import lombok.*;
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
