DROP DATABASE IF EXISTS Kingsland;
CREATE DATABASE IF NOT EXISTS Kingsland;
USE Kingsland;

CREATE TABLE Student(
	Id VARCHAR(10) not null,
	studentName VARCHAR(45) not null,
	Address VARCHAR(45) not null,
	Contact int(10),
	dob DATE,
	gender VARCHAR(10) not null,
	CONSTRAINT PRIMARY KEY(Id)
);

CREATE TABLE Course(
	code VARCHAR(20) not null,
	courseName VARCHAR(45) not null,
	courseType VARCHAR(45) not null,
	duration VARCHAR(20) not null,
	CONSTRAINT PRIMARY KEY(code)
);

CREATE TABLE Registration(
	regNo int not null,
	regDate DATE,
	regFee DOUBLE(10,2) not null,
	Id VARCHAR(10),
	code VARCHAR(20),
	CONSTRAINT PRIMARY KEY(regNo),
	CONSTRAINT FOREIGN KEY(Id) REFERENCES Student(Id)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(code) REFERENCES Course(code)
	ON UPDATE CASCADE ON DELETE CASCADE	
);


select * from Student;
select * from Course;
select * from Registration;




	
	
	
	
	
	
	
	