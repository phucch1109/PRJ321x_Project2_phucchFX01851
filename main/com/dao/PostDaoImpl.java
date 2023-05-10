package com.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Company;
import com.entity.Post;

@Repository
public class PostDaoImpl implements PostDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	//get top 5 newest posts
	public List<Post> top5Post() {
		Session currentSession = sessionFactory.getCurrentSession(); 
		Query<Post> query = currentSession.createQuery("from Post order by dateCreated" , Post.class);
		query.setMaxResults(5);
		List<Post> posts = query.getResultList();
		return posts;
	}
	
	//get all posts
	@Override
	public List<Post> getPosts() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Post> query = currentSession.createQuery("from Post" , Post.class);
		List<Post> posts = query.getResultList();
		return posts;
	}
	
	
	//count posts by category
	@Override
	public int getTotalPostByCategory(int categoryId) {
		Session currnetSession = sessionFactory.getCurrentSession();
		Query<Long> query = currnetSession.createQuery("SELECT COUNT (*) FROM Post where category.id =: categoryId", Long.class);
		query.setParameter("categoryId", categoryId);
		long output = query.getSingleResult();
		return (int)output;
	}
	
	
	//get user's post (without pagination)
	@Override
	public List<Post> getPostsByUserId(int userId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Post> query = currentSession.createQuery("from Post where user.id =: userId" , Post.class);
		query.setParameter("userId", userId);
		List<Post> posts = query.getResultList();
		return posts;
	}
	
	
	//get user's post with pagination (max result = 5)
	@Override
	public List<Post> getPostByUserId(int userId,int index) {
		int maxResult = 5;
		int offset = (index-1) *maxResult;
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Post> query = currentSession.createQuery("from Post where user.id =: userId" , Post.class);
		query.setParameter("userId", userId);
		query.setFirstResult(offset);
		query.setMaxResults(maxResult);
		List<Post> posts = query.getResultList();
		return posts;
	}
	
	//get number of user's posts
	@Override
	public int getCountByUserId(int userId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Long> query = currentSession.createQuery("SELECT COUNT (*) FROM Post where user.id =: userId", Long.class);
		query.setParameter("userId", userId);
		long output = query.getSingleResult();
		return (int)output;
	}
	
	
	@Override
	public int deletePost(int postId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("delete FROM Post where id =: postId");
		query.setParameter("postId", postId);
		int output = query.executeUpdate();
		return output;
	}
	
	
	@Override
	public Post getPostById(int postId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Post> query = currentSession.createQuery("FROM Post where id =: postId" , Post.class);
		query.setParameter("postId", postId);
		Post post = query.getSingleResult();
		return post;
	}
	
	@Override
	public int savePost(Post post) {
		Session currentSession = sessionFactory.getCurrentSession();		
		return (Integer) currentSession.save(post);
	}
	
	@Override
	public void updatePost(Post post) {
		Session currentSession = sessionFactory.getCurrentSession();	
		currentSession.update(post);
	}
	
	
	//get company search's post with pagination (max result = 5)
		@Override
		public List<Post> getPostsByCompanyName(String name,int index) {
			int maxResult = 5;
			int offset = (index-1) *maxResult;
			Session currentSession = sessionFactory.getCurrentSession();
			Query<Post> query = currentSession.createQuery("from Post where company.name like '%:name%'" , Post.class);
			query.setParameter("name", name);)
			query.setFirstResult(offset);
			query.setMaxResults(maxResult);
			List<Post> posts = query.getResultList();
			return posts;
		}
}
