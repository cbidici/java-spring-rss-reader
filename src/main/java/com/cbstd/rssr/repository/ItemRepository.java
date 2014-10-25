package com.cbstd.rssr.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cbstd.rssr.entity.Feed;
import com.cbstd.rssr.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public List<Item> findByFeed(Feed feed, Pageable pageable);
	
	public Item findByFeedAndLink(Feed feed, String link); 
}
