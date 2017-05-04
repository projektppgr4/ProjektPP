package com.taskmgr.service;


import com.taskmgr.model.User;

import java.util.List;


/**
 * Created by Akai on 2017-04-03.
 */


public interface UserService {

	User findById(int id);

	User findBySso(String sso);

	void saveUser(User user);

	List<User> findAll();

}