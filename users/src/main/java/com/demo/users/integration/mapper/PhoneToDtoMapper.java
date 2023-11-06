package com.demo.users.integration.mapper;

import com.demo.users.core.dao.http.dto.PhoneDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PhoneToDtoMapper implements Mapper<PhoneDto, Object>{
  @Override
  public PhoneDto toMap(Object dto) {
    PhoneDto phoneDto = new PhoneDto();
    BeanUtils.copyProperties(dto, phoneDto);
    return phoneDto;
  }
}
