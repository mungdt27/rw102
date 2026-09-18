import java.time.LocalDate;
import java.util.Random;

public class Exercise4 {
    Random random = new Random();
    // Question 1: In ngẫu nhiên ra 1 số nguyên
    public static void question1() {
        System.out.println("===== Question 1 =====");
        int number = new Random().nextInt();
        System.out.println("Số nguyên ngẫu nhiên: " + number);
    }

    // Question 2: In ngẫu nhiên ra 1 số thực
    public static void question2() {
        System.out.println("===== Question 2 =====");
        double number = new Random().nextDouble();
        System.out.println("Số thực ngẫu nhiên: " + number);
    }

    // Question 3:  Khai báo 1 array bao gồm các tên của các bạn trong lớp, sau đó in ngẫu nhiên ra tên của 1 bạn
    public static void question3() {
        System.out.println("===== Question 3 =====");
            String[] names = {"AnH", "Huy", "Nam", "Long", "Quang"};
        int index = new Random().nextInt(names.length);
        System.out.println("Tên ngẫu nhiên: " + names[index]);
    }

    // Question 4: Lấy ngẫu nhiên 1 ngày trong khoảng thời gian 24-07-1995 tới ngày 20-12-1995
    public static void question4() {
        System.out.println("===== Question 4 =====");
        LocalDate startDate = LocalDate.of(1995, 7, 24);
        LocalDate endDate = LocalDate.of(1995, 12, 20);
        long start = startDate.toEpochDay();
        long end = endDate.toEpochDay();
        long randomDay = start + new Random().nextLong() % (end - start + 1);
        System.out.println("Ngày ngẫu nhiên: " + LocalDate.ofEpochDay(randomDay));
    }

    // Question 5: Lấy ngẫu nhiên 1 ngày trong khoảng thời gian 1 năm trở lại đây
    public static void question5() {
        System.out.println("===== Question 5 =====");
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusYears(1);
        long start = startDate.toEpochDay();
        long end = endDate.toEpochDay();
        long randomDay = start + new Random().nextLong() % (end - start + 1);
        System.out.println("Ngày ngẫu nhiên: " + LocalDate.ofEpochDay(randomDay));
    }

    // Question 6: Lấy ngẫu nhiên 1 ngày trong quá khứ
    public static void question6() {
        System.out.println("===== Question 6 =====");
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusYears(1);
        long start = startDate.toEpochDay();
        long end = endDate.toEpochDay();
        long randomDay = start + new Random().nextLong() % (end - start + 1);
        System.out.println("Ngày ngẫu nhiên: " + LocalDate.ofEpochDay(randomDay));
    }

    // Question 7: Lấy ngẫu nhiên 1 số có 3 chữ số
    public static void question7() {
        System.out.println("===== Question 7 =====");
        int number = new Random().nextInt(900) + 100;
        System.out.println("Số có 3 chữ số ngẫu nhiên: " + number);
    }
}
