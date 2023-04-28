package com.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Post;

@Repository
public class PostDaoImpl implements PostDao{
	@Autowired
	SessionFactory sessionFactory;
	
	
	//get top 5 newest posts
	public List<Post> top5Post() {
		Session currentSession = sessionFactory.getCurrentSession(); 
		Query<Post> query = currentSession.createQuery("from Post order by dateCreated" , Post.class);
		query.setMaxResults(5);
		List<Post> posts = query.getResultList();
		return posts;
	}
	
	public List<Post> getPosts() {
		Session currentSession = sessionFactory.getCurrentSession(); 
		Query<Post> query = currentSession.createQuery("from Post" , Post.class);
		List<Post> posts = query.getResultList();
		return posts;
	}
	
	public int getTotalPostByCategory(int categoryId) {
		Session currnetSession = sessionFactory.getCurrentSession();
		Query<Long> query = currnetSession.createQuery("SELECT COUNT (*) FROM Post where category.id =: categoryId", Long.class);
		query.setParameter("categoryId", categoryId);
		long output = query.getSingleResult();
		return (int)output;
	}
	
	
	
}
