package com.udemy.user;

import com.udemy.common.repository.GenericRepository;
import com.udemy.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends GenericRepository<User, UUID> {
}
