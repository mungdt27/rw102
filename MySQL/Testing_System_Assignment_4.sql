-- Exercise 1: Tiếp tục với Database Testing System (Sử dụng subquery hoặc CTE)
-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
CREATE VIEW v_account_sale 
AS SELECT a.* FROM `account` a JOIN department d 
ON a.department_id = d.department_id 
WHERE d.department_name = "Sale";

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
CREATE VIEW v_account_most_group
AS SELECT a.* FROM `account` a JOIN group_account ga
ON a.account_id = ga.account_id 
GROUP BY a.account_id 
HAVING COUNT(ga.group_id) = (
	SELECT MAX(group_quantity)
    FROM (SELECT COUNT(ga.group_id) AS group_quantity
			FROM group_account ga
			GROUP BY ga.account_id
		) AS result
);

-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ được coi là quá dài) và xóa nó đi
CREATE VIEW v_question_content 
AS SELECT * FROM question WHERE CHAR_LENGTH(content) > 300;

DELETE FROM question
WHERE CHAR_LENGTH(content) > 300;

-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
WITH cte_employee_count AS (
    SELECT d.*, COUNT(a.account_id) AS employee_quantity
    FROM department d
    LEFT JOIN `account` a
	ON d.department_id = a.department_id
    GROUP BY d.department_id
)
SELECT *
FROM cte_employee_count
WHERE employee_quantity = (
    SELECT MAX(employee_quantity)
    FROM cte_employee_count
);

-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo
CREATE VIEW v_question_by_nguyen AS
SELECT q.*, a.full_name AS creator_name
FROM question q
JOIN `account` a
ON q.creator_id = a.account_id
WHERE a.full_name LIKE 'Nguyễn%';
