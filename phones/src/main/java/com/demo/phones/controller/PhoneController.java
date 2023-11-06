package com.demo.phones.controller;

import com.demo.phones.controller.dto.PhoneDto;
import com.demo.phones.controller.dto.PhoneResponse;
import com.demo.phones.controller.mapper.PhoneToEntityMapper;
import com.demo.phones.controller.mapper.PhoneToResponseMapper;
import com.demo.phones.entity.Phone;
import com.demo.phones.service.PhoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
public class PhoneController {

  private final PhoneService phoneService;
  private final PhoneToEntityMapper phoneToEntityMapper;
  private final PhoneToResponseMapper phoneToResponseMapper;

  public PhoneController(PhoneService phoneService, PhoneToEntityMapper phoneToEntityMapper, PhoneToResponseMapper phoneToResponseMapper) {
    this.phoneService = phoneService;
    this.phoneToEntityMapper = phoneToEntityMapper;
    this.phoneToResponseMapper = phoneToResponseMapper;
  }

  @PostMapping("/phones")
  public PhoneResponse savePhone(@RequestBody PhoneDto phoneDto) {
    Phone phone = phoneService.savePhone(phoneToEntityMapper.toMap(phoneDto));
    return phoneToResponseMapper.toMap(phone);
  }

  @GetMapping("/phones/{userId}")
  public List<PhoneResponse> allUserPhones(@PathVariable String userId) {
    return phoneService.allUserPhones(userId).stream()
      .map(phoneToResponseMapper::toMap)
      .collect(Collectors.toList());
  }

}
