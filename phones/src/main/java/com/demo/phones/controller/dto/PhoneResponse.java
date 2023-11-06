package com.demo.phones.controller.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponse {
  private String number;
  private String cityCode;
  private String countryCode;
}
