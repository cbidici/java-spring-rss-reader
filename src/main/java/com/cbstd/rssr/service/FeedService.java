package com.cbstd.rssr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.cbstd.rssr.entity.Feed;
import com.cbstd.rssr.entity.Item;
import com.cbstd.rssr.entity.User;
import com.cbstd.rssr.jaxb.rss.exception.RssException;
import com.cbstd.rssr.repository.FeedRepository;
import com.cbstd.rssr.repository.ItemRepository;
import com.cbstd.rssr.repository.UserRepository;

@Service
public class FeedService {

	@Autowired
	private FeedRepository feedRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private RssService rssService;

	public void saveUserFeed(Feed feed, String username) {
		User user = userRepository.findByUsername(username);
		saveUserFeed(feed, user);
	}

	public void saveUserFeed(Feed feed, int id) {
		User user = userRepository.findOne(id);
		saveUserFeed(feed, user);
	}

	private void saveUserFeed(Feed feed, User user) {
		feed.setUser(user);
		feedRepository.save(feed);
		saveItems(feed);
	}

	@PreAuthorize("#feed.user.username == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("feed") Feed feed) {
		feedRepository.delete(feed);
	}

	public Feed findOne(int id) {
		return feedRepository.findOne(id);
	}

	private void saveItems(Feed feed) {
		try {
			List<Item> items = rssService.getItems(feed.getUrl());
			for (Item item : items) {
				Item existingItem = itemRepository.findByFeedAndLink(feed,
						item.getLink());
				if (null == existingItem) {
					item.setFeed(feed);
					itemRepository.save(item);
				}
			}
		} catch (RssException e) {
			e.printStackTrace();
		}
	}

	@Scheduled(fixedDelay = 3600000)
	public void reloadFeeds() {
		List<Feed> feeds = feedRepository.findAll();
		for (Feed feed : feeds) {
			saveItems(feed);

		}
	}

}
