package com.cbstd.rssr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbstd.rssr.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByRole(String role);

}
