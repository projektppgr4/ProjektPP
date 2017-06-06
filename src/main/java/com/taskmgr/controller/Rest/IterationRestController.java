package com.taskmgr.controller.Rest;

import com.taskmgr.dao.IterationDao;
import com.taskmgr.model.Iteration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Akai on 2017-05-31.
 */

@RestController
public class IterationRestController {

	@Autowired
	IterationDao iterationDao;

	@RequestMapping(value = "/api/iterationList{id}")
	public List<Iteration> iterationList(@PathVariable int id) {
		List<Iteration> iterationList = iterationDao.getByProjectId(id);

		return iterationList;
	}


}
