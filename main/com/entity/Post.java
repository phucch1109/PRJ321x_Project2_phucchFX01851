package com.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post {
@Id
@Column(name="id")
private int id;
@Column(name="title")
private String title;
@Column(name="experience")
private String experience;
@Column(name = "number_of_recruit")
private int numberOfRecruit;
@Column(name = "salary")
private String salary;
@Column(name= "expireDate")
private Date expireDate;
@Column(name= "dateCreated")
private Date dateCreated;

@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
		 CascadeType.DETACH, CascadeType.REFRESH})
@JoinColumn(name = "user_id")
private User user;
@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
		 CascadeType.DETACH, CascadeType.REFRESH})
@JoinColumn(name = "categoryId")
private Category category;
@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
		 CascadeType.DETACH, CascadeType.REFRESH})
@JoinColumn(name = "job_typeId")
private JobType jobType;
@ManyToOne
@JoinColumn(name="company_id")
private Company company;

public Post() {
	super();
}




public Post(int id, String title, String experience, int numberOfRecruit, String salary, Date expireDate,
		Date dateCreated, User user, Category category, JobType jobType, Company company) {
	super();
	this.id = id;
	this.title = title;
	this.experience = experience;
	this.numberOfRecruit = numberOfRecruit;
	this.salary = salary;
	this.expireDate = expireDate;
	this.dateCreated = dateCreated;
	this.user = user;
	this.category = category;
	this.jobType = jobType;
	this.company = company;
}




public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}
public int getNumberOfRecruit() {
	return numberOfRecruit;
}
public void setNumberOfRecruit(int numberOfRecruit) {
	this.numberOfRecruit = numberOfRecruit;
}
public String getSalary() {
	return salary;
}
public void setSalary(String salary) {
	this.salary = salary;
}
public Date getExpireDate() {
	return expireDate;
}
public void setExpireDate(Date expireDate) {
	this.expireDate = expireDate;
}
public Date getDateCreated() {
	return dateCreated;
}
public void setDateCreated(Date dateCreated) {
	this.dateCreated = dateCreated;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public JobType getJobType() {
	return jobType;
}
public void setJobType(JobType jobType) {
	this.jobType = jobType;
}

public Company getCompany() {
	return company;
}

public void setCompany(Company company) {
	this.company = company;
}

}
