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
	
	public List<Category> getCategories() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Category> query = currentSession.createQuery("FROM Category",Category.class);
		List<Category> categories = query.getResultList();
		return categories;
	}
	
	public List<TopCategoryResult> getTop4Category() {
		Session currentSession = sessionFactory.getCurrentSession();
		TypedQuery<TopCategoryResult> query = currentSession.createQuery("SELECT NEW com.subEntity.TopCategoryResult (cat.name,COUNT(p.numberOfRecruit)) FROM Category AS cat INNER JOIN Post AS p ON cat.id = p.category.id GROUP BY cat.id",TopCategoryResult.class);
		query.setMaxResults(4);
		List<TopCategoryResult> temp = query.getResultList();	
		return temp;
	}
	
	
}
