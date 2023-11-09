package com.demo.users.integration.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  @NotNull(message = "El nombre no puede ser nulo.")
  @Size(min = 1, message = "El tamaño mínimo del campo name es 1 caracter.")
  private String name;

  @NotNull(message = "El email no puede ser nulo.")
  @Size(min = 5, message = "El tamaño mínimo del campo email es 5 caracteres.")
  @Email(message = "El email no es válido.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  private String email;

  @Size(min = 1, message = "El tamaño mínimo del campo password es 1 caracter.")
  @NotNull(message = "El password no puede ser nulo.")
  @Pattern(message = "La contraseña debe contener una mayuscula, letras minúsculas, y dos numeros.", regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=(?:[^0-9]*[0-9]){2})[A-Za-z0-9]*$")
  private String password;

  private List<PhoneDto> phones;
}
