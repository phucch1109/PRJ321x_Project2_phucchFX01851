package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApplyPostDaoImpl implements ApplyPostDao{
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public int deleteAllByPostId(int postId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("delete FROM ApplyPost where post.id =: postId");
		query.setParameter("postId", postId);
		int output = query.executeUpdate();
		return output;
	}
}
