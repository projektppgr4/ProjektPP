package com.taskmgr.dao.Implementation;

import com.taskmgr.dao.AbstractDao;
import com.taskmgr.dao.TaskDao;
import com.taskmgr.model.Task;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-03-28.
 */

@Transactional
@Repository("taskDao")
public class TaskDaoImpl extends AbstractDao<Integer, Task> implements TaskDao {

	@Transactional
	public List<Task> findAll() {
		@SuppressWarnings("unchecked")
		List<Task> listTask = (List<Task>) getSession()
				.createCriteria(Task.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listTask;
	}

	@Transactional
	public Task getById(int id) {
		//TODO
		Hibernate.initialize(getByKey(id).getStory().getIteration().getTaskStatuses());
		return getByKey(id);

	}

	@Transactional
	public void edit(Task task) {
		Task taskToEdit = getById(task.getId());
		taskToEdit.setName(task.getName());
		taskToEdit.setDuration(task.getDuration());
		taskToEdit.setTaskStatus(task.getTaskStatus());
		saveOrUpdate(taskToEdit);
	}

	public List<Task> getByStoryId(int storyId) {
		Criteria criteria = createEntityCriteria();
		Criteria suppCrit = criteria.createCriteria("story");
		suppCrit.add(Restrictions.eq("id", storyId));
		suppCrit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

}
