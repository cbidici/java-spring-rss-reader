package com.cbstd.rssr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.cbstd.rssr.entity.Blog;
import com.cbstd.rssr.entity.Item;
import com.cbstd.rssr.entity.User;
import com.cbstd.rssr.jaxb.rss.exception.RssException;
import com.cbstd.rssr.repository.BlogRepository;
import com.cbstd.rssr.repository.ItemRepository;
import com.cbstd.rssr.repository.UserRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private RssService rssService;

	public void saveUserBlog(Blog blog, String username) {
		User user = userRepository.findByUsername(username);
		saveUserBlog(blog, user);
	}

	public void saveUserBlog(Blog blog, int id) {
		User user = userRepository.findOne(id);
		saveUserBlog(blog, user);
	}

	private void saveUserBlog(Blog blog, User user) {
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.username == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
	}

	private void saveItems(Blog blog) {
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for (Item item : items) {
				Item existingItem = itemRepository.findByBlogAndLink(blog,
						item.getLink());
				if (null == existingItem) {
					item.setBlog(blog);
					itemRepository.save(item);
				}
			}
		} catch (RssException e) {
			e.printStackTrace();
		}
	}

	@Scheduled(fixedDelay = 3600000)
	public void reloadBlogs() {
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			saveItems(blog);

		}
	}

}
