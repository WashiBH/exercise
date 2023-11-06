package com.demo.phones.service;

import com.demo.phones.entity.Phone;
import com.demo.phones.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PhoneServiceImpl implements PhoneService{

  private final PhoneRepository phoneRepository;

  public PhoneServiceImpl(PhoneRepository phoneRepository) {
    this.phoneRepository = phoneRepository;
  }

  @Override
  public Phone savePhone(Phone phone) {
    return phoneRepository.save(phone);
  }

  @Override
  public List<Phone> allUserPhones(String userId) {
    return phoneRepository.findByUserId(userId);
  }
}
