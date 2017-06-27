package com.taskmgr.controller.Rest;

import com.taskmgr.dao.IterationDao;
import com.taskmgr.dao.ProjectDao;
import com.taskmgr.model.Iteration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Akai on 2017-05-31.
 */

@RestController
public class IterationRestController {

	@Autowired
	IterationDao iterationDao;

	@Autowired
	ProjectDao projectDao;

	@RequestMapping(value = "/api/iterationList{id}")
	public List<Iteration> iterationList(@PathVariable int id) {
		List<Iteration> iterationList = iterationDao.getByProjectId(id);

		return iterationList;
	}

	@RequestMapping(value = "/api/iteration{id}", method = RequestMethod.POST, consumes = {"application/json"})
	public ResponseEntity<Iteration> addStory(@RequestBody Iteration iteration, @PathVariable int id) {
		iteration.setProject(projectDao.getById(id));
		iterationDao.saveOrUpdate(iteration);
		return new ResponseEntity<Iteration>(iteration, HttpStatus.OK);
	}

}
