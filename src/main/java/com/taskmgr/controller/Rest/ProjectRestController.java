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

	@Autowired
	ProjectDao projectDao;

	@Autowired
	UserDao userDao;


	@RequestMapping(value = "/api/projectList{id}")
	public List<Project> taskArrayList(@PathVariable int id) {
		List<Project> projectList = projectDao.findAllUserProjectsByUserId(id);

		return projectList;
	}

	@PostMapping(value = "/api/project")
	public ResponseEntity<Project> createProject(@RequestBody Project project, Principal principal) {
		User owner = userDao.findBySSO(principal.getName());
		project.setUser(owner);
		projectDao.saveOrUpdate(project);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/project{id}", consumes = {"application/json"})
	public ResponseEntity deleteProject(@PathVariable int id) {
		projectDao.delete(projectDao.getById(id));
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "/api/project", consumes = {"application/json"})
	public ResponseEntity<Project> putProject(@RequestBody Project project) {
		//TODO check project to exist in database
		projectDao.edit(project);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

}
