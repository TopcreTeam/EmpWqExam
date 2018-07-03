DROP TABLE IF EXISTS employee;

create table employee(
	empno int(255) primary key auto_increment,
	ename varchar(20),
    gender varchar(2),
	job varchar(20),
    position varchar(20),
    email varchar(30),
	salary int(255),
	hiredate date,
    createdDate datetime default localtimestamp,
    lastUpdateDate datetime default localtimestamp  
);
