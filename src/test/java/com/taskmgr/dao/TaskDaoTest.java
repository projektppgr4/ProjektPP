package com.taskmgr.dao;

import com.taskmgr.config.SpringConfig;
import com.taskmgr.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Akai on 2017-04-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@WebAppConfiguration //musi byc bo inaczej zle laduje konfiguracje
@Rollback
@Transactional
public class TaskDaoTest {

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private StoryDao storyDao;


	@Test
	public void findAll() throws Exception {

		List<Task> insertList = new ArrayList<Task>();
		Task insert1 = new Task();
		insertList.add(insert1);

		Task insert2 = new Task();
		insertList.add(insert2);

		taskDao.saveOrUpdate(insert1);
		taskDao.saveOrUpdate(insert2);

		List<Task> resultList = taskDao.findAll();
		assertEquals(resultList, insertList);


	}

	@Test
	public void getById() throws Exception {
		Task test = taskDao.getById(1);
		assertEquals(null, test);
	}

	@Test
	public void delete() throws Exception {
		Task insert = new Task();
		insert.setId(1);
		taskDao.saveOrUpdate(insert);
		taskDao.delete(insert);

		Task test = taskDao.getById(1);
		assertEquals(null, test);
	}

	@Test
	public void saveOrUpdate() throws Exception {
		Task insert = new Task();
		insert.setId(1);
		taskDao.saveOrUpdate(insert);
		Task test = taskDao.getById(1);
		assertEquals(insert, test);
	}

}