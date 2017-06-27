package com.taskmgr.controller.Web;

import com.taskmgr.dao.IterationDao;
import com.taskmgr.dao.ProjectDao;
import com.taskmgr.model.Iteration;
import com.taskmgr.model.Project;
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
public class IterationController {


	@Autowired
	private IterationDao iterationDao;

	@Autowired
	private ProjectDao projectDao;

	@RequestMapping(value = "/project/iteration/newIteration", method = RequestMethod.GET)
	public String createNewIteration(ModelMap model, HttpServletRequest request, HttpSession session) {
		Project project = projectDao.getById((Integer.parseInt(request.getParameter("projectId"))));
		session.setAttribute("project", project);
		Iteration newIteration = new Iteration();
		model.addAttribute("iteration", newIteration);
		return "/project/iteration/iterationForm";
	}

	@RequestMapping(value = "/project/iteration/save", method = RequestMethod.POST)
	public String saveIteration(@ModelAttribute("iteration") Iteration iteration, HttpServletRequest request, HttpSession session) {
		Project project;
		if (iterationDao.getById(iteration.getId()) == null) {
			project = (Project) session.getAttribute("project");
			iteration.setProject(project);
		} else {
			project = iterationDao.getById(iteration.getId()).getProject();
			iteration.setProject(project);
		}
		iterationDao.saveOrUpdate(iteration);


		return "redirect:/project/details?id=" + project.getId();
	}


	@RequestMapping(value = "/project/iteration/edit", method = RequestMethod.GET)
	public String editIteration(ModelMap model, HttpServletRequest request) {
		int iterationId = Integer.parseInt(request.getParameter("id"));
		Iteration iteration = iterationDao.getById(iterationId);
		model.addAttribute("iteration", iteration);
		return "/project/iteration/iterationForm";
	}


	@RequestMapping(value = "/project/iteration/delete", method = RequestMethod.GET)
	public String deleteIteration(ModelMap model, HttpServletRequest request) {
		int iterationId = Integer.parseInt(request.getParameter("id"));
		Iteration deleteIteration = iterationDao.getById(iterationId);
		iterationDao.delete(deleteIteration);
		return "/project/iteration/list";
	}


}
