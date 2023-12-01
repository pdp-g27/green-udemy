package com.udemy.user;

import com.udemy.common.service.GenericCrudService;
import com.udemy.user.dto.UserCreateDto;
import com.udemy.user.dto.UserResponseDto;
import com.udemy.user.dto.UserUpdateDto;
import com.udemy.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class UserService extends GenericCrudService<User, UUID, UserCreateDto, UserResponseDto, UserUpdateDto>
                        implements UserDetailsService {

    private final UserRepository repository;
    private final UserModelMapper mapper;
    private final Class<User> entityClass = User.class;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return repository.findByPhoneNumber(phone)
                .orElseThrow(() -> new BadCredentialsException("bad credentials"));
    }



    @Override
    public User save(UserCreateDto createDto) {
        User user = mapper.toEntity(createDto);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return user;
    }


}
