package com.cbstd.rssr.service;

import java.io.File;
import java.nio.channels.Channel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;

import com.cbstd.rssr.entity.Item;
import com.cbstd.rssr.jaxb.rss.ObjectFactory;
import com.cbstd.rssr.jaxb.rss.TRss;
import com.cbstd.rssr.jaxb.rss.TRssChannel;
import com.cbstd.rssr.jaxb.rss.TRssItem;
import com.cbstd.rssr.jaxb.rss.exception.RssException;

@Service
public class RssService {

	public List<Item> getItems(File file) throws RssException
	{
		return getItems(new StreamSource(file));
	}
	
	public List<Item> getItems(String url) throws RssException
	{
		return getItems(new StreamSource(url));
	}
	
	private List<Item> getItems(Source source) throws RssException
	{
		List<Item> items = new ArrayList<Item>();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source, TRss.class);
			TRss rss = jaxbElement.getValue();
			
			List<TRssChannel> rssChannels = rss.getChannel();
			for (TRssChannel rssChannel : rssChannels) {
				List<TRssItem> rssItems = rssChannel.getItem();
				for (TRssItem rssItem : rssItems) {
					 Item item = new Item();
					 item.setTitle(rssItem.getTitle());
					 item.setDescription(rssItem.getDescription());
					 item.setLink(rssItem.getLink());
					 item.setPublishDate(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(rssItem.getPubDate()));
					 items.add(item);
				}
			}
			return items;
		} catch (JAXBException e) {
			throw new RssException(e);
		} catch (ParseException e) {
			throw new RssException(e);
		}
	}
}
