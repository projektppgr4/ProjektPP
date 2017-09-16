package com.taskmgr.controller.Rest;

import com.taskmgr.model.Task;
import com.taskmgr.model.User;
import com.taskmgr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Akai on 2017-05-27.
 */

@RestController
public class UserRestController {


	private UserService userService;

	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Login to server
	 *
	 * @param user values to login
	 * @return user and response of server status
	 */
	@PostMapping(value = "/api/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		return new ResponseEntity<User>(userService.findBySso(user.getSsoId()), HttpStatus.OK);
	}

	/**
	 * Get all user in database
	 * @return List of all user in database
	 */
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/api/users")
	public ResponseEntity<List<User>> usersList() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	/**
	 * Register new user in database
	 * @param user new user values
	 * @return user and response of server status
	 */
	@PostMapping(value = "/api/users")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		if (userService.findBySso(user.getSsoId()) != null) {
			//TODO zrobic zeby dzialalo
			return new ResponseEntity<Error>(new Error("Blad"), HttpStatus.CONFLICT);
		}
		userService.saveUser(user);
		return new ResponseEntity<User>(userService.findBySso(user.getSsoId()), HttpStatus.OK);
	}

	/**
	 * Assign new task to user
	 * @param userId id of user
	 * @param task to assign
	 * @return user and response of server status
	 */
	@PostMapping(value = "/api/user{userId}/task")
	public ResponseEntity<?> AssignTask(@PathVariable int userId, @RequestBody Task task) {
		User user = userService.findById(userId);
		user.getUserTasks().add(task);
		userService.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}


	/**
	 * Delete user from database
	 *
	 * @param userId id of user
	 * @return server status response
	 */
	@DeleteMapping(value = "/api/users{userId}")
	public HttpStatus deleteUser(@PathVariable int userId) {
		userService.deleteById(userId);
		return HttpStatus.OK;
	}


	public class Error {
		private String message;
		public Error(String message) {
			this.message = message;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}

}
