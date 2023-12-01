package com.udemy.group;

import com.udemy.common.repository.GenericRepository;
import com.udemy.group.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepository extends GenericRepository<Group, UUID> {
}
