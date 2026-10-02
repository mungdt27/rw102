package frontend;

import backend.IQuanLy;
import backend.QuanLy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        IQuanLy iql = new QuanLy();

        while (true) {
            System.out.println("==== CHỌN CHỨC NĂNG ====");
            System.out.println("1. Hiển thị toàn bộ account.");
            System.out.println("2. Tìm kiếm account theo username.");
            System.out.println("3. Hiển thị department.");
            System.out.println("4. Tìm kiếm department theo tên.");
            System.out.println("5. Thoát khỏi chương trình.");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    iql.hienThiToanBoAccount();
                    break;
                case "2":
                    iql.timKiemAccountTheoUsername();
                    break;
                case "3":
                    iql.hienThiDepartment();
                    break;
                case "4":
                    iql.timKiemDepartmentTheoTen();
                    break;
                case "5":
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Chọn sai, vui lòng chọn lại!");
            }
        }
    }
}