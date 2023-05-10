package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.ApplyPost;

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
	
	
	@Override
	public List<ApplyPost> getApplyPostsByPostId(int postId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<ApplyPost> query = currentSession.createQuery("FROM ApplyPost where post.id =: postId",ApplyPost.class);
		query.setParameter("postId", postId);
		List<ApplyPost> applyPosts = query.getResultList();
		return applyPosts;
	}


	@Override
	public ApplyPost getApplyPostById(int applyPostId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<ApplyPost> query = currentSession.createQuery("FROM ApplyPost where id =: id",ApplyPost.class);
		query.setParameter("id", applyPostId);
		ApplyPost applyPost = query.getSingleResult();
		return applyPost;
	}
	
	@Override
	public void updateApplyPost(ApplyPost applyPost) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.update(applyPost);
	}
}
