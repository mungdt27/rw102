package org.example.frontend;

import org.example.backend.controller.DepartmentController;
import org.example.entity.Account;
import org.example.entity.Department;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DepartmentFunction {

    private Scanner scanner;
    private DepartmentController departmentController;

    public DepartmentFunction() {
        this.scanner = new Scanner(System.in);
        this.departmentController = new DepartmentController();
    }

    public void menu() {
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Hiển thị toàn bộ department");
            System.out.println("2. Thêm department");
            System.out.println("3. Xóa department theo id");
            System.out.println("4. Update department theo id");
            System.out.println("5. Import file CSV");
            System.out.println("6. Thoát");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    this.findAll();
                    break;
                case "2":
                    this.create();
                    break;
                case "3":
                    this.delete();
                    break;
                case "4":
                    this.update();
                    break;
                case "5":
                    this.importCSV();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Chọn sai! Chọn lại!");
            }
        }
    }

    private void importCSV() {
        System.out.println("==== CHỨC NĂNG IMPORT DEPARTMENT ====");
        // C:\Users\PNV\Desktop\rw102\Java Core\csv\input_department.csv
        System.out.println("Nhập đường dẫn file CSV: ");
        String path = scanner.nextLine();
        String message = departmentController.importCSV(path);
        System.out.println(message);


    }

    private void update() {
        System.out.println("==== CHỨC NĂNG UPDATE DEPARTMENT ====");
    }

    private void delete() {
        System.out.println("==== CHỨC NĂNG XÓA DEPARTMENT ====");
    }

    private void create() {
        System.out.println("==== CHỨC NĂNG TẠO MỚI DEPARTMENT ====");
    }

    private void findAll() {
        System.out.println("==== HIỂN THỊ TOÀN BỘ DEPARTMENT ====");
        List<Department> departments = departmentController.findAll();

        System.out.println("+-----+-------------------------+");
        System.out.printf("|%5s|%25s|\n", "ID", "Name");
        System.out.println("+-----+-------------------------+");
        for (Department dep : departments) {
            System.out.printf("|%5s|%25s|\n", dep.getId(), dep.getName());
        }
        System.out.println("+-----+-------------------------+");

    }
}