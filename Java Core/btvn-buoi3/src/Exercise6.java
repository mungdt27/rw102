public class Exercise6 {
    // Question 1: Tạo method để in ra các số chẵn nguyên dương nhỏ hơn 10
    public static void question1() {
        for (int i = 2; i < 10; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Question 2: Tạo method để in thông tin các account
    public static void question2(Account account) {
        System.out.println("Account Info:");
        System.out.println("ID: " + account.getId());
        System.out.println("Username: " + account.getUsername());
        System.out.println("Email: " + account.getEmail());
    }

    // Question 3: Tạo method để in ra các số nguyên dương nhỏ hơn 10
    public static void question3() {
        for (int i = 1; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
