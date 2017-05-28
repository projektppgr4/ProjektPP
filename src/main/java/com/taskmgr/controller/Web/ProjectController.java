package com.taskmgr.controller.Web;

import com.taskmgr.dao.ProjectDao;
import com.taskmgr.dao.UserDao;
import com.taskmgr.model.Project;
import com.taskmgr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Akai on 2017-05-04.
 */

@Controller
public class ProjectController {

	@Autowired
	UserDao userDao;

	@Autowired
	ProjectDao projectDao;


	@RequestMapping(value = "/project/newProject", method = RequestMethod.GET)
	public String createNewProject(ModelMap model, HttpServletRequest request, HttpSession session) {
		User user = userDao.findById((Integer.parseInt(request.getParameter("userId"))));
		session.setAttribute("user", user);
		Project newProject = new Project();
		model.addAttribute("project", newProject);
		return "/project/projectForm";
	}

	@RequestMapping(value = "/project/save", method = RequestMethod.POST)
	public String saveProject(@ModelAttribute("project") Project project, HttpServletRequest request, HttpSession session) {
		User user;
		if (projectDao.getById(project.getId()) == null) {
			user = (User) session.getAttribute("user");
			project.setUser(user);
			user.getProjects().add(project);
		} else {
			user = projectDao.getById(project.getId()).getUser();
			project.setUser(user);
		}
		projectDao.saveOrUpdate(project);


		return "redirect:/project/list";
	}


	@RequestMapping(value = "/project/edit", method = RequestMethod.GET)
	public String editProject(ModelMap model, HttpServletRequest request) {
		int projectId = Integer.parseInt(request.getParameter("id"));
		Project project = projectDao.getById(projectId);
		model.addAttribute("project", project);
		return "/project/projectForm";
	}

	@RequestMapping(value = "/project/delete", method = RequestMethod.GET)
	public String deleteProject(ModelMap model, HttpServletRequest request) {
		int projectId = Integer.parseInt(request.getParameter("id"));
		Project deleteProject = projectDao.getById(projectId);
		projectDao.delete(deleteProject);
		return "redirect:/project/list";
	}

}
