-- Exercise 1: Join
-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ 
SELECT a.account_id, a.full_name, a.user_name, a.email, d.department_id, d.department_name
FROM `account` a
JOIN department d 
ON a.department_id = d.department_id;

-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010  
SELECT * FROM `account` WHERE create_date > '2010-12-20';

-- Question 3: Viết lệnh để lấy ra tất cả các developer  
SELECT a.account_id, a.full_name, a.email, p.position_name
FROM `account` a
JOIN `position` p
ON a.position_id = p.position_id
WHERE p.position_name = 'DEV';

-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên 
SELECT d.department_id, d.department_name, COUNT(a.account_id) AS employee_quantity
FROM department d
JOIN `account` a
ON d.department_id = a.department_id
GROUP BY d.department_id
HAVING COUNT(a.account_id) > 3;

-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất 
SELECT q.question_id, q.content, COUNT(eq.exam_id) AS exam_quantity
FROM question q
JOIN exam_question eq
ON q.question_id = eq.question_id
GROUP BY q.question_id
HAVING COUNT(eq.exam_id) = (
		SELECT MAX(exam_quantity)
		FROM (SELECT COUNT(eq.exam_id) AS exam_quantity FROM exam_question eq GROUP BY eq.question_id) AS result
);

-- Question 6: Thống kê mỗi category Question được sử dụng trong bao nhiêu Question 
SELECT c.category_id, c.category_name, COUNT(q.question_id) AS question_quantity
FROM category_question c
LEFT JOIN question q
ON c.category_id = q.category_id
GROUP BY c.category_id;

-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam 
SELECT q.question_id, q.content, COUNT(eq.exam_id) AS exam_quantity
FROM question q
LEFT JOIN exam_question eq
ON q.question_id = eq.question_id
GROUP BY q.question_id;

-- Question 8: Lấy ra Question có nhiều câu trả lời nhất 
SELECT q.question_id, q.content, COUNT(a.answer_id) AS answer_quantity
FROM question q
JOIN answer a
ON q.question_id = a.question_id
GROUP BY q.question_id
HAVING COUNT(a.answer_id) = (
		SELECT MAX(answer_quantity)
		FROM (SELECT COUNT(a.answer_id) AS answer_quantity FROM answer a GROUP BY a.question_id) AS result
);

-- Question 9: Thống kê số lượng account trong mỗi group  
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS account_quantity
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id;

-- Question 10: Tìm chức vụ có ít người nhất  
SELECT p.position_id, p.position_name, COUNT(a.account_id) AS employee_quantity
FROM `position` p
LEFT JOIN `account` a
ON p.position_id = a.position_id
GROUP BY p.position_id
HAVING COUNT(a.account_id) = (
		SELECT MIN(employee_count)
		FROM (SELECT COUNT(a.account_id) AS employee_count FROM `position` p 
				LEFT JOIN `account` a ON p.position_id = a.position_id GROUP BY p.position_id) AS result
);

-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM   
SELECT d.department_id, d.department_name, p.position_name, COUNT(a.account_id) AS employee_quantity
FROM department d
LEFT JOIN `account` a ON d.department_id = a.department_id
LEFT JOIN `position` p ON a.position_id = p.position_id
GROUP BY d.department_id, p.position_id;

-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: thông tin cơ bản của question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, … 
SELECT q.question_id, q.content AS question_content, c.category_name, tq.type_name,
       a.account_id AS creator_id, a.full_name AS creator_name, ans.answer_id,
	   ans.content AS answer_content, ans.is_correct
FROM question q
JOIN category_question c ON q.category_id = c.category_id
JOIN type_question tq ON q.type_id = tq.type_id
JOIN `account` a ON q.creator_id = a.account_id
LEFT JOIN answer ans ON q.question_id = ans.question_id;

-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm 
SELECT tq.type_id, tq.type_name, COUNT(q.question_id) AS question_quantity
FROM type_question tq
LEFT JOIN question q
ON tq.type_id = q.type_id
GROUP BY tq.type_id;
    
-- Question 14: Lấy ra group không có account nào 
SELECT g.group_id, g.group_name
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
WHERE ga.account_id IS NULL;

-- Question 15: Lấy ra account chưa có group nào 
SELECT a.account_id, a.full_name, a.email
FROM `account` a
LEFT JOIN group_account ga
ON a.account_id = ga.account_id
WHERE ga.account_id IS NULL;

-- Question 16: Lấy ra question không có answer nào 
SELECT q.question_id, q.content
FROM question q
LEFT JOIN answer a
ON q.question_id = a.question_id
WHERE a.answer_id IS NULL;

-- Exercise 2: Union 
-- Question 17:  
-- a) Lấy các account thuộc nhóm thứ 1 
SELECT a.account_id, a.full_name, a.email
FROM `account` a
JOIN group_account ga
ON a.account_id = ga.account_id
WHERE ga.group_id = 1;

-- b) Lấy các account thuộc nhóm thứ 2 
SELECT a.account_id, a.full_name, a.email
FROM `account` a
JOIN group_account ga
ON a.account_id = ga.account_id
WHERE ga.group_id = 2;

-- c) Ghép 2 kết quả từ câu a) và câu b) sao cho không có record nào trùng nhau 
SELECT a.account_id, a.full_name, a.email
FROM `account` a
JOIN group_account ga
ON a.account_id = ga.account_id
WHERE ga.group_id = 1
UNION
SELECT a.account_id, a.full_name, a.email
FROM `account` a
JOIN group_account ga
ON a.account_id = ga.account_id
WHERE ga.group_id = 2;

-- Question 18:  
-- a) Lấy các group có lớn hơn 5 thành viên 
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS member_quantity
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id
HAVING COUNT(ga.account_id) > 5;

-- b) Lấy các group có nhỏ hơn 7 thành viên 
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS member_quantity
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id
HAVING COUNT(ga.account_id) < 7;

-- c) Ghép 2 kết quả từ câu a) và câu b) 
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS member_quantity
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id
HAVING COUNT(ga.account_id) > 5
UNION
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS member_quantity
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id
HAVING COUNT(ga.account_id) < 7;
