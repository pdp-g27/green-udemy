package com.udemy.user;

import com.udemy.common.jwt.JwtUtils;
import com.udemy.user.dto.UserCreateDto;
import com.udemy.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto createDto){
        UserResponseDto responseDto = userService.create(createDto);
        String token = jwtUtils.generateToken(createDto.getPhoneNumber());
        return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .header(HttpHeaders.AUTHORIZATION,"Bearer %s".formatted(token))
                 .body(responseDto);
    }

}


