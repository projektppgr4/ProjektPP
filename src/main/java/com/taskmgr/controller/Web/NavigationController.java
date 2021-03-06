package com.taskmgr.controller.Web;

import com.taskmgr.dao.*;
import com.taskmgr.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * Created by Akai on 2017-04-12.
 */

@Controller
public class NavigationController {

	@Autowired
	ProjectDao projectDao;

	@Autowired
	IterationDao iterationDao;

	@Autowired
	StoryDao storyDao;

	@Autowired
	TaskDao taskDao;

	@Autowired
	UserDao userDao;


	@RequestMapping(value = "/project/list", method = RequestMethod.GET)
	public String projectsPage(ModelMap model, Principal principal) {
		String userName = principal.getName();
		List<Project> projectList = projectDao.findAllUserProjectsBySsoId(userName);
		User user = userDao.findBySSO(userName);

		model.addAttribute("userId", user.getId());
		model.addAttribute("projects", projectList);
		return "/project/list";
	}


	@RequestMapping(value = "/project/details", method = RequestMethod.GET)
	public String projectDetails(ModelMap model, HttpServletRequest request) {
		int projectId = Integer.parseInt(request.getParameter("id"));
		List<Iteration> iterationList = iterationDao.getByProjectId(projectId);

		model.addAttribute("projectId", projectId);
		model.addAttribute("iterationList", iterationList);
		return "/project/iteration/list";
	}


	@RequestMapping(value = "/project/iteration/details", method = RequestMethod.GET)
	public String iterationDetails(ModelMap model, HttpServletRequest request) {
		int iterationId = Integer.parseInt(request.getParameter("id"));
		List<Story> storyList = storyDao.getByIterationId(iterationId);

		model.addAttribute("iterationId", iterationId);
		model.addAttribute("storyList", storyList);
		return "/project/iteration/story/list";
	}


	@RequestMapping(value = "/project/iteration/story/details", method = RequestMethod.GET)
	public String storyDetails(ModelMap model, HttpServletRequest request) {
		int storyId = Integer.parseInt(request.getParameter("id"));
		List<Task> storyList = taskDao.getByStoryId(storyId);

		model.addAttribute("storyId", storyId);
		model.addAttribute("taskList", storyList);

		return "/project/iteration/story/task/list";
	}

}
