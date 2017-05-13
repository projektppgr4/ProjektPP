package com.taskmgr.controller;

import com.taskmgr.service.UserService;
import com.taskmgr.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Akai on 2017-04-09.
 */
@Controller
public class HomeController {

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model, Principal principal) {
		model.addAttribute("user", principal.getName());
		return "admin/main";
	}

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "/welcome";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model, Principal principal) {
		model.addAttribute("user", principal.getName());
		return "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model, Principal principal) {
		model.addAttribute("user", principal.getName());
		return "accessDenied";
	}


}
