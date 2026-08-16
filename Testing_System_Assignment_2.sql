DROP DATABASE IF EXISTS exam_management;
CREATE DATABASE exam_management;
USE exam_management;

-- Tạo các bảng
CREATE TABLE department (
	department_id 		INT PRIMARY KEY AUTO_INCREMENT,
    department_name 	VARCHAR(50) NOT NULL
);

CREATE TABLE `position` (
	position_id			INT PRIMARY KEY AUTO_INCREMENT,
    position_name		ENUM ('DEV', 'TEST', 'SCRUM_MASTER', 'PM')
);

CREATE TABLE `account` (
	account_id			INT PRIMARY KEY AUTO_INCREMENT,
    email				VARCHAR(100) UNIQUE NOT NULL,
    user_name			VARCHAR(100) UNIQUE NOT NULL,
    full_name			VARCHAR(100) DEFAULT 'NO NAME',
    department_id		INT,
    position_id			INT,
    create_date			DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) 	REFERENCES department(department_id),
    FOREIGN KEY (position_id) 		REFERENCES `position`(position_id)
);

CREATE TABLE `group` (
	group_id			INT PRIMARY KEY AUTO_INCREMENT,
    group_name			VARCHAR(30) NOT NULL,
    creator_id			INT,
    create_date			DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (creator_id) REFERENCES `account`(account_id)
);

CREATE TABLE group_account (
	group_id			INT,
    account_id			INT,
    join_date			DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (group_id, account_id),
    FOREIGN KEY (group_id) 		REFERENCES `group`(group_id),
    FOREIGN KEY (account_id) 	REFERENCES `account`(account_id)
); 

CREATE TABLE type_question (
	type_id				INT PRIMARY KEY AUTO_INCREMENT,
    type_name			ENUM('ESSAY', 'MULTIPLE-CHOICE')
);

CREATE TABLE category_question (
	category_id			INT PRIMARY KEY AUTO_INCREMENT,
    category_name		VARCHAR(50) NOT NULL
);

CREATE TABLE question (
	question_id			INT PRIMARY KEY AUTO_INCREMENT,
    content				VARCHAR(255) NOT NULL,
    category_id			INT,
    type_id				INT,
    creator_id			INT,
    create_date			DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) 	REFERENCES category_question(category_id),
    FOREIGN KEY (type_id) 		REFERENCES type_question(type_id),
    FOREIGN KEY (creator_id) 	REFERENCES `account`(account_id)
);

CREATE TABLE answer (
	answer_id			INT PRIMARY KEY AUTO_INCREMENT,
    content				VARCHAR(255) NOT NULL,
    question_id			INT,
    is_correct			BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (question_id) REFERENCES question(question_id)
);

CREATE TABLE exam (
	exam_id				INT PRIMARY KEY AUTO_INCREMENT,
    code				VARCHAR(10),
	title				VARCHAR(50),
    category_id			INT,
    duration			INT,
    creator_id			INT,
    create_date			DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) 	REFERENCES category_question(category_id),
    FOREIGN KEY (creator_id) 	REFERENCES `account`(account_id)
);

CREATE TABLE exam_question (
	exam_id				INT,
    question_id			INT,
    PRIMARY KEY (exam_id, question_id),
    FOREIGN KEY (exam_id) 		REFERENCES exam(exam_id),
    FOREIGN KEY (question_id) 	REFERENCES question(question_id)
);

-- Chèn dữ liệu vào các bảng
INSERT INTO department (department_name)
	VALUES 	('Marketing'),
			('Sale'),
            ('Bảo vệ'),
            ('Nhân sự'),
            ('Kỹ thuật');

INSERT INTO `position` (position_name)
	VALUES 	('DEV'),
			('TEST'),
            ('SCRUM_MASTER'),
            ('PM');
            
INSERT INTO `account` (email, user_name, full_name, department_id, position_id)
	VALUES 	('an.nguyen@vti.com.vn', 'annguyen', 'Nguyễn Văn An', 1, 1),
			('binh.tran@vti.com.vn', 'binhtran', 'Trần Bình', 2, 2),
			('cuong.le@vti.com.vn', 'cuongle', 'Lê Văn Cường', 3, 1),
			('dung.do@vti.com.vn', 'dungdo', 'Đỗ Dũng', 5, 3),
			('hoa.pham@vti.com.vn', 'hoapham', 'Phạm Thị Hoa', 4, 4);
            
INSERT INTO `group` (group_name, creator_id)
	VALUES	('Team Java', 1),
			('Tester Team', 2),
			('FE Team', 5),
			('Team Mono', 4),
			('Database Team', 3);
            
INSERT INTO group_account (group_id, account_id)
	VALUES	(1, 1),
			(1, 3),
			(2, 5),
			(3, 1),
			(4, 5);
            
INSERT INTO type_question (type_name)
	VALUES	('ESSAY'),
			('MULTIPLE-CHOICE');
            
INSERT INTO category_question (category_name)
	VALUES	('Java'),
			('SQL'),
			('Ruby'),
			('HTML/CSS'),
			('JavaScript'),
            ('Software Testing'),
			('Web Development');
            
INSERT INTO question (content, category_id, type_id, creator_id)
	VALUES	('What is the difference between JDK, JRE and JVM?', 1, 2, 1),
			('What is inheritance in Java?', 1, 2, 1),
			('What is a primary key in SQL?', 2, 2, 2),
			('What is the difference between INNER JOIN and LEFT JOIN?', 2, 2, 2),
			('Explain the difference between HTML and CSS.', 4, 1, 3);

INSERT INTO answer (content, question_id, is_correct)
	VALUES
    -- Question 1
    ('Java Development Kit', 1, TRUE),
    ('Java Runtime Environment', 1, FALSE),
    ('Java Virtual Machine', 1, FALSE),
    ('Java Database Kit', 1, FALSE),
    -- Question 2
    ('A mechanism that allows a class to inherit properties and methods from another class', 2, TRUE),
    ('A mechanism for connecting Java to a database', 2, FALSE),
    ('A Java exception handling mechanism', 2, FALSE),
    ('A mechanism for creating interfaces', 2, FALSE),
    -- Question 3
    ('A field that uniquely identifies each record', 3, TRUE),
    ('A field that stores multiple values', 3, FALSE),
    ('A field used only for sorting', 3, FALSE),
    ('A field used to store passwords', 3, FALSE),
    -- Question 4
    ('INNER JOIN returns matching records, LEFT JOIN also returns unmatched records from the left table', 4, TRUE),
    ('INNER JOIN returns all records from both tables', 4, FALSE),
    ('LEFT JOIN only returns unmatched records', 4, FALSE),
    ('There is no difference', 4, FALSE);
    
INSERT INTO exam (code, title, category_id, duration, creator_id)
	VALUES	('JAVA01', 'Java Basic Test', 1, 60, 1),
			('SQL01', 'SQL Fundamental Test', 2, 45, 2),
			('TEST01', 'Software Testing Basic Test', 6, 45, 3),
			('WEB01', 'Web Development Test', 7, 60, 4),
			('JS01', 'JavaScript Basic Test', 5, 60, 5);
            
INSERT INTO exam_question (exam_id, question_id)
	VALUES	(1, 2),
			(1, 1),
			(2, 3),
			(2, 4),
			(3, 5),
			(4, 1),
			(5, 1);

