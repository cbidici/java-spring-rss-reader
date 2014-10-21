package com.cbstd.rssr.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cbstd.rssr.entity.Blog;
import com.cbstd.rssr.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public List<Item> findByBlog(Blog blog, Pageable pageable);
	
	public Item findByBlogAndLink(Blog blog, String link); 
}
