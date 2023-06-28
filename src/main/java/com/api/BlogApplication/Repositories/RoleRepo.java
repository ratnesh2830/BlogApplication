package com.api.BlogApplication.Repositories;

import com.api.BlogApplication.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
