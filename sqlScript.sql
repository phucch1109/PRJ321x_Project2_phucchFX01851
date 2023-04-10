DROP DATABASE  IF EXISTS job_hunt_data;
CREATE DATABASE  IF NOT EXISTS job_hunt_data;
USE job_hunt_data;
CREATE TABLE users (
id int auto_increment primary key,
  username varchar(50) NOT NULL,
  password varchar(68) NOT NULL,
  enabled tinyint(1) NOT NULL,
  PRIMARY KEY (id)
);
INSERT INTO users
VALUES 
('kaho','{bcrypt}$2a$12$a/h9d9AU7.4Tdap2oZUq.u4G5bH2PbHFImGrhlaEyUOkX8QDzQBRy',1),
('alan','{bcrypt}$2a$12$a/h9d9AU7.4Tdap2oZUq.u4G5bH2PbHFImGrhlaEyUOkX8QDzQBRy',1),
('hapi','{bcrypt}$2a$12$MHnB18Pz1KSrfy4L.spR6O6uSg8YVUYXxImgUj/zFKOlNt46UtTbq',1);


CREATE TABLE roles (
  id int auto_increment primary key,
  name varchar(50) NOT NULL
) ;
INSERT INTO roles
VALUES 
(1,'RECRUITER'),
(2,'JOBSEEKER'),
(3,'ROLE_ADMIN');

CREATE TABLE user_role (
user_id int ,
role_id int,
CONSTRAINT userRoleId_fk FOREIGN KEY (user_id) REFERENCES users(id),
CONSTRAINT roleUserId_fk FOREIGN KEY (role_id) REFERENCES role(id)
PRIMARY KEY (user_id,role_id)
)
INSERT INTO user_role ((1,1),(1,2),
)


-- 1 user can have many version of CV
CREATE TABLE cv_files (
id int auto_increment primary key,
name nvarchar(255),
dateCreated date,
fromUsername varchar(50) NOT NULL,
CONSTRAINT userCv_fk FOREIGN KEY (fromUsername) REFERENCES users(username)
);

CREATE TABLE avatar_files (
id int auto_increment primary key,
name varchar(255),
dateCreated date,
fromUsername varchar(50),
CONSTRAINT userAvatar FOREIGN KEY (fromUsername) REFERENCES users(username)
);

CREATE TABLE companies (
id int auto_increment primary key,
name nvarchar(255),
address nvarchar(255),
phoneNumber varchar(255),
description nvarchar(255)
);

-- cvId and avatarId mapped to latest/newest version
CREATE TABLE user_profile
(
id int auto_increment primary key,
email nvarchar(255),
fullName nvarchar(255),
address nvarchar(255),
phoneNumber varchar(255),
description nvarchar(255),
cvId int,
username varchar(50),
avatarId int,
CONSTRAINT userCurrCV_fk FOREIGN KEY (cvId) REFERENCES cv_files (id),
CONSTRAINT userCurrAvatar_fk FOREIGN KEY (avatarId) REFERENCES avatar_files (id),
CONSTRAINT userCurrProfile_fk FOREIGN KEY (username) REFERENCES users(username)
);

-- job type
CREATE TABLE job_type (
id int primary key,
name nvarchar(255),
dateCreated date 
);
INSERT INTO job_type
VALUES 
(1,'fulltime',curdate()),
(2,'parttime',curdate()),
(3,'freelancer',curdate());

CREATE TABLE category (
id int auto_increment primary key,
name nvarchar(255),
dateCreated date
);
INSERT INTO category
VALUES 
(1,'ect...',curdate()),
(2,'Java',curdate()),
(3,'.NET',curdate()),
(4,'#C',curdate()),
(5,'Android',curdate()),
(6,'Unreal Engine',curdate()),
(7,'NodeJS',curdate()),
(8,'PHP',curdate());


CREATE TABLE post (
id int auto_increment primary key,
title nvarchar(255),
experience nvarchar(255),
number_of_recruit int,
salary nvarchar(255),
categoryId int,
expireDate date,
username varchar(50),
dateCreated date,
job_typeId int,
CONSTRAINT userPost_fk FOREIGN KEY (username) REFERENCES users (username),
CONSTRAINT postCategory_fk FOREIGN KEY (categoryId) REFERENCES category (id),
CONSTRAINT jobTypePost_fk FOREIGN KEY (job_typeId) REFERENCES job_type (id)
)