package com.demo.phones.service;

import com.demo.phones.entity.Phone;
import com.demo.phones.repository.PhoneRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Clase que maneja el CRUD de telefonos para el usuario.
 */

@RequiredArgsConstructor
@Service
public class PhoneServiceImpl implements PhoneService {

  private final PhoneRepository phoneRepository;

  @Override
  public Phone savePhone(Phone phone) {
    Optional<Phone> phoneOptional = phoneRepository
        .findByUserIdAndNumber(phone.getUserId(), phone.getNumber());
    phoneOptional.ifPresent(value -> phone.setId(value.getId()));
    return phoneRepository.save(phone);
  }

  @Override
  public List<Phone> getAllPhonesForUser(String userId) {
    return phoneRepository.findByUserId(userId);
  }

}
