package com.taskmgr.controller.Rest;

import com.taskmgr.dao.ProjectDao;
import com.taskmgr.dao.UserDao;
import com.taskmgr.model.Project;
import com.taskmgr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Akai on 2017-05-29.
 */

@RestController
public class ProjectRestController {


	private ProjectDao projectDao;
	private UserDao userDao;

	@Autowired
	public ProjectRestController(ProjectDao projectDao, UserDao userDao) {
		this.projectDao = projectDao;
		this.userDao = userDao;
	}

	/**
	 * Get list of user projects
	 *
	 * @param userId id of user
	 * @return list of user projects
	 */
	@RequestMapping(value = "/api/projectList{userId}")
	public List<Project> taskArrayList(@PathVariable int userId) {
		return projectDao.findAllUserProjectsByUserId(userId);
	}

	/**
	 * Create new project to actually logged user
	 * @param project    configured project
	 * @param principal logged user
	 * @return Project and server response
	 */
	@PostMapping(value = "/api/project")
	public ResponseEntity<Project> createProject(@RequestBody Project project, Principal principal) {
		User owner = userDao.findBySSO(principal.getName());
		project.setUser(owner);
		projectDao.saveOrUpdate(project);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

	/**
	 * Delete project from database
	 *
	 * @param projectId id of project
	 * @return server response status
	 */
	@DeleteMapping(value = "/api/project{projectId}", consumes = {"application/json"})
	public ResponseEntity deleteProject(@PathVariable int projectId) {
		projectDao.delete(projectDao.getById(projectId));
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	/**
	 * Edit parameters of project without change it id
	 * @param project value to insert
	 * @return project and server status response
	 */

	@PutMapping(value = "/api/project", consumes = {"application/json"})
	public ResponseEntity<Project> editProject(@RequestBody Project project) {
		//TODO check project to exist in database
		projectDao.edit(project);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

}
