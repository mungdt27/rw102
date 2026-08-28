-- Exercise 1: Tiếp tục với Database Testing System 
-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó 
DELIMITER $$
CREATE PROCEDURE get_account_by_department (IN in_department_name VARCHAR(50))
BEGIN
    SELECT a.* FROM `account` a JOIN department d
	ON a.department_id = d.department_id
    WHERE d.department_name = in_department_name;
END $$
DELIMITER ;

-- Question 2: Tạo store để in ra số lượng account trong mỗi group  
DELIMITER $$
CREATE PROCEDURE get_account_quantity_by_group()
BEGIN
    SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS account_quantity
    FROM `group` g LEFT JOIN group_account ga
	ON g.group_id = ga.group_id
    GROUP BY g.group_id;
END $$
DELIMITER ;

-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại 
DELIMITER $$
CREATE PROCEDURE get_question_quantity_current_month()
BEGIN
    SELECT tq.type_id, tq.type_name, COUNT(q.question_id) AS question_quantity
    FROM type_question tq JOIN question q
	ON tq.type_id = q.type_id
    WHERE MONTH(q.create_date) = MONTH(CURDATE()) AND YEAR(q.create_date) = YEAR(CURDATE())
    GROUP BY tq.type_id;
END $$
DELIMITER ;

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất 
DELIMITER $$
CREATE PROCEDURE get_type_question_most_question (OUT out_type_id INT)
BEGIN
	WITH type_question_count AS (
    SELECT tq.type_id, COUNT(q.question_id) AS question_quantity
			FROM type_question tq LEFT JOIN question q
			ON tq.type_id = q.type_id
			GROUP BY tq.type_id
		)
	SELECT type_id INTO out_type_id
    FROM type_question_count
    WHERE question_quantity = (
        SELECT MAX(question_quantity)
        FROM type_question_count
    );
END $$
DELIMITER ;

-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question 
CALL get_type_question_most_question(@type_id);

SELECT type_name
FROM type_question
WHERE type_id = @type_id;

-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên 
			-- chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào 
DELIMITER $$
CREATE PROCEDURE find_group_or_account (IN in_keyword VARCHAR(100))
BEGIN
    SELECT g.group_id AS id, g.group_name AS name
    FROM `group` g
    WHERE g.group_name LIKE CONCAT('%', in_keyword, '%')

    UNION

    SELECT a.account_id AS id, a.user_name AS name
    FROM `account` a
    WHERE a.user_name LIKE CONCAT('%', in_keyword, '%');
END $$
DELIMITER ;

-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:  
			-- username sẽ giống email nhưng bỏ phần @..mail đi  	
			-- positionID: sẽ có default là developer 
			-- departmentID: sẽ được cho vào 1 phòng chờ 
	-- Sau đó in ra kết quả tạo thành công 
DELIMITER $$
CREATE PROCEDURE create_account (IN in_full_name VARCHAR(100), IN in_email VARCHAR(100))
BEGIN
    DECLARE v_user_name VARCHAR(100);
    DECLARE v_department_id INT;
    DECLARE v_position_id INT;
    SET v_user_name = SUBSTRING_INDEX(in_email, '@', 1);

    SELECT department_id INTO v_department_id
    FROM department WHERE department_name = 'Chờ việc';

    SELECT position_id INTO v_position_id
    FROM `position` WHERE position_name = 'DEV';

    INSERT INTO `account` (email, user_name, full_name, department_id, position_id)
    VALUES (in_email, v_user_name, in_full_name, v_department_id, v_position_id);

    SELECT * FROM `account` ORDER BY account_id DESC LIMIT 1;
END $$
DELIMITER ;

-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice 
			-- để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất 
DELIMITER $$
CREATE PROCEDURE get_longest_question (IN in_type_name VARCHAR(30))
BEGIN
	WITH question_by_type AS (
		SELECT q.question_id, q.content, tq.type_name, CHAR_LENGTH(q.content) AS length_content
		FROM question q JOIN type_question tq
		ON q.type_id = tq.type_id
		WHERE tq.type_name = in_type_name
    )
	SELECT * FROM question_by_type
    WHERE length_content = (SELECT MAX(length_content) FROM question_by_type);
END $$
DELIMITER ;

-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID 
DELIMITER $$
CREATE PROCEDURE delete_exam (IN in_exam_id INT)
BEGIN
    DELETE FROM exam
    WHERE exam_id = in_exam_id;
END $$
DELIMITER ;

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử dụng store ở câu 9 để xóa) 
			-- Sau đó in số lượng record đã remove từ các table liên quan trong khi removing 
DELIMITER $$
CREATE PROCEDURE delete_old_exam()
BEGIN
    DELETE FROM exam_question
    WHERE exam_id IN (
        SELECT exam_id FROM exam
        WHERE create_date < DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
    );
    DELETE FROM exam
    WHERE create_date < DATE_SUB(CURDATE(), INTERVAL 3 YEAR);
END $$
DELIMITER ;

-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng nhập vào tên phòng ban 
			-- và các account thuộc phòng ban đó sẽ được chuyển về phòng ban default là phòng ban chờ việc 
DELIMITER $$
CREATE PROCEDURE delete_department (IN in_department_name VARCHAR(50))
BEGIN
    DECLARE v_department_id INT;
    DECLARE v_default_department_id INT;

    SELECT department_id INTO v_department_id
    FROM department WHERE department_name = in_department_name;

    SELECT department_id INTO v_default_department_id
    FROM department WHERE department_name = 'Chờ việc';

    UPDATE `account`
    SET department_id = v_default_department_id
    WHERE department_id = v_department_id;

    DELETE FROM department
    WHERE department_id = v_department_id;
END $$
DELIMITER ;

-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay 
DELIMITER $$
CREATE PROCEDURE get_question_quantity_by_month()
BEGIN
    SELECT 
        MONTH(create_date) AS number_month,
        COUNT(question_id) AS question_quantity
    FROM question
    WHERE YEAR(create_date) = YEAR(CURDATE())
    GROUP BY MONTH(create_date);
END $$
DELIMITER ;

-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất  
			-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong  tháng") 
DELIMITER $$
CREATE PROCEDURE get_question_by_6month()
BEGIN
    SELECT
        YEAR(create_date) AS year_number,
        MONTH(create_date) AS month_number,
        COUNT(question_id) AS question_quantity
    FROM question WHERE create_date >= DATE_SUB(CURDATE(), INTERVAL 5 MONTH)
    GROUP BY YEAR(create_date), MONTH(create_date);
END $$
DELIMITER ;
