package com.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.JobType;

@Repository
public class JobTypeDaoImpl implements JobTypeDao {
@Autowired
SessionFactory sessionFactory;

@Override
//get all jobtype
public List<JobType> getJobTypes() {
	Session currentSession = sessionFactory.getCurrentSession();
	Query<JobType> query = currentSession.createQuery("FROM JobType",JobType.class);
	List<JobType> jobTypes = query.getResultList();
	return jobTypes;
}

@Override
public JobType getJobTypeById(int id) {
	Session currentSession = sessionFactory.getCurrentSession();
	Query<JobType> query = currentSession.createQuery("FROM JobType where id =:id",JobType.class);
	query.setParameter("id", id);
	JobType jobTypes = query.getSingleResult();
	return jobTypes;
}
	
}
