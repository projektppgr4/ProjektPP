package com.taskmgr.controller;

import com.taskmgr.dao.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Akai on 2017-03-29.
 */
@Deprecated
@RestController
public class TaskRestController {

	@Autowired
	private TaskDao taskDao;

	@GetMapping("/Task")
	public List getTask() {
		return taskDao.findAll();
	}

}
