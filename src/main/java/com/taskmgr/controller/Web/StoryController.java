package com.taskmgr.controller.Web;

import com.taskmgr.dao.IterationDao;
import com.taskmgr.dao.StoryDao;
import com.taskmgr.model.Iteration;
import com.taskmgr.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Akai on 2017-03-29.
 */

@Controller
public class StoryController {

	@Autowired
	private IterationDao iterationDao;

	@Autowired
	private StoryDao storyDao;

	@RequestMapping(value = "/project/iteration/story/newStory", method = RequestMethod.GET)
	public String createNewStory(ModelMap model, HttpServletRequest request, HttpSession session) {
		Iteration iteration = iterationDao.getById((Integer.parseInt(request.getParameter("iterationId"))));
		session.setAttribute("iteration", iteration);
		Story newStory = new Story();
		model.addAttribute("story", newStory);
		return "/project/iteration/story/storyForm";
	}

	@RequestMapping(value = "/project/iteration/story/save", method = RequestMethod.POST)
	public String saveTask(@ModelAttribute("story") Story story, HttpServletRequest request, HttpSession session) {
		Iteration iteration;
		if (storyDao.getById(story.getId()) == null) {
			iteration = (Iteration) session.getAttribute("iteration");
			story.setIteration(iteration);
		} else {
			iteration = storyDao.getById(story.getId()).getIteration();
			story.setIteration(iteration);
		}
		storyDao.saveOrUpdate(story);


		return "redirect:/project/iteration/details?id=" + iteration.getId();
	}

	@RequestMapping(value = "/project/iteration/story/edit", method = RequestMethod.GET)
	public String editStory(ModelMap model, HttpServletRequest request) {
		int storyId = Integer.parseInt(request.getParameter("id"));
		Story story = storyDao.getById(storyId);
		model.addAttribute("story", story);
		return "/project/iteration/story/storyForm";
	}


	@RequestMapping(value = "/project/iteration/story/delete", method = RequestMethod.GET)
	public String deleteStory(ModelMap model, HttpServletRequest request) {
		int storyId = Integer.parseInt(request.getParameter("id"));
		Story deleteStory = storyDao.getById(storyId);
		int iterationId = deleteStory.getIteration().getId();
		storyDao.delete(deleteStory);
		return "redirect:/project/iteration/details?id=" + iterationId;
	}

}
