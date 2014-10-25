package com.cbstd.rssr.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "rssr_feed")
public class Feed {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 1, max = 256, message = "{com.cbstd.rssr.entity.feed.name.size}")
	private String name;

	@Size(min = 1, message = "{com.cbstd.rssr.entity.feed.url.url}")
	@URL(message = "{com.cbstd.rssr.entity.feed.url.url}")
	@Column(length = 1000)
	private String url;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
	private List<Item> items;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
