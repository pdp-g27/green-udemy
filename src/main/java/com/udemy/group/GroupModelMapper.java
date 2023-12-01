package com.udemy.group;

import com.udemy.common.service.GenericModelMapper;
import com.udemy.group.dto.GroupCreateDto;
import com.udemy.group.dto.GroupResponseDto;
import com.udemy.group.dto.GroupUpdateDto;
import com.udemy.group.entity.Group;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GroupModelMapper extends GenericModelMapper<Group, GroupCreateDto, GroupResponseDto, GroupUpdateDto> {

    private final ModelMapper mapper;

    @Override
    public Group toEntity(GroupCreateDto groupCreateDto) {
        return mapper.map(groupCreateDto,Group.class);
    }

    @Override
    public GroupResponseDto toResponseDto(Group group) {
        return mapper.map(group, GroupResponseDto.class);
    }

    @Override
    public void update(GroupUpdateDto groupUpdateDto, Group group) {
        mapper.map(groupUpdateDto,group);
    }
}
