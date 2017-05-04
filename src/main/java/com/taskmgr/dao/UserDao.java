package com.taskmgr.dao;

import com.taskmgr.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-04.
 */
public interface UserDao {

	@Transactional
	User findById(int id);

	@Transactional
	User findBySSO(String sso);

	@Transactional
	void saveOrUpdate(User user);

	@Transactional
	List<User> findAll();
}
