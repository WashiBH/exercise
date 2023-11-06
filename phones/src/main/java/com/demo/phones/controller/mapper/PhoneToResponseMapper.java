package com.demo.phones.controller.mapper;

import com.demo.phones.controller.dto.PhoneResponse;
import com.demo.phones.entity.Phone;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PhoneToResponseMapper implements Mapper<PhoneResponse, Phone>{
  @Override
  public PhoneResponse toMap(Phone in) {
    PhoneResponse response = new PhoneResponse();
    BeanUtils.copyProperties(in, response);
    return response;
  }
}
