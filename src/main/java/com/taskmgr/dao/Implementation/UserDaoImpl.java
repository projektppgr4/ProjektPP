package com.taskmgr.dao.Implementation;

import com.taskmgr.dao.AbstractDao;
import com.taskmgr.dao.UserDao;
import com.taskmgr.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Akai on 2017-04-04.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		return getByKey(id);
	}

	public User findBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		return (User) crit.uniqueResult();
	}

	@Transactional
	public List<User> findAll() {
		@SuppressWarnings("unchecked")
		List<User> usersList = (List<User>) getSession()
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return usersList;
	}

	@Transactional
	public List<User> findByLastNameBegin(String lastName) {
		List<User> usersList = (List<User>) getSession()
				.createCriteria(User.class)
				.add(Restrictions.like("lastName", lastName + "%"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return usersList;
	}


}

