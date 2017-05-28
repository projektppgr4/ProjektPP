package com.taskmgr.dao;

import com.taskmgr.model.Story;
import com.taskmgr.model.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-03-29.
 */

public interface TaskDao {
	@Transactional
	List<Task> findAll();

	@Transactional
	Task getById(int id);

	@Transactional
	void delete(Task t);

	@Transactional
	void saveOrUpdate(Task t);

	@Transactional
	List<Story> getByStoryId(int iterationId);

}
