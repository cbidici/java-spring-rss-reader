package com.cbstd.rssr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cbstd.rssr.entity.Blog;
import com.cbstd.rssr.entity.User;
import com.cbstd.rssr.repository.ItemRepository;
import com.cbstd.rssr.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	// public User findOne(int id) {
	// return userRepository.findOne(id);
	// }

	@Transactional
	public User loadUserById(int id) {
		User user = userRepository.loadUserById(id);

		for (Blog blog : user.getBlogs()) {
			blog.setItems(itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishDate")));
		}

		return user;
	}

	public void save(User user) {
		userRepository.save(user);
	}

}
