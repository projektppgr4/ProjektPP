package com.taskmgr.validator;

import com.taskmgr.model.User;
import com.taskmgr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Akai on 2017-04-06.
 */

@Component
public class UserValidator implements Validator {

	//TODO validacja
	@Autowired
	UserService userService;

	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssoId", "NotEmpty");
		if (user.getSsoId().length() < 6 || user.getSsoId().length() > 32) {
			errors.rejectValue("ssoId", "Size.userForm.ssoId");
		}
		if (userService.findBySso(user.getSsoId()) != null) {
			errors.rejectValue("ssoId", "Duplicate.userForm.ssoId");
		}

	}
}
