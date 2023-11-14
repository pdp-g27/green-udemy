package com.udemy.user;

import com.udemy.common.service.GenericModelMapper;
import com.udemy.user.dto.UserCreateDto;
import com.udemy.user.dto.UserResponseDto;
import com.udemy.user.dto.UserUpdateDto;
import com.udemy.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserModelMapper extends GenericModelMapper<User, UserCreateDto, UserResponseDto, UserUpdateDto> {

    private final ModelMapper mapper;

    @Override
    public User toEntity(UserCreateDto createDto) {
        return mapper.map(createDto,User.class);
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    public void update(UserUpdateDto userUpdateDto, User user) {
        mapper.map(userUpdateDto,user);
    }
}
