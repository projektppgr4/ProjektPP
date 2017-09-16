package com.taskmgr.dao;

import com.taskmgr.model.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-03-29.
 */

public interface TaskDao {

	/**
	 * Get all tasks in database
	 *
	 * @return List of all task in database
	 */
	@Transactional
	List<Task> findAll();

	/**
	 * Get task by its id
	 * @param id id of requested task
	 * @return task
	 */
	@Transactional
	Task getById(int id);

	/**
	 * Delete task from database
	 * @param t task
	 */
	@Transactional
	void delete(Task t);

	/**
	 * Use to edit task without change storyId
	 *
	 * @param task new values to assign
	 */
	@Transactional
	void edit(Task task);

	/**
	 * Use to edit with !!! change taskId
	 * @param t task
	 */
	@Transactional
	void saveOrUpdate(Task t);

	/**
	 * Get all tasks in story
	 * @param storyId story id
	 * @return List of all tasks in story
	 */
	@Transactional
	List<Task> getByStoryId(int storyId);

}
