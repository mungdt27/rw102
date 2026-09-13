import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        // Table Department
        Department dep1 = new Department();
        dep1.id = 1;
        dep1.name = "Marketing";

        Department dep2 = new Department();
        dep2.id = 2;
        dep2.name = "Sale";

        Department dep3 = new Department();
        dep3.id = 3;
        dep3.name = "Developer";

        System.out.println("=== Department ===");
        System.out.println("Department ID: " + dep1.id);
        System.out.println("Department Name: " + dep1.name);

        // Table Position
        Position pos1 = new Position();
        pos1.id = 1;
        pos1.name = Position.PositionName.DEV;

        Position pos2 = new Position();
        pos2.id = 2;
        pos2.name = Position.PositionName.TEST;

        Position pos3 = new Position();
        pos3.id = 3;
        pos3.name = Position.PositionName.SCRUM_MASTER;

        Position pos4 = new Position();
        pos4.id = 4;
        pos4.name = Position.PositionName.PM;

        System.out.println("=== Position ===");
        System.out.println("Position ID: " + pos1.id);
        System.out.println("Position Name: " + pos1.name);

        // Table Account
        Account acc1 = new Account();
        acc1.id = 1;
        acc1.email = "a.nguyen@gmail.com";
        acc1.userName = "vana";
        acc1.fullName = "Nguyen Van A";
        acc1.department = dep1;
        acc1.position = pos1;
        acc1.createDate = LocalDate.now();

        Account acc2 = new Account();
        acc2.id = 2;
        acc2.email = "b.nguyen@gmail.com";
        acc2.userName = "vanb";
        acc2.fullName = "Nguyen Van B";
        acc2.department = dep2;
        acc2.position = pos2;
        acc2.createDate = LocalDate.now();

        Account acc3 = new Account();
        acc3.id = 3;
        acc3.email = "c.nguyen@gmail.com";
        acc3.userName = "vanc";
        acc3.fullName = "Nguyen Van C";
        acc3.department = dep1;
        acc3.position = pos4;
        acc3.createDate = LocalDate.now();

        System.out.println("=== Account ===");
        System.out.println("Account ID: " + acc1.id);
        System.out.println("Email: " + acc1.email);
        System.out.println("Username: " + acc1.userName);
        System.out.println("Full Name: " + acc1.fullName);
        System.out.println("Department ID: " + acc1.department.id);
        System.out.println("Position ID: " + acc1.position.id);
        System.out.println("Create Date: " + acc1.createDate);

        // Table Group
        Group group1 = new Group();
        group1.id = 1;
        group1.name = "Team Java";
        group1.account = acc1;
        group1.createDate = LocalDate.now();

        Group group2 = new Group();
        group2.id = 2;
        group2.name = "Tester Team";
        group2.account = acc3;
        group2.createDate = LocalDate.now();

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "FE Team";
        group3.account = acc2;
        group3.createDate = LocalDate.now();

        System.out.println("=== Group ===");
        System.out.println("Group ID: " + group1.id);
        System.out.println("Group Name: " + group1.name);
        System.out.println("Creator ID: " + group1.account.id);
        System.out.println("Create Date: " + group1.createDate);

        // Table GroupAccount
        GroupAccount ga1 = new GroupAccount();
        ga1.group = group1;
        ga1.account = acc2;
        ga1.joinDate = LocalDate.now();

        GroupAccount ga2 = new GroupAccount();
        ga2.group = group2;
        ga2.account = acc1;
        ga2.joinDate = LocalDate.now();

        GroupAccount ga3 = new GroupAccount();
        ga3.group = group3;
        ga3.account = acc3;
        ga3.joinDate = LocalDate.now();

        System.out.println("=== Group Account ===");
        System.out.println("Group ID: " + ga1.group.id);
        System.out.println("Account ID: " + ga1.account.id);
        System.out.println("Join Date: " + ga1.joinDate);

        // Table TypeQuestion
        TypeQuestion tq1 = new TypeQuestion();
        tq1.id = 1;
        tq1.name = TypeQuestion.TypeName.ESSAY;

        TypeQuestion tq2 = new TypeQuestion();
        tq2.id = 2;
        tq2.name = TypeQuestion.TypeName.MULTIPLE_CHOICE;

        System.out.println("=== Type Question ===");
        System.out.println("Type ID: " + tq1.id);
        System.out.println("Type Name: " + tq1.name);

        // Table CategoryQuestion
        CategoryQuestion cq1 = new CategoryQuestion();
        cq1.id = 1;
        cq1.name = "Java";

        CategoryQuestion cq2 = new CategoryQuestion();
        cq2.id = 2;
        cq2.name = "SQL";

        CategoryQuestion cq3 = new CategoryQuestion();
        cq3.id = 3;
        cq3.name = "Python";

        System.out.println("=== Category Question ===");
        System.out.println("Category ID: " + cq1.id);
        System.out.println("Category Name: " + cq1.name);

        // Table Question
        Question ques1 = new Question();
        ques1.id = 1;
        ques1.content = "What is inheritance in Java?";
        ques1.categoryQuestion = cq1;
        ques1.typeQuestion = tq2;
        ques1.account = acc1;
        ques1.createDate = LocalDate.now();

        Question ques2 = new Question();
        ques2.id = 2;
        ques2.content = "What is a primary key in SQL?";
        ques2.categoryQuestion = cq2;
        ques2.typeQuestion = tq2;
        ques2.account = acc2;
        ques2.createDate = LocalDate.now();

        Question ques3 = new Question();
        ques3.id = 3;
        ques3.content = "What is the difference between INNER JOIN and LEFT JOIN?";
        ques3.categoryQuestion = cq2;
        ques3.typeQuestion = tq2;
        ques3.account = acc2;
        ques3.createDate = LocalDate.now();

        System.out.println("=== Question ===");
        System.out.println("Question ID: " + ques1.id);
        System.out.println("Content: " + ques1.content);
        System.out.println("Category ID: " + ques1.categoryQuestion.id);
        System.out.println("Type ID: " + ques1.typeQuestion.id);
        System.out.println("Creator ID: " + ques1.account.id);
        System.out.println("Create Date: " + ques1.createDate);

        // Table Answer
        Answer ans1 = new Answer();
        ans1.id = 1;
        ans1.content = "A field that uniquely identifies each record";
        ans1.question = ques3;
        ans1.isCorrect = true;

        Answer ans2 = new Answer();
        ans2.id = 2;
        ans2.content = "A field that stores multiple values";
        ans2.question = ques3;
        ans2.isCorrect = false;

        Answer ans3 = new Answer();
        ans3.id = 3;
        ans3.content = "A field used only for sorting";
        ans3.question = ques3;
        ans3.isCorrect = false;

        System.out.println("=== Answer ===");
        System.out.println("Answer ID: " + ans1.id);
        System.out.println("Content: " + ans1.content);
        System.out.println("Question ID: " + ans1.question.id);
        System.out.println("Is Corect: " + ans1.isCorrect);

        // Table Exam
        Exam exam1 = new Exam();
        exam1.id = 1;
        exam1.code = "SQL01";
        exam1.title = "SQL Fundamental Test";
        exam1.categoryQuestion = cq2;
        exam1.duration = 45;
        exam1.account = acc2;

        Exam exam2 = new Exam();
        exam2.id = 2;
        exam2.code = "TEST01";
        exam2.title = "Software Testing Basic Test";
        exam2.categoryQuestion = cq3;
        exam2.duration = 60;
        exam2.account = acc3;

        Exam exam3 = new Exam();
        exam3.id = 3;
        exam3.code = "JAVA01";
        exam3.title = "Java Basic Test";
        exam3.categoryQuestion = cq1;
        exam3.duration = 45;
        exam3.account = acc1;

        System.out.println("=== Exam ===");
        System.out.println("Exam ID: " + exam1.id);
        System.out.println("Code: " + exam1.code);
        System.out.println("Title: " + exam1.title);
        System.out.println("Category ID: " + exam1.categoryQuestion.id);
        System.out.println("Duration: " + exam1.duration);
        System.out.println("Creator ID: " + exam1.account.id);
        System.out.println("Create Date: " + exam1.createDate);

        // Table ExamQuestion
        ExamQuestion eq1 = new ExamQuestion();
        eq1.exam = exam1;
        eq1.question = ques2;

        ExamQuestion eq2 = new ExamQuestion();
        eq2.exam = exam1;
        eq2.question = ques1;

        ExamQuestion eq3 = new ExamQuestion();
        eq3.exam = exam2;
        eq3.question = ques3;

        System.out.println("=== Exam Question ===");
        System.out.println("Exam ID: " + eq1.exam.id);
        System.out.println("Question ID: " + eq1.question.id);
    }
}