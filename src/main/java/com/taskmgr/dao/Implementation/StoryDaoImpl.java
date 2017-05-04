package com.taskmgr.dao.Implementation;

import com.taskmgr.dao.AbstractDao;
import com.taskmgr.dao.StoryDao;
import com.taskmgr.model.Story;
import com.taskmgr.model.Task;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-12.
 */

@Transactional
@Repository("storyDao")
public class StoryDaoImpl extends AbstractDao<Integer, Story> implements StoryDao {

	@Transactional
	public Story getById(int id) {
		return getByKey(id);

	}

	@Override
	public void delete(Story story) {
		for (Task task : story.getTasks()) {
			getSession().delete(task);

		}
		getSession().delete(story);
	}

	@Transactional
	public List<Story> getByIterationId(int iterationId) {
		Criteria criteria = createEntityCriteria();
		Criteria suppCrit = criteria.createCriteria("iteration");
		suppCrit.add(Restrictions.eq("id", iterationId));
		suppCrit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
}
