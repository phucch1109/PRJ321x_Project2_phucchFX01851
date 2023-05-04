package com.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="applyposts")
public class ApplyPost {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="dateCreated")
	private Date dateCreated;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	private User user;
	
	@Column(name="status")
	private int status;
	
	@Column(name="text")
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	public ApplyPost() {
		super();
	}

	public ApplyPost(int id, Date dateCreate, User user, int status, String text, Post post) {
		super();
		this.id = id;
		this.dateCreated = dateCreate;
		this.user = user;
		this.status = status;
		this.text = text;
		this.post = post;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreated = dateCreate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
}
