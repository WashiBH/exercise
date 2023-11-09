package com.demo.users.integration.mapper;

import com.demo.users.core.dao.http.dto.PhoneResponse;
import com.demo.users.integration.dto.PhoneDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
@Component
public class PhoneResponseToDtoMapper implements Mapper<PhoneDto, PhoneResponse>{

  @Override
  public PhoneDto toMap(PhoneResponse in) {
    PhoneDto phoneDto = new PhoneDto();
    BeanUtils.copyProperties(in, phoneDto);
    return phoneDto;
  }
}
