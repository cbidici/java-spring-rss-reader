package com.cbstd.rssr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbstd.rssr.entity.User;
import com.cbstd.rssr.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterCtrl {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}
	
	@RequestMapping
	public String showRegister() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors())
		{
			return "register";
		}
		userService.save(user);
		redirectAttributes.addFlashAttribute("register", "success");
		return "redirect:/register.html";
	}
	
	@RequestMapping("/available")
	@ResponseBody
	public String available(@RequestParam String username)
	{
		Boolean available = null == userService.findByUsername(username);
		return available.toString();
	}
}
