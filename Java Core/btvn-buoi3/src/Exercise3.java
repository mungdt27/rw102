import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exercise3 {
    // Question 1: In ra thông tin Exam thứ 1 và property create date sẽ được format theo định dạng vietnamese
    public static void question1(Exam[] exams) {
        System.out.println("===== Question 1 =====");
        Exam exam = exams[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Id: " + exam.getId());
        System.out.println("Code: " + exam.getCode());
        System.out.println("Title: " + exam.getTitle());
        System.out.println("Create Date: " + exam.getCreateDate().format(formatter));
    }

    // Question 2: In ra thông tin: Exam đã tạo ngày nào theo định dạng
    // Năm – tháng – ngày – giờ – phút – giây
    public static void question2(Exam[] exams) {
        System.out.println("===== Question 2 =====");
        Exam exam = exams[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Exam đã tạo ngày: " + exam.getCreateDate().format(formatter));
    }

    // Question 3: Chỉ in ra năm của create date property trong Question 2
    public static void question3(Exam[] exams) {
        System.out.println("===== Question 3 =====");
        Exam exam = exams[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        System.out.println("Năm của create date: " + exam.getCreateDate().format(formatter));
    }

    // Question 4: Chỉ in ra tháng và năm của create date property trong Question 2
    public static void question4(Exam[] exams) {
        System.out.println("===== Question 4 =====");
        Exam exam = exams[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        System.out.println("Tháng và năm của create date: " + exam.getCreateDate().format(formatter));
    }

    // Question 5: Chỉ in ra "MM-DD" của create date trong Question 2
    public static void question5(Exam[] exams) {
        System.out.println("===== Question 5 =====");
        Exam exam = exams[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        System.out.println("MM-DD của create date: " + exam.getCreateDate().format(formatter));
    }
}
