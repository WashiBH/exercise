package com.demo.phones.controller.mapper;

import com.demo.phones.controller.dto.PhoneDto;
import com.demo.phones.entity.Phone;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PhoneToEntityMapper implements Mapper<Phone, PhoneDto>{
  @Override
  public Phone toMap(PhoneDto in) {
    Phone phone = new Phone();
    BeanUtils.copyProperties(in, phone);
    return phone;
  }
}
