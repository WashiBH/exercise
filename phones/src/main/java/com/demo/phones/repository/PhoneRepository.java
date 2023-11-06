package com.demo.phones.repository;

import com.demo.phones.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PhoneRepository extends JpaRepository<Phone, String> {
  public List<Phone> findByUserId(String userId);
}
