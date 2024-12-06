package com.personal.dat.be.best_store_server.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserUpdateRequest {
     String password;
     String firstName;
     String lastName;
     LocalDate dob;

}
