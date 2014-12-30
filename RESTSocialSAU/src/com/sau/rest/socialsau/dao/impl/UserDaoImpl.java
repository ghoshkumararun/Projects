package com.sau.rest.socialsau.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sau.rest.socialsau.dao.UserDao;
import com.sau.rest.socialsau.dto.User;
import com.sau.rest.socialsau.util.MD5;


@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao  {

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public User getUserJSON(String email, String password) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("password", MD5.getInstance(password)));
		return (User) criteria.uniqueResult();
	}

}
