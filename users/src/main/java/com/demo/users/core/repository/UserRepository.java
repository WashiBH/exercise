package com.demo.users.core.repository;

import com.demo.users.core.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  public Optional<User> findByEmail(String email);
}
