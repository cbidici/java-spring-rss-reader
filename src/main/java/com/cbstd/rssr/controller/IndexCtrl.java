package com.cbstd.rssr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCtrl {

	@Autowired
	MessageSource messageSource;
	
	@RequestMapping("/index")
	public String index(Model model)
	{
		return "index";
	}
}
