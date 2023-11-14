package com.udemy.group.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class GroupResponseDto extends GroupBaseDto {

    private UUID id;
}
