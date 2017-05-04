package com.taskmgr.controller;

import com.taskmgr.dao.StoryDao;
import com.taskmgr.dao.TaskDao;
import com.taskmgr.model.Story;
import com.taskmgr.model.Task;
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
public class TaskController {

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private StoryDao storyDao;

	@RequestMapping(value = "/project/iteration/story/newTask", method = RequestMethod.GET)
	public String createNewTask(ModelMap model, HttpServletRequest request, HttpSession session) {
		Story story = storyDao.getById((Integer.parseInt(request.getParameter("storyId"))));
		session.setAttribute("story", story);
		Task newTask = new Task();
		model.addAttribute("task", newTask);
		return "/project/iteration/story/task/taskForm";
	}

	@RequestMapping(value = "/project/iteration/story/task/edit", method = RequestMethod.GET)
	public String editTask(ModelMap model, HttpServletRequest request) {
		int taskId = Integer.parseInt(request.getParameter("id"));
		Task task = taskDao.getById(taskId);
		model.addAttribute("task", task);
		return "/project/iteration/story/task/taskForm";
	}

	@RequestMapping(value = "/project/iteration/story/task/delete", method = RequestMethod.GET)
	public String deleteTask(ModelMap model, HttpServletRequest request) {
		int taskId = Integer.parseInt(request.getParameter("id"));
		Task deleteTask = taskDao.getById(taskId);
		deleteTask.getStory().getTasks().remove(deleteTask);
		taskDao.delete(deleteTask);
		return "redirect:/project/iteration/story/details?id=" + deleteTask.getStory().getId();
	}

	@RequestMapping(value = "/project/iteration/story/task/save", method = RequestMethod.POST)
	public String saveTask(@ModelAttribute("task") Task task, HttpServletRequest request, HttpSession session) {
		Story story;
		if (taskDao.getById(task.getId()) == null) {
			story = (Story) session.getAttribute("story");
			task.setStory(story);
			story.getTasks().add(task);
		} else {
			story = taskDao.getById(task.getId()).getStory();
			task.setStory(story);
		}
		taskDao.saveOrUpdate(task);


		return "redirect:/project/iteration/story/details?id=" + story.getId();
	}


}
