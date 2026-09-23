public class Main {
    public static void main(String[] args) {
        Department dep1 = new Department(1, "Sales");
        Department dep2 = new Department(2, "Marketing");
        Department dep3 = new Department(3, "IT");
        Department[] departments = { dep1, dep2, dep3 };

        System.out.println("===== EXERCISE 4 =====");
        Exercise4.question1();
        Exercise4.question2();
        Exercise4.question3();
        Exercise4.question4();
        Exercise4.question5();
        Exercise4.question6();
        Exercise4.question7();
        Exercise4.question8();
        Exercise4.question9();
        Exercise4.question10();
        Exercise4.question11();
        Exercise4.question12();
        Exercise4.question13();
        Exercise4.question14();
        Exercise4.question15();
        Exercise4.question16();

        System.out.println("===== EXERCISE 5 =====");
        Exercise5.question5(departments);
        Exercise5.question6();
        Exercise5.question7();
    }
}