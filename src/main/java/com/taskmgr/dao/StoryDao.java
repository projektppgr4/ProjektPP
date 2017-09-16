package com.taskmgr.dao;

import com.taskmgr.model.Story;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-12.
 */
public interface StoryDao {

	/**
	 * Use to edit story without change storyId
	 *
	 * @param story new values to assign
	 */
	@Transactional
	void edit(Story story);

	/**
	 * Use to edit story with !!!! change is storyId
	 *
	 * @param s story
	 */
	@Transactional
	void saveOrUpdate(Story s);

	/**
	 * Get story by its id
	 * @param id story id
	 * @return Story
	 */
	@Transactional
	Story getById(int id);

	/**
	 * Delete story from database
	 * @param s stary to dalete
	 */
	@Transactional
	void delete(Story s);

	/**
	 * Get list of all story in iteration
	 * @param iterationId id of iteration
	 * @return list of all stories in iteration
	 */
	@Transactional
	List<Story> getByIterationId(int iterationId);
}
