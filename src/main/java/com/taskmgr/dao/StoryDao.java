package com.taskmgr.dao;

import com.taskmgr.model.Story;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-12.
 */
public interface StoryDao {

	@Transactional
	void saveOrUpdate(Story s);

	@Transactional
	Story getById(int id);

	@Transactional
	void delete(Story s);

	@Transactional
	List<Story> getByIterationId(int iterationId);
}
