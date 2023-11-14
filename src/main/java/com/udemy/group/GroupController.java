package com.udemy.group;

import com.udemy.group.dto.GroupCreateDto;
import com.udemy.group.dto.GroupResponseDto;
import com.udemy.group.dto.GroupUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<GroupResponseDto> create(@RequestBody GroupCreateDto createDto){
        return groupService.create(createDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDto> getById(@PathVariable(required = false) UUID id){
        return groupService.getById(id);
    }
    @GetMapping("/list")
    public ResponseEntity<Page<GroupResponseDto>> getAll(Pageable pageable, String predicate){
        return groupService.getAll(pageable,predicate);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable UUID id){
       return groupService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GroupResponseDto>update(@PathVariable UUID id,
                                                 @RequestBody GroupUpdateDto updateDto){
        return groupService.update(id, updateDto);
    }

}
