package com.taskmgr.controller.Rest;

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

	@Autowired
	private UserService userService;

	@PostMapping(value = "/api/login")
	public ResponseEntity<User> login(@RequestBody User user) {


		return new ResponseEntity<User>(userService.findBySso(user.getSsoId()), HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	//TODO check user profile
	@GetMapping(value = "/api/users")
	public ResponseEntity<List<User>> userslist() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/api/users")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		User newUser = user;
		if (userService.findBySso(user.getSsoId()) != null) {
			//TODO zrobic zeby dzialalo
			return new ResponseEntity<Error>(new Error("Blad"), HttpStatus.CONFLICT);
		}
		userService.saveUser(newUser);

		return new ResponseEntity<User>(userService.findBySso(user.getSsoId()), HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/users{id}")
	public HttpStatus deleteUser(@PathVariable int id) {
		userService.deleteById(id);

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
