package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {
	@Autowired
	SessionFactory sessionFactory;
	
	// get top 5 companies by number of post
	public List<Company> top5Companies() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Company> theQuery = currentSession.createQuery("from Company c order by size(c.posts) desc", Company.class);
		theQuery.setMaxResults(5);
		List<Company> companiesList = null;
		companiesList = theQuery.getResultList();
		return companiesList;
	}
	
	public List<Company> getCompanies() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Company> theQuery = currentSession.createQuery("from Company c ", Company.class);
		List<Company> companiesList = theQuery.getResultList();
		return companiesList;
	}
	
	public Company getCompanyById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Company> theQuery = currentSession.createQuery("from Company c where id =: id ", Company.class);
		theQuery.setParameter("id", id);
		Company company = theQuery.getSingleResult();
		return company;
	}
	
	public int addNewCompany(String name) {
		Session currentSession = sessionFactory.getCurrentSession();
		Company company = new Company();
		company.setName(name);
		System.out.println(company);
		return (Integer) currentSession.save(company);
	}
	
	public void update(Company company) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.update(company);
	}
 	
}
