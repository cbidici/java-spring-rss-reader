package com.cbstd.rssr.repository;

import com.cbstd.rssr.entity.User;

public interface UserRepositoryCustom {
	
	public User loadUserById(int id);
	
}
