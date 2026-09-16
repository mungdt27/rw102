import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        // Table Department
        Department dep1 = new Department(1, "Marketing");

        Department dep2 = new Department(2, "Sale");

        Department dep3 = new Department(3, "Developer");

        System.out.println("=== Department ===");
        System.out.println(dep1.toString());
        System.out.println(dep2.toString());
        System.out.println(dep3.toString());

        // Table Position
        Position pos1 = new Position(1, Position.PositionName.DEV);

        Position pos2 = new Position(2, Position.PositionName.TEST);

        Position pos3 = new Position(3, Position.PositionName.SCRUM_MASTER);

        Position pos4 = new Position(4, Position.PositionName.PM);

        System.out.println("=== Position ===");
        System.out.println(pos1.toString());
        System.out.println(pos2.toString());
        System.out.println(pos3.toString());
        System.out.println(pos4.toString());

        // Table Account
        Account acc1 = new Account(1, "a.nguyen@gmail.com", "vana", "Nguyen Van A", dep1, pos1, LocalDate.now());
        Account acc2 = new Account(2, "b.nguyen@gmail.com", "vanb", "Nguyen Van B", dep2, pos2, LocalDate.now());
        Account acc3 = new Account(3, "c.nguyen@gmail.com", "vanc", "Nguyen Van C", dep1, pos4, LocalDate.now());

        System.out.println("=== Account ===");
        System.out.println(acc1.toString());
        System.out.println(acc2.toString());
        System.out.println(acc3.toString());

        // Table Group
        Group group1 = new Group(1, "Team Java", acc1, LocalDate.now());
        Group group2 = new Group(2, "Tester Team", acc3, LocalDate.now());
        Group group3 = new Group(3, "FE Team", acc2, LocalDate.now());

        System.out.println("=== Group ===");
        System.out.println(group1.toString());
        System.out.println(group2.toString());
        System.out.println(group3.toString());

        // Table GroupAccount
        GroupAccount ga1 = new GroupAccount(group1, acc2, LocalDate.now());
        GroupAccount ga2 = new GroupAccount(group2, acc1, LocalDate.now());
        GroupAccount ga3 = new GroupAccount(group3, acc3, LocalDate.now());

        System.out.println("=== Group Account ===");
        System.out.println(ga1.toString());
        System.out.println(ga2.toString());
        System.out.println(ga3.toString());

        // Table TypeQuestion
        TypeQuestion tq1 = new TypeQuestion(1, TypeQuestion.TypeName.ESSAY);
        TypeQuestion tq2 = new TypeQuestion(2, TypeQuestion.TypeName.MULTIPLE_CHOICE);

        System.out.println("=== Type Question ===");
        System.out.println(tq1.toString());
        System.out.println(tq2.toString());

        // Table CategoryQuestion
        CategoryQuestion cq1 = new CategoryQuestion(1, "Java");
        CategoryQuestion cq2 = new CategoryQuestion(2, "SQL");
        CategoryQuestion cq3 = new CategoryQuestion(3, "Python");

        System.out.println("=== Category Question ===");
        System.out.println(cq1.toString());
        System.out.println(cq2.toString());
        System.out.println(cq3.toString());

        // Table Question
        Question ques1 = new Question(1, "What is inheritance in Java?", cq1, tq2, acc1, LocalDate.now());
        Question ques2 = new Question(2, "What is a primary key in SQL?", cq2, tq2, acc2, LocalDate.now());
        Question ques3 = new Question(3, "What is the difference between INNER JOIN and LEFT JOIN?", cq2, tq2, acc2, LocalDate.now());

        System.out.println("=== Question ===");
        System.out.println(ques1.toString());
        System.out.println(ques2.toString());
        System.out.println(ques3.toString());

        // Table Answer
        Answer ans1 = new Answer(1, "A field that uniquely identifies each record", ques3, true);
        Answer ans2 = new Answer(2, "A field that stores multiple values", ques3, false);
        Answer ans3 = new Answer(3, "A field used only for sorting", ques3, false);

        System.out.println("=== Answer ===");
        System.out.println(ans1.toString());
        System.out.println(ans2.toString());
        System.out.println(ans3.toString());

        // Table Exam
        Exam exam1 = new Exam(1, "SQL01", "SQL Fundamental Test", cq2, 45, acc2, LocalDate.now());
        Exam exam2 = new Exam(2, "TEST01", "Software Testing Basic Test", cq3, 60, acc3, LocalDate.now());
        Exam exam3 = new Exam(3, "JAVA01", "Java Basic Test", cq1, 45, acc1, LocalDate.now());

        System.out.println("=== Exam ===");
        System.out.println(exam1.toString());
        System.out.println(exam2.toString());
        System.out.println(exam3.toString());

        // Table ExamQuestion
        ExamQuestion eq1 = new ExamQuestion(exam1, ques2);
        ExamQuestion eq2 = new ExamQuestion(exam1, ques1);
        ExamQuestion eq3 = new ExamQuestion(exam2, ques3);

        System.out.println("=== Exam Question ===");
        System.out.println(eq1.toString());
        System.out.println(eq2.toString());
        System.out.println(eq3.toString());

        // Tạo các mảng
        Department[] departments = { dep1, dep2, dep3 };

        Account[] accounts = { acc1, acc2, acc3 };

        Group[] groups = { group1, group2, group3 };

        GroupAccount[] groupAccounts = { ga1, ga2, ga3 };


        // Gọi các phương thức trong Exercise1

        Exercise1.question1(accounts);
        Exercise1.question2(accounts, groupAccounts);
        Exercise1.question3(accounts);
        Exercise1.question4(accounts);

        Exercise1.question5(groups, groupAccounts);
        Exercise1.question6(accounts, groupAccounts);
        Exercise1.question7(accounts);

        Exercise1.question8(accounts);
        Exercise1.question9(departments);

        Exercise1.question10(accounts);
        Exercise1.question11(departments);
        Exercise1.question12(departments);
        Exercise1.question13(accounts);
        Exercise1.question14(accounts);
        Exercise1.question15();
    }
}