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

	/**
	 * Get all available task statues of chosen iteration
	 *
	 * @param iterationId id number of iteration
	 * @return list of available statues in this iteration
	 */
	@RequestMapping(value = "/api/iteration{iterationId}/statuses")
	public List<TaskStatus> getAvailableStates(@PathVariable int iterationId) {
		Iteration iteration = iterationDao.getById(iterationId);
		List<TaskStatus> stateList = new ArrayList<TaskStatus>();
		stateList.addAll(iteration.getTaskStatuses());
		return stateList;
	}

	/**
	 * Send list of all iteration in chosen project
	 *
	 * @param projectId id of project
	 * @return List of iterations
	 */
	@RequestMapping(value = "/api/iterationList{projectId}")
	public ResponseEntity<List<Iteration>> iterationList(@PathVariable int projectId) {
		List<Iteration> iterationList = iterationDao.getByProjectId(projectId);

		return new ResponseEntity<List<Iteration>>(iterationList, HttpStatus.OK);
	}

	/**
	 * Add new iteration to project
	 *
	 * @param iteration body of iteration with gonna be add
	 * @param projectId id of project
	 * @return iteration and server response status
	 */
	@PostMapping(value = "/api/iteration{projectId}", consumes = {"application/json"})
	public ResponseEntity<Iteration> addIteration(@RequestBody Iteration iteration, @PathVariable int projectId) {
		iteration.setProject(projectDao.getById(projectId));
		iterationDao.saveOrUpdate(iteration);
		return new ResponseEntity<Iteration>(iteration, HttpStatus.OK);
	}

	/**
	 * Delete requested iteration from server
	 *
	 * @param iterationId id of iteration
	 * @return server response status
	 */
	@DeleteMapping(value = "/api/iteration{iterationId}", consumes = {"application/json"})
	public ResponseEntity deleteIteration(@PathVariable int iterationId) {
		iterationDao.delete(iterationDao.getById(iterationId));
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

}
