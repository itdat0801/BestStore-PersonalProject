package com.personal.dat.be.best_store_server.dto.request;

import com.personal.dat.be.best_store_server.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, max = 50, message = "USERNAME_INVALID")
    String username;
    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;
    @DobConstraint(min = 16, message = "INVALID_DOB")
    LocalDate dob;

}
