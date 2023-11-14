package com.udemy.user;

import com.udemy.user.UserService;
import com.udemy.user.dto.UserCreateDto;
import com.udemy.user.dto.UserResponseDto;
import com.udemy.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable(required = false) UUID id){
        UserResponseDto responseDto = userService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }
    @GetMapping("/list")
    public ResponseEntity<Page<UserResponseDto>> getAll(Pageable pageable, @RequestParam(required = false) String predicate){
        Page<UserResponseDto> userList = userService.getAll(pageable, predicate);
        return ResponseEntity.ok().body(userList);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto>update(@PathVariable UUID id,
                                                 @RequestBody UserUpdateDto updateDto){
        UserResponseDto responseDto = userService.update(id, updateDto);
        return ResponseEntity.ok().body(responseDto);
    }

}
