package com.udemy.user;

import com.udemy.user.UserService;
import com.udemy.user.dto.UserCreateDto;
import com.udemy.user.dto.UserResponseDto;
import com.udemy.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto createDto){
        return userService.create(createDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable(required = false) UUID id){
        return userService.getById(id);
    }
    @GetMapping("/list")
    public ResponseEntity<Page<UserResponseDto>> getAll(Pageable pageable, String predicate){
        return userService.getAll(pageable,predicate);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable UUID id){
       return userService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto>update(@PathVariable UUID id,
                                                 @RequestBody UserUpdateDto updateDto){
        return userService.update(id, updateDto);
    }

}
