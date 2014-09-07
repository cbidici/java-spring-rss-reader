package com.cbstd.rssr.repository.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.cbstd.rssr.entity.Blog;
import com.cbstd.rssr.entity.User;
import com.cbstd.rssr.repository.UserRepository;
import com.cbstd.rssr.repository.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom {


	@Autowired
	private UserRepository userRepository;

	@Override
	public User loadUserById(int id) {

		User user = userRepository.findOne(id);
		// this will force SQL to execute the query that will join with the
		// user's profile and populate
		// the appropriate information into the user object.
		Hibernate.initialize(user.getBlogs());

		return user;
	}

}
