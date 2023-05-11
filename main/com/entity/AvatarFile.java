package com.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avatar_files")
public class AvatarFile {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name= "id")
private int id;
@Column(name = "name")
private String name;
@Column(name = "dateCreated")
private Date dateCreated;
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
@OneToOne(mappedBy = "applyPost")
private User selected;

}
