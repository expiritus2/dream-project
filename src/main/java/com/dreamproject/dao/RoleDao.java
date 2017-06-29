package com.dreamproject.dao;

import com.dreamproject.entity.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Long> {

    Role findByName(String role);
}
