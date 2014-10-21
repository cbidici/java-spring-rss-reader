package com.cbstd.rssr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbstd.rssr.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

	User findByUsername(String username);

}
