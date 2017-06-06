package com.taskmgr.dao;

import com.taskmgr.model.Project;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-12.
 */
public interface ProjectDao {
	@Transactional
	Project getById(int id);

	@Transactional
	void saveOrUpdate(Project p);

	@Transactional
	void delete(Project p);

	@Transactional
	List<Project> findAllUserProjectsBySsoId(String ssoid);

	@Transactional
	List<Project> findAllUserProjectsByUserId(int id);
}
