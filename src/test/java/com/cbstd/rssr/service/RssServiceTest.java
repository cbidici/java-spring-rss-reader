package com.cbstd.rssr.service;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cbstd.rssr.entity.Item;
import com.cbstd.rssr.jaxb.rss.exception.RssException;

public class RssServiceTest {

	private RssService rssService;
	
	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService.getItems(new File("src/test/resources/test.xml"));
		assertEquals(10, items.size());
		Item item = items.get(0);
		assertEquals("How to solve Source not found error during debug in Eclipse", item.getTitle());
		assertEquals("22 06 2014 23:35:49", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(item.getPublishDate()));
	}

}
