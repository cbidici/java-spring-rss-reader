package com.cbstd.rssr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbstd.rssr.entity.Feed;
import com.cbstd.rssr.service.FeedService;
import com.cbstd.rssr.service.UserService;

@Controller
@RequestMapping("/users")
public class AdminCtrl {

	@Autowired
	UserService userService;
	
	@Autowired
	FeedService feedService;
	
	@ModelAttribute("feed")
	public Feed constructFeed() {
		return new Feed();
	}
	
	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	@RequestMapping("/{id}")
	public String userDetail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.loadUser(id));
		return "userdetail";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String doAddFeedFromDeail(Model model, @Valid @ModelAttribute("feed") Feed feed, BindingResult result, @PathVariable int id) {
		feed.setId(null);
		if(result.hasErrors())
		{
			return userDetail(model, id);
		}
		feedService.saveUserFeed(feed, id);
		model.addAttribute("user", userService.loadUser(id));
		return "redirect:/users/{id}.html";
	}
	
	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}

}
