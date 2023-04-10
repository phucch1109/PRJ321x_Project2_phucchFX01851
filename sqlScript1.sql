DROP DATABASE  IF EXISTS job_hunt_data;
CREATE DATABASE  IF NOT EXISTS job_hunt_data;
USE job_hunt_data;
--
-- Table structure for table `user`
--


CREATE TABLE companies (
id int auto_increment primary key,
name nvarchar(255),
address nvarchar(255),
phoneNumber varchar(255),
description nvarchar(255)
);
INSERT INTO companies (id,name,address,phoneNumber,description)
VALUES (1,"FPT software","Hoa Lac","0999239402389","VERY BIG COPR with alot of seeder"),
(2,"NFT company","Hanoi","2983485212","Greedy company"),
(3,"Scarlet Union","Onshore","12300402389","Implemented with anti air cut in"),
(4,"Abydos","Desert","402389000","Empty company with big snake hid in beneath"),
(5,"Gehenna ","Teyvat","1230211402389","Mostly handy man and some outlaw"),
(6,"Millennium Science","Space station","12300402389","Using cutting edge tech, but easily go bankrupt"),
(7,"Trinity","Church","1230040222389","Finding way to make human closer to god")
;




DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  password char(80) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  phoneNumber varchar(255),
  description nvarchar(255),
  company_id int,
  PRIMARY KEY (id),
  CONSTRAINT FK_USER_COMPANY FOREIGN KEY (company_id) 
  REFERENCES companies (id) 
) AUTO_INCREMENT=1;

INSERT INTO users (id,username,password,first_name,last_name,email,phoneNumber,description,company_id)
VALUES 
(1,'john','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','John','Doe','john@luv2code.com',"1111","test",NULL),
(2,'mary','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Mary','Public','mary@luv2code.com',"2222","test",NULL),
(3,'susan','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Susan','Adams','susan@luv2code.com',"3333","test",NULL),
(4,'kaho','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Kaho','Yukihare','Yukihare@sasasa.com',"123129084","test employer",1),
(5,'alan','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Alan','Wake','AlanTheWriter@bookforHire.com',"999003223","test employer",2),
(6,'naxaki','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Wakamo','Sets','swq@luv2code.com',"333425333","test employer",3),
(7,'usima','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Hibiki','Adams','susedffsdan@luv2code.com',"3232111333","test employer",4),
(8,'ayaka','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Âyka','Adams','susedffsdan@luv2code.com',"3332341453","test empleyee",NULL),
(9,'noa','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Noa','Adams','susedffsdan@luv2code.com',"333434343","test empleyee",NULL),
(10,'yuuka','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Yuuka','Adams','susedffsdan@luv2code.com',"3333","test empleyee",NULL);


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1 ;

--
-- Dumping data for table `roles`
--

INSERT INTO roles (name)
VALUES 
('ROLE_EMPLOYEE'),('ROLE_RECRUITER'),('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE users_roles (
  user_id int NOT NULL,
  role_id int NOT NULL,
  
  PRIMARY KEY (user_id,role_id),
  
  KEY FK_ROLE_idx (role_id),

  CONSTRAINT FK_USER_05 FOREIGN KEY (user_id) 
  REFERENCES users (id) ,
  
  CONSTRAINT FK_ROLE FOREIGN KEY (role_id) 
  REFERENCES roles (id) 
) ;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO users_roles (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 3),
(4,2),
(5,2),
(6,2),
(7,2),
(8,1),
(9,1),
(10,1);
-- 1 user can have many version of CV
CREATE TABLE cv_files (
id int auto_increment primary key,
name nvarchar(255),
dateCreated date,
user_id int NOT NULL,
CONSTRAINT userCv_fk FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO cv_files (id,name,dateCreated,user_id)
VALUES (1,"name",curdate(),1), (2,"name",curdate(),2), (3,"name",curdate(),3);


CREATE TABLE avatar_files (
id int auto_increment primary key,
name varchar(255),
dateCreated date,
user_id int NOT NULL,
CONSTRAINT userAvatar FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE user_current_file (
 id int PRIMARY KEY,
 user_id int NOT NULL,
 avatar_id int ,
 cvFile_id int,
 CONSTRAINT FK_USER_CURRENT FOREIGN KEY (user_id) 
 REFERENCES users (id) ,
 CONSTRAINT FK_USERAVATAR_CURRENT FOREIGN KEY (avatar_id) 
 REFERENCES avatar_files (id) ,
 CONSTRAINT FK_USERCVFILE_CURRENT FOREIGN KEY (cvFile_id) 
 REFERENCES cv_files (id) 
);

INSERT INTO user_current_file (id,user_id,avatar_id,cvFile_id)
VALUES (1,1,NULL,NULL),(2,2,NULL,NULL) , (3,3,NULL,NULL);

-- job type
CREATE TABLE job_types (
id int primary key,
name nvarchar(255),
dateCreated date 
);
INSERT INTO job_types
VALUES 
(1,'fulltime',curdate()),
(2,'parttime',curdate()),
(3,'freelancer',curdate());


CREATE TABLE categorys (
id int auto_increment primary key,
name nvarchar(255),
dateCreated date
);
INSERT INTO categorys
VALUES 
(1,'ect...',curdate()),
(2,'Java',curdate()),
(3,'.NET',curdate()),
(4,'#C',curdate()),
(5,'Android',curdate()),
(6,'Unreal Engine',curdate()),
(7,'NodeJS',curdate()),
(8,'PHP',curdate());


CREATE TABLE posts (
id int auto_increment primary key,
title nvarchar(255),
experience nvarchar(255),
number_of_recruit int,
salary nvarchar(255),
categoryId int,
expireDate date,
user_id int NOT NULL,
company_id int NOT NULL,
dateCreated date,
job_typeId int,
CONSTRAINT userPost_fk FOREIGN KEY (user_id) REFERENCES users (id),
CONSTRAINT postCategory_fk FOREIGN KEY (categoryId) REFERENCES categorys (id),
CONSTRAINT jobTypePost_fk FOREIGN KEY (job_typeId) REFERENCES job_types (id),
CONSTRAINT companyPost_fk FOREIGN KEY (company_id) REFERENCES companies(id)
);

INSERT INTO posts (id,title,experience,number_of_recruit,salary,categoryId,expireDate,user_id,company_id,dateCreated,job_typeId) 
VALUES 
(1,"Tuyển nhân viên .NET 1","không cần kinh nghiệm",5,"Thỏa thuận",3,curdate(),4,1,curdate(),1),
(2,"Tuyển nhân viên .NET pro","1 năm kinh nghiệm",10,"Thỏa thuận",3,curdate(),4,1,curdate(),1),
(3,"Tuyển nhân viên C# gấp","không cần kinh nghiệm",5,"Thỏa thuận",4,curdate(),4,1,curdate(),2),
(4,"Tuyển nhân viên .NET 2","không cần kinh nghiệm",5,"Thỏa thuận",3,curdate(),5,2,curdate(),1),
(5,"Tuyển nhân viên PHP nhanh","không cần kinh nghiệm",5,"Thỏa thuận",8,curdate(),5,2,curdate(),3),
(6,"Tuyển nhân viên java","3 năm kinh nghiệm",5,"Thỏa thuận",2,curdate(),5,2,curdate(),3),
(7,"Tuyển nhân viên .NET 3","không cần kinh nghiệm",5,"Thỏa thuận",3,curdate(),6,3,curdate(),1);


CREATE TABLE applyposts (
id int auto_increment primary key,
dateCreated date,
post_id int,
user_id int,
cvFile_id int,
status int,
text nvarchar(255),
CONSTRAINT applyUser_fk FOREIGN KEY (user_id) REFERENCES users(id),
CONSTRAINT applyPost_fk FOREIGN KEY (post_id) REFERENCES posts(id),
CONSTRAINT applyCvFile_fk FOREIGN KEY (cvFile_id) REFERENCES cv_files(id)
)

-- sum job offer by category
SELECT COUNT(id) FROM Posts WHERE categoryId = 3 ;
SELECT cat.name,COUNT(p.number_of_recruit) FROM categorys AS cat INNER JOIN Posts AS p ON cat.id = p.categoryId GROUP BY cat.id;


-- temp for deleting user
select * from users_roles;
delete from users_roles where user_id = 12;
delete from users where id = 12;