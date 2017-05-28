package com.taskmgr.service.Implementation;

/**
 * Created by Akai on 2017-04-04.
 */

import com.taskmgr.dao.UserDao;
import com.taskmgr.model.User;
import com.taskmgr.model.UserProfile;
import com.taskmgr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;


	public void deleteById(int id) {
		userDao.delete(userDao.findById(id));
	}

	public User findById(int id) {
		return userDao.findById(id);
	}

	public User findBySso(String sso) {
		return userDao.findBySSO(sso);
	}

	public void saveUser(User user) {
		userDao.saveOrUpdate(user);
		user.setUserProfiles(new HashSet<UserProfile>());
	}

	public List findAll() {
		return userDao.findAll();
	}

}