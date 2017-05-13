package com.taskmgr.controller;

import com.taskmgr.dao.StoryDao;
import com.taskmgr.dao.TaskDao;
import com.taskmgr.dao.UserDao;
import com.taskmgr.model.Story;
import com.taskmgr.model.Task;
import com.taskmgr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Akai on 2017-03-29.
 */

@Controller
public class TaskController {

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private StoryDao storyDao;

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/project/iteration/story/task/newTask", method = RequestMethod.GET)
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

	@RequestMapping(value = "/project/iteration/story/task/userToFind", method = RequestMethod.POST)
	public String usersAviableToAssignList(@ModelAttribute("user") User user, ModelMap model) {
		List<User> userList = userDao.findByLastNameBegin(user.getLastName());
		model.addAttribute("userList", userList);
		return "/project/iteration/story/task/userToAssignList";
	}

	@RequestMapping(value = "/project/iteration/story/task/assignToTask", method = RequestMethod.GET)
	public String searchUser(ModelMap model, HttpServletRequest request, HttpSession session) {
		int taskId = Integer.parseInt(request.getParameter("id"));
		Task task = taskDao.getById(taskId);
		session.setAttribute("task", task);

		User user = new User();
		model.addAttribute(user);
		return "/project/iteration/story/task/userSearch";
	}


	@RequestMapping(value = "/project/iteration/story/task/assign", method = RequestMethod.GET)
	public String userAssing(HttpServletRequest request, HttpSession session, ModelMap model) {
		//TODO zabezpieczenie przed podwojnym dodaniem
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userDao.findById(userId);
		Task task = (Task) session.getAttribute("task");
		user.getUserTasks().add(task);
		userDao.saveOrUpdate(user);
		return "redirect:/project/iteration/story/details?id=" + task.getStory().getId();
	}

	@RequestMapping(value = "/user/work", method = RequestMethod.GET)
	public String work(ModelMap model, HttpServletRequest request) {
		int taskId = Integer.parseInt(request.getParameter("id"));
		Task task = taskDao.getById(taskId);
		model.addAttribute("task", task);
		model.addAttribute("story", task.getStory());
		model.addAttribute("iteration", task.getStory().getIteration());
		model.addAttribute("project", task.getStory().getIteration().getProject());
		return "user/workStatus";
	}

	@PostMapping(value = "/user/changeTime")
	public String changeTime(@Valid @ModelAttribute("task") Task task, BindingResult result) {
		Story story;
		story = taskDao.getById(task.getId()).getStory();
		task.setStory(story);

		if (!result.hasErrors())
			taskDao.saveOrUpdate(task);

		return "redirect:/user/work?id=" + task.getId();
	}
}
