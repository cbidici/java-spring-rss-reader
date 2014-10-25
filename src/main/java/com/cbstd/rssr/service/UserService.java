package com.cbstd.rssr.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cbstd.rssr.entity.Feed;
import com.cbstd.rssr.entity.Role;
import com.cbstd.rssr.entity.User;
import com.cbstd.rssr.repository.ItemRepository;
import com.cbstd.rssr.repository.RoleRepository;
import com.cbstd.rssr.repository.UserRepository;
import com.cbstd.rssr.util.Constants;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	// public User findOne(int id) {
	// return userRepository.findOne(id);
	// }

	@Transactional
	public User loadUser(int id) {
		User user = userRepository.loadUserById(id);

		for (Feed feed : user.getFeeds()) {
			feed.setItems(itemRepository.findByFeed(feed, new PageRequest(0, 10, Direction.DESC, "publishDate")));
		}

		return user;
	}

	public void save(User user) {
		BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder();
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByRole(Constants.ROLE_READER));
		user.setRoles(roles);
		user.setEnabled(true);
		user.setPassword(bEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Transactional
	public User loadUser(String username) {
		User user = userRepository.findByUsername(username);
		return loadUser(user.getId());
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void delete(int id) {
		userRepository.delete(id);
	}

}
