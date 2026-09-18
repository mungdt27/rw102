import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exercise2 {
    // Question 1:  Khai báo 1 số nguyên = 5 và sử dụng lệnh System out printf để in ra số nguyên đó
    public static void question1() {
        System.out.println("===== Question 1 =====");
        int number = 5;
        System.out.printf("%d\n", number);
    }

    // Question 2: Khai báo 1 số nguyên = 100 000 000 và sử dụng lệnh System out printf
    // để in ra số nguyên đó thành định dạng như sau: 100,000,000
    public static void question2() {
        System.out.println("===== Question 2 =====");
        int number = 100000000;
        System.out.printf("%,d\n", number);
    }

    // Question 3: Khai báo 1 số thực = 5,567098 và sử dụng lệnh System out printf
    // để in ra số thực đó chỉ bao gồm 4 số đằng sau
    public static void question3() {
        System.out.println("===== Question 3 =====");
        double number = 5.567098;
        System.out.printf("%.4f\n", number);
    }

    // Question 4: Khai báo Họ và tên của 1 học sinh và in ra họ và tên học sinh đó theo định dạng như sau:
    // Họ và tên: "Nguyễn Văn A" thì sẽ in ra trên console như sau:
    // Tên tôi là "Nguyễn Văn A" và tôi đang độc thân.
    public static void question4() {
        System.out.println("===== Question 4 =====");
        String fullName = "Nguyễn Văn A";
        System.out.printf("Tên tôi là \"%s\" và tôi đang độc thân.\n", fullName);
    }

    // Question 5: Lấy thời gian bây giờ và in ra theo định dạng sau: 24/04/2020 11h:16p:20s
    public static void question5() {
        System.out.println("===== Question 5 =====");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH'h':mm'p':ss's'");
        System.out.printf("%s\n", now.format(formatter)
        );
    }

    // Question 6: In ra thông tin account (như Question 8 phần FOREACH) theo định dạng table (giống trong Database)
    public static void question6(Account[] accounts) {
        System.out.println("===== Question 6 =====");
        System.out.println("+----------------------+----------------------+--------------------+");
        System.out.printf(
                "|%22s|%22s|%20s|\n",
                "Email",
                "Full Name",
                "Department"
        );
        System.out.println("+----------------------+----------------------+--------------------+");
        for (Account account : accounts) {
            String departmentName;
            if (account.getDepartment() == null) {
                departmentName = "Chưa có phòng ban";
            } else {
                departmentName = account.getDepartment().getName();
            }
            System.out.printf(
                    "|%22s|%22s|%20s|\n",
                    account.getEmail(),
                    account.getFullName(),
                    departmentName
            );
        }
        System.out.println("+----------------------+----------------------+--------------------+");
    }
}
