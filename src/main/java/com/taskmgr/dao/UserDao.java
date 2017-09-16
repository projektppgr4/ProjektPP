package com.taskmgr.dao;

import com.taskmgr.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-04.
 */
public interface UserDao {

	/**
	 * Get user by its id
	 *
	 * @param id user id
	 * @return user
	 */
	@Transactional
	User findById(int id);

	/**
	 * Get user by spring security login
	 * @param sso spring security login
	 * @return user
	 */
	@Transactional
	User findBySSO(String sso);

	/**
	 * Edit user with change its userId!!!
	 * @param user user
	 */
	@Transactional
	void saveOrUpdate(User user);

	/**
	 * Get all users in database
	 * @return list of all users in database
	 */
	@Transactional
	List<User> findAll();

	/**
	 * Search database to find users with surname begin in typed string
	 * @param lastName begining string of user surname
	 * @return List of user with surname begins in typed string
	 */
	@Transactional
	List<User> findByLastNameBegin(String lastName);

	/**
	 * Delete user from database
	 * @param user user to delete
	 */
	@Transactional
	void delete(User user);

}
