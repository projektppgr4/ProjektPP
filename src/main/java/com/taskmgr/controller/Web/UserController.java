package com.taskmgr.controller.Web;

import com.taskmgr.dao.UserDao;
import com.taskmgr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by Akai on 2017-05-12.
 */

@Controller
public class UserController {


	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/user/getTasks", method = RequestMethod.GET)
	public String userTasks(ModelMap model, HttpServletRequest request, Principal principal) {
		User user = userDao.findBySSO(principal.getName());
		model.addAttribute("taskList", user.getUserTasks());


		return "/user/tasks";
	}
}
