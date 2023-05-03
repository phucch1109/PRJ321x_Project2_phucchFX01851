package com.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Category;
import com.subEntity.TopCategoryResult;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	@Autowired
	SessionFactory sessionFactory;
	
	//get all categories
	public List<Category> getCategories() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Category> query = currentSession.createQuery("FROM Category",Category.class);
		List<Category> categories = query.getResultList();
		return categories;
	}
	
	//get top 4 categories that have most posts
	public List<TopCategoryResult> getTop4Category() {
		Session currentSession = sessionFactory.getCurrentSession();
		TypedQuery<TopCategoryResult> query = currentSession.createQuery("SELECT NEW com.subEntity.TopCategoryResult (cat.name,COUNT(p.numberOfRecruit)) FROM Category AS cat INNER JOIN Post AS p ON cat.id = p.category.id GROUP BY cat.id ORDER BY COUNT(p.numberOfRecruit) desc",TopCategoryResult.class);
		query.setMaxResults(4);
		List<TopCategoryResult> temp = query.getResultList();	
		return temp;
	}
	
	public Category getCategoryById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Category> query = currentSession.createQuery("FROM Category where id =: id",Category.class);
		query.setParameter("id", id);
		Category category = query.getSingleResult();
		return category;
	}
}
