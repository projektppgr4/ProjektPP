package com.taskmgr.controller.Rest;

import com.taskmgr.dao.StoryDao;
import com.taskmgr.dao.TaskDao;
import com.taskmgr.model.Task;
import com.taskmgr.model.TaskStatus;
import com.taskmgr.model.User;
import com.taskmgr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akai on 2017-03-29.
 */
@RestController
public class TaskRestController {

	private TaskDao taskDao;
	private StoryDao storyDao;
	private UserService userService;

	@Autowired
	public TaskRestController(TaskDao taskDao, StoryDao storyDao, UserService userService) {
		this.taskDao = taskDao;
		this.storyDao = storyDao;
		this.userService = userService;
	}

	/**
	 * Add new task without set its story
	 * Only to test use !!!
	 *
	 * @param task task to add
	 * @return task and server response status
	 */
	@Deprecated
	@PostMapping(value = "/api/task", consumes = {"application/json"})
	public ResponseEntity<Task> addTask(@RequestBody Task task) {
		taskDao.saveOrUpdate(task);
		return new ResponseEntity<Task>(task, HttpStatus.CREATED);
	}

	/**
	 * Delete test by its id
	 *
	 * @param taskId id of task to delete
	 * @return Server response and status
	 */
	@DeleteMapping(value = "/api/task{taskId}", consumes = {"application/json"})
	public ResponseEntity deleteTask(@PathVariable int taskId) {
		taskDao.delete(taskDao.getById(taskId));
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	/**
	 * Edit task without change its id
	 *
	 * @param task   values of task to edit
	 * @param status new status of task
	 * @return task and server status response
	 */
	@PutMapping(value = "/api/task{status}", consumes = {"application/json"})
	public ResponseEntity<Task> editTask(@RequestBody Task task, @PathVariable String status) {
		//TODO check task to exist in database
		List<TaskStatus> taskStatuses = new ArrayList<TaskStatus>();
		taskStatuses.addAll(taskDao.getById(task.getId()).getStory().getIteration().getTaskStatuses());
		for (TaskStatus statues : taskStatuses) {
			if (statues.getName().equals(status))
			{
				task.setTaskStatus(statues);
			}
		}
		taskDao.edit(task);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	/**
	 * Add new task to story and sets its status
	 *
	 * @param task    values of task
	 * @param storyId if of story
	 * @param status  new task status
	 * @return task and server status response
	 */
	@PostMapping(value = "/api/task{storyId}/{status}", consumes = {"application/json"})
	public ResponseEntity<Task> addTaskToStory(@RequestBody Task task, @PathVariable int storyId, @PathVariable String status) {
		task.setStory(storyDao.getById(storyId));
		List<TaskStatus> taskStatuses = new ArrayList<TaskStatus>();
		taskStatuses.addAll(storyDao.getById(storyId).getIteration().getTaskStatuses());
		for (TaskStatus statues:taskStatuses) {
			if(statues.getName().equals(status))
			{
				task.setTaskStatus(statues);
			}
		}
		taskDao.saveOrUpdate(task);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	/**
	 * Get all task in database
	 * @return list of all task in database
	 */
	@GetMapping(value = "/api/taskList")
	public List<Task> allTaskList() {
		return taskDao.findAll();
	}

	/**
	 * Get all task of selected user
	 *
	 * @param userId id of user
	 * @return list of all user tasks
	 */
	@RequestMapping(value = "/api/taskList{userId}")
	public List<Task> getUserTasksList(@PathVariable int userId) {
		List<Task> taskList = new ArrayList<Task>();
		User user = userService.findById(userId);
		taskList.addAll(user.getUserTasks());
		return taskList;
	}

	/**
	 * Get all tasks in story
	 *
	 * @param storyId id of story
	 * @return List of tasks
	 */
	@RequestMapping(value = "/api/storyTasks{storyId}")
	public List<Task> iterationList(@PathVariable int storyId) {
		return taskDao.getByStoryId(storyId);
	}



}

