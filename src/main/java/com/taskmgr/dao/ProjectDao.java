package com.taskmgr.dao;

import com.taskmgr.model.Project;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-12.
 */
public interface ProjectDao {
	/**
	 * Get project by its id
	 *
	 * @param id id of project
	 * @return project
	 */
	@Transactional
	Project getById(int id);

	/**
	 * Edit project with !!! change its id
	 * @param p project
	 */
	@Transactional
	void saveOrUpdate(Project p);

	/**
	 * Delete project
	 * @param p project
	 */
	@Transactional
	void delete(Project p);

	/**
	 * Find all user project by user spring security id
	 * @param ssoid user spring security id
	 * @return List of all user projects
	 */
	@Transactional
	List<Project> findAllUserProjectsBySsoId(String ssoid);

	/**
	 * Find all user project by user id
	 * @param id user id
	 * @return List of all user projects
	 */
	@Transactional
	List<Project> findAllUserProjectsByUserId(int id);

	/**
	 * Use to edit project without change projectId
	 *
	 * @param project new values to assign
	 */
	@Transactional
	void edit(Project project);
}
