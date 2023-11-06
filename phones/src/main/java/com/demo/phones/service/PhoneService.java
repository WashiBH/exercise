package com.demo.phones.service;

import com.demo.phones.entity.Phone;

import java.util.List;
public interface PhoneService {
  public Phone savePhone(Phone phone);

  public List<Phone> allUserPhones(String userId);
}
