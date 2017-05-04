package com.taskmgr.controller;

import com.taskmgr.dao.UserDao;
import com.taskmgr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Akai on 2017-04-09.
 */


@Controller
public class AdminController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@RequestMapping("/admin/list")
	public ModelAndView handleRequest() throws Exception {
		List userTask = userService.findAll();
		ModelAndView model = new ModelAndView("/admin/allUserList");
		model.addObject("userList", userTask);
		return model;
	}

}
