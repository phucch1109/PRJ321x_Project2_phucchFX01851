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
description nvarchar(255),
email varchar(50)
);
INSERT INTO companies (id,name,address,phoneNumber,description,email)
VALUES (1,"FPT software","Hoa Lac","0999239402389","VERY BIG COPR with alot of seeder","fpt@gmail.com"),
(2,"NFT company","Hanoi","2983485212","Greedy company","NFTcorp@gmail.com"),
(3,"Scarlet Union","Onshore","12300402389","Implemented with anti air cut in","ScarletUnion@yahoo.com"),
(4,"Abydos","Desert","402389000","Empty company with big snake hid in beneath","BluluAr@gmail.com"),
(5,"Gehenna ","Teyvat","1230211402389","Mostly handy man and some outlaw","OneDayOneBadDeed@gmail.com"),
(6,"Millennium Science","Space station","12300402389","Using cutting edge tech, but easily go bankrupt","FutureIsKey@yahoo.com"),
(7,"Trinity","Church","1230040222389","Finding way to make human closer to god","MayPeace4All@yahoo.com")
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
  avatar mediumblob,
  cvFile mediumblob,
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
title nvarchar(100),
experience nvarchar(100),
description nvarchar(255),
number_of_recruit int,
salary nvarchar(100),
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

INSERT INTO posts (id,title,experience,description,number_of_recruit,salary,categoryId,expireDate,user_id,company_id,dateCreated,job_typeId) 
VALUES 
(1,"Tuyển nhân viên .NET 1","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",3,curdate(),4,1,curdate(),1),
(2,"Tuyển nhân viên .NET pro","1 năm kinh nghiệm","mô tả",10,"Thỏa thuận",3,curdate(),4,1,curdate(),1),
(3,"Tuyển nhân viên C# gấp","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",4,curdate(),4,1,curdate(),2),
(4,"Tuyển nhân viên .NET 2","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",3,curdate(),5,2,curdate(),1),
(5,"Tuyển nhân viên PHP nhanh","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",8,curdate(),5,2,curdate(),3),
(6,"Tuyển nhân viên java","3 năm kinh nghiệm","mô tả",5,"Thỏa thuận",2,curdate(),5,2,curdate(),3),
(7,"Tuyển nhân viên .NET 3","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",3,curdate(),6,3,curdate(),1),
(8,"Tuyển nhân viên C# gấp1","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",4,curdate(),4,1,curdate(),2),
(9,"Tuyển nhân viên C# gấp2","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",4,curdate(),4,1,curdate(),2),
(10,"Tuyển nhân viên C# gấp3","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",4,curdate(),4,1,curdate(),2),
(11,"Tuyển nhân viên C# gấp4","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",4,curdate(),4,1,curdate(),2),
(12,"Tuyển nhân viên C# gấp5","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",4,curdate(),4,1,curdate(),2),
(13,"Tuyển nhân viên C# gấp6","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",4,curdate(),4,1,curdate(),2),
(14,"Tuyển nhân viên C# gấp7","không cần kinh nghiệm","mô tả",5,"Thỏa thuận",4,curdate(),4,1,curdate(),2);


CREATE TABLE applyposts (
id int auto_increment primary key,
dateCreated date,
post_id int,
user_id int,
cvFile mediumblob,
status int,
text nvarchar(255),
CONSTRAINT applyUser_fk FOREIGN KEY (user_id) REFERENCES users(id),
CONSTRAINT applyPost_fk FOREIGN KEY (post_id) REFERENCES posts(id)
);

INSERT INTO applyposts(id,dateCreated,post_id,user_id,cvFile,status,text) VALUES
(1,curdate(),14,8,null,0,"somthing"),
(2,curdate(),14,9,null,0,"somthing"),
(3,curdate(),14,10,null,0,"somthing"),
(4,curdate(),14,9,null,0,"somthing"),
(5,curdate(),14,9,null,0,"somthing");

-- SELECT c.name, count(p.company_id)  FROM companies c LEFT JOIN posts p ON c.id = p.company_id group by c.name order by count(p.company_id) desc;

SET FOREIGN_KEY_CHECKS = 1;
-- sum job offer by category
-- SELECT COUNT(id) FROM Posts WHERE categoryId = 3 ;
-- SELECT cat.name,COUNT(p.number_of_recruit) FROM categorys AS cat INNER JOIN Posts AS p ON cat.id = p.categoryId GROUP BY cat.id;
select * from posts ;
-- temp for deleting user
-- delete from users_roles where user_id = 12;
-- delete from users where id = 12;