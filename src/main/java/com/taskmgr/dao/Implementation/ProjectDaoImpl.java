package com.taskmgr.dao.Implementation;

import com.taskmgr.dao.AbstractDao;
import com.taskmgr.model.Project;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-12.
 */

@Transactional
@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDao<Integer, Project> implements com.taskmgr.dao.ProjectDao {

	@Transactional
	public Project getById(int id) {
		return getByKey(id);

	}


	@Transactional
	public List<Project> findAllUserProjectsBySsoId(String ssoid) {

		Criteria criteria = getSession().createCriteria(Project.class);
		Criteria suppCrit = criteria.createCriteria("user");
		suppCrit.add(Restrictions.eq("ssoId", ssoid));
		suppCrit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Transactional
	public List<Project> findAllUserProjectsByUserId(int id) {

		Criteria criteria = getSession().createCriteria(Project.class);
		Criteria suppCrit = criteria.createCriteria("user");
		suppCrit.add(Restrictions.eq("id", id));
		suppCrit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

}
