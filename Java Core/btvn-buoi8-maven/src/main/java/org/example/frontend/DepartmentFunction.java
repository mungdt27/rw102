package org.example.frontend;

import org.example.backend.controller.DepartmentController;
import org.example.entity.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentFunction {

    private DepartmentController departmentController;
    private Scanner sc;

    public DepartmentFunction(Scanner sc) {
        this.sc = sc;
        this.departmentController = new DepartmentController();
    }

    // In danh sách Department dạng bảng
    private void inDanhSachDepartment(List<Department> departments) {

        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Department Name");
        System.out.println("+-----+--------------------+");

        for (Department department : departments) {
            System.out.printf(
                    "|%5d|%20s|\n",
                    department.getId(),
                    department.getName()
            );
        }

        System.out.println("+-----+--------------------+");
    }

    // Question 1: Hiển thị Department
    public void hienThiDepartment() {

        System.out.println("==== HIỂN THỊ DEPARTMENT ====");

        List<Department> departments =
                departmentController.findAll();

        if (departments.isEmpty()) {
            System.out.println("Không có Department nào!");
            return;
        }

        inDanhSachDepartment(departments);
    }

    // Question 2: Tìm kiếm Department theo tên
    public void timKiemDepartment() {

        System.out.println("==== TÌM KIẾM DEPARTMENT ====");
        System.out.print("Nhập tên Department cần tìm: ");

        String name = sc.nextLine().trim();

        List<Department> departments =
                departmentController.findByName(name);

        if (departments.isEmpty()) {
            System.out.println("Không có kết quả tương ứng!");
            return;
        }

        inDanhSachDepartment(departments);
    }

    // Question 3: Thêm Department
    public void themMoiDepartment() {

        System.out.println("==== THÊM MỚI DEPARTMENT ====");

        int id;

        while (true) {
            System.out.print("Nhập ID Department: ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.err.println("ID phải là số! Nhập lại.");
                continue;
            }

            id = sc.nextInt();
            sc.nextLine();
            break;
        }

        System.out.print("Nhập tên Department: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Tên Department không được để trống!");
            return;
        }

        Department department = new Department(id, name);

        boolean check = departmentController.insert(department);

        if (check) {
            System.out.println("Thêm Department thành công!");
        } else {
            System.out.println("Thêm Department thất bại!");
        }
    }

    // Question 4: Xóa Department theo ID
    public void xoaDepartment() {

        System.out.println("==== XÓA DEPARTMENT THEO ID ====");

        int id;

        while (true) {
            System.out.print("Nhập ID Department cần xóa: ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.err.println("ID phải là số! Nhập lại.");
                continue;
            }

            id = sc.nextInt();
            sc.nextLine();
            break;
        }

        boolean check = departmentController.deleteById(id);

        if (check) {
            System.out.println("Xóa Department thành công!");
        } else {
            System.out.println("Xóa Department không thành công!");
        }
    }

    // Question 5: Update tên Department theo ID
    public void updateTenDepartment() {

        System.out.println("==== UPDATE TÊN DEPARTMENT THEO ID ====");

        int id;

        while (true) {
            System.out.print("Nhập ID Department cần update: ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.err.println("ID phải là số! Nhập lại.");
                continue;
            }

            id = sc.nextInt();
            sc.nextLine();
            break;
        }

        System.out.print("Nhập tên Department mới: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Tên Department không được để trống!");
            return;
        }

        boolean check = departmentController.updateName(id, name);

        if (check) {
            System.out.println("Update Department thành công!");
        } else {
            System.out.println("Update Department không thành công!");
        }
    }

    // Menu riêng cho Department
    public void menu() {

        while (true) {
            System.out.println();
            System.out.println("==== QUẢN LÝ DEPARTMENT ====");
            System.out.println("1. Hiển thị Department.");
            System.out.println("2. Tìm kiếm Department theo tên.");
            System.out.println("3. Thêm mới Department.");
            System.out.println("4. Xóa Department theo ID.");
            System.out.println("5. Update tên Department theo ID.");
            System.out.println("6. Quay lại menu chính.");
            System.out.print("Mời bạn chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    hienThiDepartment();
                    break;
                case "2":
                    timKiemDepartment();
                    break;
                case "3":
                    themMoiDepartment();
                    break;
                case "4":
                    xoaDepartment();
                    break;
                case "5":
                    updateTenDepartment();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Chọn sai, chọn lại!");
            }
        }
    }
}