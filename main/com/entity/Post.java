package com.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="posts")
public class Post {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
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
@Column(name="description")
private String description;

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

@OneToMany(mappedBy = "post" ,cascade= CascadeType.ALL)
private List<ApplyPost> applyPosts;

public Post() {
	super();
}





public Post(int id, String title, String experience, int numberOfRecruit, String salary, Date expireDate,
		Date dateCreated, String description, User user, Category category, JobType jobType, Company company,
		List<ApplyPost> applyPosts) {
	super();
	this.id = id;
	this.title = title;
	this.experience = experience;
	this.numberOfRecruit = numberOfRecruit;
	this.salary = salary;
	this.expireDate = expireDate;
	this.dateCreated = dateCreated;
	this.description = description;
	this.user = user;
	this.category = category;
	this.jobType = jobType;
	this.company = company;
	this.applyPosts = applyPosts;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
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

public List<ApplyPost> getApplyPosts() {
	return applyPosts;
}

public void setApplyPosts(List<ApplyPost> applyPosts) {
	this.applyPosts = applyPosts;
}





@Override
public String toString() {
	return "Post [id=" + id + ", title=" + title + ", experience=" + experience + ", numberOfRecruit=" + numberOfRecruit
			+ ", salary=" + salary + ", expireDate=" + expireDate + ", dateCreated=" + dateCreated + ", description="
			+ description + ", user=" + user + ", category=" + category + ", jobType=" + jobType + ", company="
			+ company + ", applyPosts=" + applyPosts + "]";
}


}
