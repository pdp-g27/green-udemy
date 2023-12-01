package com.udemy.group.dto;

import com.udemy.enums.GroupType;
import jakarta.persistence.EnumType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class GroupBaseDto {

    @NotBlank(message = "group.name.required")
    private String name;

    @NotBlank(message = "group.type.required")
    @Enumerated(EnumType.STRING)
    private GroupType groupType;

}
