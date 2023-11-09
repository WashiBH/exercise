package com.demo.phones.controller;

import com.demo.phones.controller.dto.PhoneDto;
import com.demo.phones.controller.dto.PhoneResponse;
import com.demo.phones.controller.mapper.PhoneToEntityMapper;
import com.demo.phones.controller.mapper.PhoneToResponseMapper;
import com.demo.phones.entity.Phone;
import com.demo.phones.service.PhoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase REST que contiene los HTTP endpoints.
 */
@RequiredArgsConstructor
@RestController
public class PhoneController {

  private final PhoneService phoneService;
  private final PhoneToEntityMapper phoneToEntityMapper;
  private final PhoneToResponseMapper phoneToResponseMapper;

  @PostMapping("/phones")
  public PhoneResponse savePhone(@RequestBody PhoneDto phoneDto) {
    Phone phone = phoneService.savePhone(phoneToEntityMapper.toMap(phoneDto));
    return phoneToResponseMapper.toMap(phone);
  }

  /**
   * Metodo que obtiene todos los telefonos de un usuario a partir del ID.
   *
   * @param userId ID del usuario.
   * @return Lista de telefonos del usuario.
   */
  @GetMapping("/phones/{userId}")
  public List<PhoneResponse> allUserPhones(@PathVariable String userId) {
    return phoneService.getAllPhonesForUser(userId).stream()
      .map(phoneToResponseMapper::toMap)
      .toList();
  }

}
