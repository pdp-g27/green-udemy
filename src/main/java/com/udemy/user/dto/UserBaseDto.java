package com.udemy.user.dto;

import com.udemy.enums.UserType;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@MappedSuperclass
public class UserBaseDto {

    @NotBlank(message = "auth.user.firstName.required")
    private String firstName;

    @NotBlank(message = "auth.user.lastName.required")
    private String lastName;

    private String middleName;

    private UserType userType;

    @Email(message = "auth.user.email.required")
    private String email;

    @NotBlank(message = "auth.user.phoneNumber.required")
    @Pattern(regexp = "^(\\+998)\\d{9}", message = "pattern.phone.number")
    private String phoneNumber;


}
