package com.udemy.user;

import com.udemy.common.service.GenericCrudService;
import com.udemy.user.dto.UserCreateDto;
import com.udemy.user.dto.UserResponseDto;
import com.udemy.user.dto.UserUpdateDto;
import com.udemy.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class UserService extends GenericCrudService<User, UUID, UserCreateDto, UserResponseDto, UserUpdateDto> {

    private final UserRepository repository;
    private final UserModelMapper mapper;

}
