DROP DATABASE IF EXISTS exam_management;
CREATE DATABASE exam_management;
USE exam_management;

CREATE TABLE departments (
	department_id 		INT PRIMARY KEY AUTO_INCREMENT,
    department_name 	VARCHAR(50)
);

CREATE TABLE positions (
	position_id			INT PRIMARY KEY AUTO_INCREMENT,
    position_name		ENUM ('Dev', 'Test', 'Scrum Master', 'PM')
);

CREATE TABLE accounts (
	account_id			INT PRIMARY KEY AUTO_INCREMENT,
    email				VARCHAR(50),
    user_name			VARCHAR(30),
    full_name			VARCHAR(50),
    department_id		INT,
    position_id			INT,
    create_date			DATE,
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (position_id) REFERENCES positions(position_id)
);

CREATE TABLE `groups` (
	group_id			INT PRIMARY KEY AUTO_INCREMENT,
    group_name			VARCHAR(30),
    creator_id			INT,
    create_date			DATE,
    FOREIGN KEY (creator_id) REFERENCES accounts(account_id)
);

CREATE TABLE group_account (
	group_id			INT,
    account_id			INT,
    join_date			DATE,
    PRIMARY KEY (group_id, account_id),
    FOREIGN KEY (group_id) REFERENCES `groups`(group_id),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
); 

CREATE TABLE type_question (
	type_id				INT PRIMARY KEY AUTO_INCREMENT,
    type_name			ENUM('Essay', 'Multiple-Choice')
);

CREATE TABLE category_question (
	category_id			INT PRIMARY KEY AUTO_INCREMENT,
    category_name		VARCHAR(50)
);

CREATE TABLE questions (
	question_id			INT PRIMARY KEY AUTO_INCREMENT,
    content				VARCHAR(255),
    category_id			INT,
    type_id				INT,
    creator_id			INT,
    create_date			DATE,
    FOREIGN KEY (category_id) REFERENCES category_question(category_id),
    FOREIGN KEY (type_id) REFERENCES type_question(type_id),
    FOREIGN KEY (creator_id) REFERENCES accounts(account_id)
);

CREATE TABLE answers (
	answer_id			INT PRIMARY KEY AUTO_INCREMENT,
    content				VARCHAR(255),
    question_id			INT,
    is_correct			BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

CREATE TABLE exams (
	exam_id				INT PRIMARY KEY AUTO_INCREMENT,
    code				VARCHAR(10),
	title				VARCHAR(50),
    category_id			INT,
    duration			INT,
    creator_id			INT,
    create_date			DATE,
    FOREIGN KEY (category_id) REFERENCES category_question(category_id),
    FOREIGN KEY (creator_id) REFERENCES accounts(account_id)
);

CREATE TABLE exam_question (
	exam_id				INT,
    question_id			INT,
    PRIMARY KEY (exam_id, question_id),
    FOREIGN KEY (exam_id) REFERENCES exams(exam_id),
    FOREIGN KEY (question_id) REFERENCES questions(question_id)
);
