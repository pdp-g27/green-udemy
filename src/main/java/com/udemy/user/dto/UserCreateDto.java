package com.udemy.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
@EqualsAndHashCode(callSuper = true)
public class UserCreateDto extends UserBaseDto {

    @NotBlank
    private String password;

}
