package com.udemy.group;

import com.udemy.common.service.GenericCrudService;
import com.udemy.group.dto.GroupCreateDto;
import com.udemy.group.dto.GroupResponseDto;
import com.udemy.group.dto.GroupUpdateDto;
import com.udemy.group.entity.Group;
import com.udemy.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class GroupService extends GenericCrudService<Group, UUID, GroupCreateDto, GroupResponseDto, GroupUpdateDto> {

    private final GroupRepository repository;
    private final GroupModelMapper mapper;
    private final Class<Group> entityClass = Group.class;
    @Override
    public Group save(GroupCreateDto groupCreateDto) {
        return null;
    }
}
