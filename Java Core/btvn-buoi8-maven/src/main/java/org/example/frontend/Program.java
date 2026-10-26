package org.example.frontend;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        AccountFunction accountFunction = new AccountFunction();
        DepartmentFunction departmentFunction = new DepartmentFunction();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Mời bạn chọn chức năng");
            System.out.println("1. Làm việc với account");
            System.out.println("2. Làm việc với deparment");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    accountFunction.menu();
                    break;
                case "2":
                    departmentFunction.menu();
                    break;
                default:
                    System.out.println("Chọn sai! chọn lại:");
            }
        }

    }
}