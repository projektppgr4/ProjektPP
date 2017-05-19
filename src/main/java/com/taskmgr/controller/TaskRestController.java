package com.taskmgr.controller;

import com.taskmgr.model.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Akai on 2017-03-29.
 */

@RestController
public class TaskRestController {

	@RequestMapping(value = "/task")
	public ArrayList<Task> Test12() {
		Task task1 = new Task();
		task1.setName("Jas");
		task1.setId(50);
		task1.setDuration(10);
		Task task2 = new Task();
		task2.setId(80);
		task2.setName("Zosia");
		ArrayList<Task> out = new ArrayList<Task>();
		out.add(task1);
		out.add(task2);
		return out;


	}

}
