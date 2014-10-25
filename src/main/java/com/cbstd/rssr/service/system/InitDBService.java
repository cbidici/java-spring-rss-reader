package com.cbstd.rssr.service.system;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cbstd.rssr.entity.Feed;
import com.cbstd.rssr.entity.Role;
import com.cbstd.rssr.entity.User;
import com.cbstd.rssr.repository.FeedRepository;
import com.cbstd.rssr.repository.ItemRepository;
import com.cbstd.rssr.repository.RoleRepository;
import com.cbstd.rssr.repository.UserRepository;
import com.cbstd.rssr.util.Constants;

@Transactional
@Service
public class InitDBService {

	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void init()
	{
		
		if(userRepository.findByUsername("admin") == null)
		{
			BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder();
			
			Role roleReader = new Role();
			roleReader.setRole(Constants.ROLE_READER);
			roleRepository.save(roleReader);
			
			Role roleAdmin = new Role();
			roleAdmin.setRole(Constants.ROLE_ADMIN);
			roleRepository.save(roleAdmin);
			
			User userAdmin = new User();
			userAdmin.setEmail("cbidici@gmail.com");
			userAdmin.setUsername("admin");
			userAdmin.setPassword(bEncoder.encode("admin"));
			userAdmin.setRoles(new ArrayList<Role>());
			userAdmin.getRoles().add(roleReader);
			userAdmin.getRoles().add(roleAdmin);
			userAdmin.setEnabled(true);
			userRepository.save(userAdmin);
			
			Feed feedJavavids = new Feed();
			feedJavavids.setName("JavaVids");
			feedJavavids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
			feedJavavids.setUser(userAdmin);
			feedRepository.save(feedJavavids);
		}
	}
	
}
