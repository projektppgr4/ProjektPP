package com.taskmgr.dao.Implementation;

import com.taskmgr.dao.AbstractDao;
import com.taskmgr.dao.IterationDao;
import com.taskmgr.model.Iteration;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-18.
 */

@Repository
public class IterarionDaoImpl extends AbstractDao<Integer, Iteration> implements IterationDao {


	@Transactional
	public Iteration getById(int iterationId) {
		//TODO split to two functions
		Hibernate.initialize(getByKey(iterationId)
				.getTaskStatuses());
		return getByKey(iterationId);

	}

	@Transactional
	public List<Iteration> getByProjectId(int projectId) {
		Criteria criteria = createEntityCriteria();
		Criteria suppCrit = criteria.createCriteria("project");
		suppCrit.add(Restrictions.eq("id", projectId));
		suppCrit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();

	}

}
