package com.example.demo.DTO;

import com.example.demo.Model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

  @NotBlank(message = "نام کاربری نباید خالی باشد")
  private String username;

  @Email(message = "ایمیل باید معتبر باشد")
  @NotBlank(message = "ایمیل نباید خالی باشد")
  private String email;

  @NotBlank(message = "رمز عبور نباید خالی باشد")
  private String password;

  @NotNull(message = "نقش کاربر باید مشخص شود")
  private Role role;
}
