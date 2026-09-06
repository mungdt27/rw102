-- Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo trước 1 năm trước 
DELIMITER $$		
CREATE TRIGGER trigger_check_create_date
BEFORE INSERT ON `group`
FOR EACH ROW
BEGIN
    IF NEW.create_date < DATE_SUB(CURDATE(), INTERVAL 1 YEAR) THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'Group date cannot be older than 1 year!';
    END IF;
END $$
DELIMITER ;

-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào department "Sale" nữa, 
-- khi thêm thì hiện ra thông báo "Department "Sale" cannot add more user" 
DELIMITER $$
CREATE TRIGGER trigger_no_add_sale
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    IF NEW.department_id = (SELECT department_id FROM department
							WHERE department_name = 'Sale'
    ) THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'Department "Sale" cannot add more user!';
    END IF;
END $$
DELIMITER ;

-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user 
DELIMITER $$
CREATE TRIGGER trigger_max_5_user
BEFORE INSERT ON group_account
FOR EACH ROW
BEGIN
    DECLARE user_quantity INT;
    SELECT COUNT(account_id) INTO user_quantity
    FROM group_account WHERE group_id = NEW.group_id;
    IF user_quantity >= 5 THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'A group can have maximum 5 users!';
    END IF;
END $$
DELIMITER ;

-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question 
DELIMITER $$
CREATE TRIGGER trigger_max_10_question
BEFORE INSERT ON exam_question
FOR EACH ROW
BEGIN
    DECLARE question_quantity INT;
    SELECT COUNT(question_id) INTO question_quantity
    FROM exam_question WHERE exam_id = NEW.exam_id;
    IF question_quantity >= 10 THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'An exam can have maximum 10 questions!';
    END IF;
END $$
DELIMITER ;

-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là 
-- admin@gmail.com (đây là tài khoản admin, không cho phép user xóa), 
-- còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông tin liên quan tới user đó 
DELIMITER $$
CREATE TRIGGER trigger_no_delete_admin
BEFORE DELETE ON `account`
FOR EACH ROW
BEGIN
    IF OLD.email = 'admin@gmail.com' THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'Admin account cannot be deleted!';
    END IF;
END $$
DELIMITER ;

-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của table Account, 
-- hãy tạo trigger cho phép người dùng khi tạo account không điền vào departmentID thì sẽ được phân vào phòng ban "waiting Department"  
DELIMITER $$
CREATE TRIGGER trigger_default_department
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    IF NEW.department_id IS NULL THEN
        SET NEW.department_id = (
            SELECT department_id FROM department
            WHERE department_name = 'Chờ việc'
        );
    END IF;
END $$
DELIMITER ;
 
-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi question, trong đó có tối đa 2 đáp án đúng
DELIMITER $$
CREATE TRIGGER trigger_max_answer
BEFORE INSERT ON answer
FOR EACH ROW
BEGIN
    IF (
        SELECT COUNT(*) FROM answer
        WHERE question_id = NEW.question_id
    ) >= 4 THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'A question can have maximum 4 answers!';
    ELSEIF (
        SELECT COUNT(*) FROM answer
        WHERE question_id = NEW.question_id AND is_correct = TRUE
    ) >= 2
    AND NEW.is_correct = TRUE THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'A question can have maximum 2 correct answers!';
    END IF;
END $$
DELIMITER ;

-- Question 8: Viết trigger sửa lại dữ liệu cho đúng: Nếu người dùng nhập vào gender của account là nam, nữ, chưa xác định 
-- thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database 
DELIMITER $$
CREATE TRIGGER trigger_gender
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    IF NEW.gender = 'nam' THEN
        SET NEW.gender = 'M';
    ELSEIF NEW.gender = 'nữ' THEN
        SET NEW.gender = 'F';
    ELSEIF NEW.gender = 'chưa xác định' THEN
        SET NEW.gender = 'U';
    END IF;
END $$
DELIMITER ;

-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày 
DELIMITER $$
CREATE TRIGGER trigger_no_delete_new_exam
BEFORE DELETE ON exam
FOR EACH ROW
BEGIN
    IF DATEDIFF(CURDATE(), DATE(OLD.create_date)) < 2 THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'Exam created within 2 days cannot be deleted!';
    END IF;
END $$
DELIMITER ;

-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, delete các question khi question đó chưa nằm trong exam nào 
-- Update
DELIMITER $$
CREATE TRIGGER trigger_question_update
BEFORE UPDATE ON question
FOR EACH ROW
BEGIN
    IF EXISTS (SELECT * FROM exam_question
        WHERE question_id = OLD.question_id
    ) THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'Question is being used in an exam and cannot be updated!';
    END IF;
END $$
DELIMITER ;

-- Delete
DELIMITER $$
CREATE TRIGGER trigger_question_delete
BEFORE DELETE ON question
FOR EACH ROW
BEGIN
    IF EXISTS (SELECT * FROM exam_question
        WHERE question_id = OLD.question_id
    ) THEN
        SIGNAL SQLSTATE '88888'
        SET MESSAGE_TEXT = 'Question is being used in an exam and cannot be deleted!';
    END IF;
END $$
DELIMITER ;

-- Question 12: Lấy ra thông tin exam trong đó: 
-- Duration <= 30 thì sẽ đổi thành giá trị "Short time" 
-- 30 < Duration <= 60 thì sẽ đổi thành giá trị "Medium time" 
-- Duration > 60 thì sẽ đổi thành giá trị "Long time" 
SELECT exam_id, code, title, duration,
    CASE
        WHEN duration <= 30 THEN 'Short time'
        WHEN duration <= 60 THEN 'Medium time'
        ELSE 'Long time'
    END AS duration_type
FROM exam;

-- Question 13: Thống kê số account trong mỗi group và in ra thêm 1 column nữa có tên là 
-- the_number_user_amount và mang giá trị được quy định như sau:
-- Nếu số lượng user trong group =< 5 thì sẽ có giá trị là few 
-- Nếu số lượng user trong group <= 20 và > 5  thì sẽ có giá trị là normal 
-- Nếu số lượng user trong group > 20 thì sẽ có giá trị là higher 

-- Question 14: Thống kê số mỗi phòng ban có bao nhiêu user, 
-- nếu phòng ban nào không có user thì sẽ thay đổi giá trị 0 thành "Không có User" 

