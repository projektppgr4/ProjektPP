package com.taskmgr.dao.Implementation;

import com.taskmgr.dao.AbstractDao;
import com.taskmgr.model.Iteration;
import com.taskmgr.model.Project;
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
@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDao<Integer, Project> implements com.taskmgr.dao.ProjectDao {

	@Transactional
	public Project getById(int id) {
		return getByKey(id);

	}


	@Override
	public void delete(Project project) {
		for (Iteration iteration : project.getIterations()) {
			for (Story story : iteration.getStories()) {
				for (Task task : story.getTasks()) {
					getSession().delete(task);
				}
				getSession().delete(story);
			}
			getSession().delete(iteration);
		}

		getSession().delete(project);
	}


	@Transactional
	public List<Project> findAllUserProjects(String ssoid) {

		Criteria criteria = getSession().createCriteria(Project.class);
		Criteria suppCrit = criteria.createCriteria("user");
		suppCrit.add(Restrictions.eq("ssoId", ssoid));
		suppCrit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
}
