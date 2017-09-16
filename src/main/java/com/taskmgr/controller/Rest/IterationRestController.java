package com.taskmgr.controller.Rest;

import com.taskmgr.dao.IterationDao;
import com.taskmgr.dao.ProjectDao;
import com.taskmgr.model.Iteration;
import com.taskmgr.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akai on 2017-05-31.
 */

@RestController
public class IterationRestController {

	private IterationDao iterationDao;
	private ProjectDao projectDao;

	@Autowired
	public IterationRestController(IterationDao iterationDao, ProjectDao projectDao) {
		this.iterationDao = iterationDao;
		this.projectDao = projectDao;
	}

	@RequestMapping(value = "/api/iteration{id}/statuses")
	public List<TaskStatus> getAviableStates(@PathVariable int id) {
		Iteration iteration = iterationDao.getById(id);
		List<TaskStatus> stateList = new ArrayList<TaskStatus>();
		stateList.addAll(iteration.getTaskStatuses());
		return stateList;
	}


	@RequestMapping(value = "/api/iterationList{id}")
	public ResponseEntity<List<Iteration>> iterationList(@PathVariable int id) {
		List<Iteration> iterationList = iterationDao.getByProjectId(id);

		return new ResponseEntity<List<Iteration>>(iterationList, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/iteration{id}", method = RequestMethod.POST, consumes = {"application/json"})
	public ResponseEntity<Iteration> addStory(@RequestBody Iteration iteration, @PathVariable int id) {
		iteration.setProject(projectDao.getById(id));
		iterationDao.saveOrUpdate(iteration);
		return new ResponseEntity<Iteration>(iteration, HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/iteration{id}", consumes = {"application/json"})
	public ResponseEntity deleteIteration(@PathVariable int id) {
		iterationDao.delete(iterationDao.getById(id));
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

}
