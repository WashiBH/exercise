package com.demo.users.core.dao.http;

import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient(name = "phones", url = "http://localhost:8582")
public interface PhoneFeignClient {
  @PostMapping("/phones")
  public PhoneResponse savePhone(@RequestBody PhoneDto phoneDto);

  @GetMapping("/phones/{userId}")
  public List<PhoneResponse> findUserPhones(@PathVariable String userId);
}
