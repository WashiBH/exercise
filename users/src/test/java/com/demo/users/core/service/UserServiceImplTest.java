package com.demo.users.core.service;

import com.demo.users.core.dao.UserFacadeDao;
import com.demo.users.core.dao.entity.User;
import com.demo.users.core.dao.http.dto.PhoneDto;
import com.demo.users.core.dao.http.dto.PhoneResponse;
import com.demo.users.error.PhoneException;
import com.demo.users.error.UserEmailFoundException;
import com.demo.users.error.UserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserFacadeDao userDao;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private JwtService jwtService;

  @Test
  void testSuccessSaveUser() {
    User expected = new User();
    expected.setName("Pedro Perez");
    expected.setEmail("pedro.perez@gmail.com");
    expected.setPassword(passwordEncoder.encode("password"));

    Mockito.when(userDao.findUserByEmail(Mockito.anyString())).thenReturn(Optional.empty());
    Mockito.when(userDao.saveUser(Mockito.any(User.class))).thenReturn(Optional.of(expected));

    User savedUser = userService.saveUser(expected);

    assertEquals(expected, savedUser);
  }

  @Test
  void testSaveUserDuplicate() {
    User duplicated = new User();
    duplicated.setName("Pedro Perez");
    duplicated.setEmail("pedro.perez@gmail.com");

    Mockito.when(userDao.findUserByEmail(Mockito.anyString())).thenReturn(Optional.of(duplicated));

    assertThrows(UserEmailFoundException.class, () -> userService.saveUser(duplicated));
  }

  @Test
  void testExceptionInSaveUser() {
    User user = new User();
    user.setName("Pedro Perez");
    user.setEmail("pedro.perez@gmail.com");

    Mockito.when(userDao.findUserByEmail(Mockito.anyString())).thenReturn(Optional.empty());
    Mockito.when(userDao.saveUser(Mockito.any(User.class))).thenReturn(Optional.empty());

    assertThrows(UserException.class, () -> userService.saveUser(user));
  }

  @Test
  void testSuccessSavePhone() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setUserId("wererz");
    phoneDto.setNumber("940000000");
    phoneDto.setCityCode("1");
    phoneDto.setCountryCode("57");

    PhoneResponse expected = new PhoneResponse();
    expected.setNumber("940000000");
    expected.setCityCode("1");
    expected.setCountryCode("57");

    Mockito.when(userDao.savePhone(Mockito.any(PhoneDto.class))).thenReturn(Optional.of(expected));

    PhoneResponse savedPhoneResponse = userService.savePhone(phoneDto);

    assertEquals(expected, savedPhoneResponse);
  }

  @Test
  void testExceptionInSavePhone() {
    PhoneDto phoneDto = new PhoneDto();
    phoneDto.setUserId("wererz");
    phoneDto.setNumber("940000000");
    phoneDto.setCityCode("1");
    phoneDto.setCountryCode("57");

    Mockito.when(userDao.savePhone(Mockito.any(PhoneDto.class))).thenReturn(Optional.empty());

    assertThrows(PhoneException.class, () -> userService.savePhone(phoneDto));
  }
}