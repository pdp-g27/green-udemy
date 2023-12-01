package com.udemy.group;

import com.udemy.group.dto.GroupCreateDto;
import com.udemy.group.dto.GroupResponseDto;
import com.udemy.group.dto.GroupUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        GroupResponseDto groupResponseDto = groupService.create(createDto);
        return ResponseEntity.ok().body(groupResponseDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDto> getById(@PathVariable(required = false) UUID id){
        GroupResponseDto group= groupService.getById(id);
        return ResponseEntity.ok().body(group);
    }
    @GetMapping("/list")
    public ResponseEntity<Page<GroupResponseDto>> getAll(Pageable pageable, @RequestParam(required = false) String predicate){
        Page<GroupResponseDto> groupList = groupService.getAll(pageable, predicate);
        return ResponseEntity.ok().body(groupList);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable UUID id){
        groupService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GroupResponseDto>update(@PathVariable UUID id,
                                                 @RequestBody GroupUpdateDto updateDto){
        GroupResponseDto groupResponseDto = groupService.update(id, updateDto);
        return ResponseEntity.ok().body(groupResponseDto);
    }

}
