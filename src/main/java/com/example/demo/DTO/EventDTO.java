package com.example.demo.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {

  @NotBlank(message = "عنوان رویداد نباید خالی باشد")
  private String title;

  @NotBlank(message = "توضیحات رویداد نباید خالی باشد")
  private String description;

  @Future(message = "تاریخ رویداد باید در آینده باشد")
  @NotNull(message = "تاریخ رویداد نباید خالی باشد")
  private LocalDateTime dateTime;

  @NotBlank(message = "مکان رویداد نباید خالی باشد")
  private String location;

  @Min(value = 1, message = "حداقل تعداد صندلی‌ها باید ۱ باشد")
  private int availableSeats;

  @NotNull(message = "شناسه سازمان‌دهنده نباید خالی باشد")
  private Long organizerId;
}
