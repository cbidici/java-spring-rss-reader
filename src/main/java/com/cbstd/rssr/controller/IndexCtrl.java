package com.cbstd.rssr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbstd.rssr.service.ItemService;

@Controller
public class IndexCtrl {

	@Autowired
	ItemService itemService;
	
	@RequestMapping("/index")
	public String index(Model model)
	{
		model.addAttribute("items", itemService.getItems());
		return "index";
	}
}
