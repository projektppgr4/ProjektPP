package com.taskmgr.dao;

import com.taskmgr.model.Iteration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-18.
 */

public interface IterationDao {

	@Transactional
	List<Iteration> getByProjectId(int projectId);

	@Transactional
	Iteration getById(int id);
}
