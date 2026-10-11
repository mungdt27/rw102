package org.example;

import org.example.frontend.AccountFunction;
import org.example.frontend.DepartmentFunction;



import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AccountFunction accountFunction =
                new AccountFunction(scanner);

        DepartmentFunction departmentFunction =
                new DepartmentFunction(scanner);

        while (true) {
            System.out.println();
            System.out.println("==== MỜI BẠN CHỌN CHỨC NĂNG ====");
            System.out.println("1. Làm việc với Account.");
            System.out.println("2. Làm việc với Department.");
            System.out.println("3. Thoát khỏi chương trình.");
            System.out.print("Mời bạn chọn: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    accountFunction.menu();
                    break;

                case "2":
                    departmentFunction.menu();
                    break;

                case "3":
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("Chọn sai, chọn lại!");
            }
        }
    }
}