package com.taskmgr.dao;

import com.taskmgr.model.Iteration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-18.
 */

public interface IterationDao {

	/**
	 * Get iterations by its project id
	 *
	 * @param projectId project id
	 * @return list of all iteration in project
	 */
	@Transactional
	List<Iteration> getByProjectId(int projectId);

	/**
	 * Get iteration by its id
	 * @param iterationId iteration id
	 * @return requested iteration
	 */
	@Transactional
	Iteration getById(int iterationId);

	/**
	 * Save iteration with change its id !!
	 * @param i iteration
	 */
	@Transactional
	void saveOrUpdate(Iteration i);

	/**
	 * Delete iteration
	 * @param i iteration
	 */
	@Transactional
	void delete(Iteration i);
}
