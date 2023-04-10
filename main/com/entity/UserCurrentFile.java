package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_current_file")
public class UserCurrentFile {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private int id;
@OneToOne
@JoinColumn(name = "user_id")
private User user;
@OneToOne
@JoinColumn(name="avatar_id")
private AvatarFile avatarFile;
@OneToOne
@JoinColumn(name="cvFile_id")
private CvFile cvFile;
	
}
