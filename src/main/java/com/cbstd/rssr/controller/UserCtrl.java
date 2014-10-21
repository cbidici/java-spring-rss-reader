package com.cbstd.rssr.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbstd.rssr.entity.Blog;
import com.cbstd.rssr.entity.User;
import com.cbstd.rssr.service.BlogService;
import com.cbstd.rssr.service.UserService;

@Controller
@RequestMapping("/user")
public class UserCtrl {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;

	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}

	@ModelAttribute("blog")
	public Blog constructBlog() {
		return new Blog();
	}

	@RequestMapping(value = "/feeds")
	public String feeds(Model model, Principal principal) {
		model.addAttribute("user", userService.loadUser(principal.getName()));
		return "feeds";
	}

	@RequestMapping(value = "/account")
	public String account(Model model, Principal principal) {
		model.addAttribute("user", userService.loadUser(principal.getName()));
		return "account";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddFeed(Model model, @Valid @ModelAttribute("blog") Blog blog, BindingResult result, Principal principal) {
		if(result.hasErrors())
		{
			return account(model, principal);
		}
		blogService.saveUserBlog(blog, principal.getName());
		return "redirect:/user/account.html";
	}
	
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id) {
		Blog blog = blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/user/account.html";
	}

}
