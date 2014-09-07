package com.cbstd.rssr.service.system;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbstd.rssr.entity.Blog;
import com.cbstd.rssr.entity.Item;
import com.cbstd.rssr.entity.Role;
import com.cbstd.rssr.entity.User;
import com.cbstd.rssr.repository.BlogRepository;
import com.cbstd.rssr.repository.ItemRepository;
import com.cbstd.rssr.repository.RoleRepository;
import com.cbstd.rssr.repository.UserRepository;
import com.cbstd.rssr.repository.system.DictionaryRepository;

@Transactional
@Service
public class InitDBService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@PostConstruct
	public void init()
	{		
		
		Role roleReader = new Role();
		roleReader.setRole("ROLE_READER");
		roleRepository.save(roleReader);
		
		Role roleAdmin = new Role();
		roleAdmin.setRole("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setEmail("cbidici@gmail.com");
		userAdmin.setUsername("admin");
		userAdmin.setPassword("admin");
		userAdmin.setRoles(new ArrayList<Role>());
		userAdmin.getRoles().add(roleReader);
		userAdmin.getRoles().add(roleAdmin);
		userRepository.save(userAdmin);
		
		Blog blogJavavids = new Blog();
		blogJavavids.setName("JavaVids");
		blogJavavids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJavavids.setUser(userAdmin);
		blogRepository.save(blogJavavids);
		
		Item item1 = new Item();
		item1.setBlog(blogJavavids);
		item1.setTitle("First Item");
		item1.setLink("http://www.google.com.tr");
		item1.setPublishDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(blogJavavids);
		item2.setTitle("Second Item");
		item2.setLink("http://www.google.com.tr");
		item2.setPublishDate(new Date());
		itemRepository.save(item2);
	}
	
}
