package com.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username",updatable = false)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "description")
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	@OneToMany(mappedBy = "user" ,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Post> posts;
	
	@ManyToOne
	@JoinColumn(name = "company_id",updatable = false)
	private Company company;
	
	@OneToMany(mappedBy = "user")
	private List<AvatarFile> avatarFiles;
	
	@OneToOne
	@JoinColumn(name="cvFile_id")
	private CvFile cvFile;
	
	@OneToOne
	@JoinColumn(name="avatar_id")
	private ApplyPost applyPost;
	
	
	@OneToMany(mappedBy = "user", cascade= CascadeType.ALL)
	private List<CvFile> cvFiles;
	
	@OneToMany(mappedBy = "user")
	private List<ApplyPost> applyposts;
	
	public User() {
	}


	public int getId() {
		return id;
	}

	public User(int id, String userName, String password, String firstName, String lastName, String email,
		String phoneNumber, String description, Collection<Role> roles, List<Post> posts, Company company,
	 List<AvatarFile> avatarFiles, List<CvFile> cvFiles) {
	super();
	this.id = id;
	this.userName = userName;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.description = description;
	this.roles = roles;
	this.posts = posts;
	this.company = company;
	this.avatarFiles = avatarFiles;
	this.cvFiles = cvFiles;
}



	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}

	public List<AvatarFile> getAvatarFiles() {
		return avatarFiles;
	}



	public void setAvatarFiles(List<AvatarFile> avatarFiles) {
		this.avatarFiles = avatarFiles;
	}



	public List<CvFile> getCvFiles() {
		return cvFiles;
	}



	public void setCvFiles(List<CvFile> cvFiles) {
		this.cvFiles = cvFiles;
	}


	public List<ApplyPost> getApplyposts() {
		return applyposts;
	}


	public void setApplyposts(List<ApplyPost> applyposts) {
		this.applyposts = applyposts;
	}
	

}
