package com.taskmgr.dao.Implementation;

import com.taskmgr.dao.AbstractDao;
import com.taskmgr.dao.StoryDao;
import com.taskmgr.model.Story;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
	public void edit(Story story) {
		Story storyToEdit = getById(story.getId());
		storyToEdit.setName(story.getName());
		saveOrUpdate(storyToEdit);
	}

	@Transactional
	public Story getById(int id) {
		Hibernate.initialize(getByKey(id)
				.getIteration().getTaskStatuses());
		return getByKey(id);

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
