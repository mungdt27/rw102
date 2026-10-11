package org.example.frontend;

import org.example.backend.controller.AccountController;
import org.example.entity.Account;
import org.example.entity.Department;
import org.example.entity.Position;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AccountFunction {

    private AccountController accountController;
    private Scanner sc;

    public AccountFunction(Scanner sc) {
        this.sc = sc;
        this.accountController = new AccountController();
    }

    // In danh sách Account dạng bảng
    private void inDanhSachAccount(List<Account> accounts) {

        System.out.println(
                "+----+----------------------+------------+----------------------+"
        );
        System.out.printf(
                "|%4s|%22s|%12s|%22s|\n",
                "ID", "Email", "Username", "Full Name"
        );
        System.out.println(
                "+----+----------------------+------------+----------------------+"
        );

        for (Account account : accounts) {
            System.out.printf(
                    "|%4d|%22s|%12s|%22s|\n",
                    account.getId(),
                    account.getEmail(),
                    account.getUsername(),
                    account.getFullName()
            );
        }

        System.out.println(
                "+----+----------------------+------------+----------------------+"
        );
    }

    // Question 1: Thêm mới Account
    public void themMoi() {

        System.out.println("==== THÊM MỚI ACCOUNT ====");

        // Username: 5-50 ký tự, không trùng
        String username;

        while (true) {
            System.out.print("Nhập username: ");
            username = sc.nextLine().trim();

            if (username.length() < 5 || username.length() > 50) {
                System.err.println("Username từ 5 đến 50 ký tự! Nhập lại.");
                continue;
            }

            if (accountController.existByUsername(username)) {
                System.err.println("Username đã tồn tại! Nhập lại.");
                continue;
            }

            break;
        }

        // Email: 5-50 ký tự, đúng định dạng, không trùng
        String email;

        while (true) {
            System.out.print("Nhập email: ");
            email = sc.nextLine().trim();

            if (email.length() < 5 || email.length() > 50) {
                System.err.println("Email từ 5 đến 50 ký tự! Nhập lại.");
                continue;
            }

            if (!email.matches(
                    "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
            )) {
                System.err.println("Email không đúng định dạng! Nhập lại.");
                continue;
            }

            if (accountController.existByEmail(email)) {
                System.err.println("Email đã tồn tại! Nhập lại.");
                continue;
            }

            break;
        }

        // FullName: 5-50 ký tự
        String fullName;

        while (true) {
            System.out.print("Nhập fullname: ");
            fullName = sc.nextLine().trim();

            if (fullName.length() < 5 || fullName.length() > 50) {
                System.err.println("FullName từ 5 đến 50 ký tự! Nhập lại.");
                continue;
            }

            break;
        }

        // Lấy danh sách Department từ DB
        List<Department> departments =
                accountController.findAllDepartments();

        if (departments.isEmpty()) {
            System.out.println("Không có Department để lựa chọn!");
            return;
        }

        System.out.println("==== DANH SÁCH DEPARTMENT ====");
        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Name");
        System.out.println("+-----+--------------------+");

        for (Department d : departments) {
            System.out.printf(
                    "|%5d|%20s|\n",
                    d.getId(),
                    d.getName()
            );
        }

        System.out.println("+-----+--------------------+");

        Department department = null;

        while (department == null) {
            System.out.print("Nhập department id: ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.err.println("Department ID phải là số! Nhập lại.");
                continue;
            }

            int departmentId = sc.nextInt();
            sc.nextLine();

            for (Department d : departments) {
                if (d.getId() == departmentId) {
                    department = d;
                    break;
                }
            }

            if (department == null) {
                System.err.println("Department ID không tồn tại! Nhập lại.");
            }
        }

        // Lấy danh sách Position từ DB
        List<Position> positions =
                accountController.findAllPositions();

        if (positions.isEmpty()) {
            System.out.println("Không có Position để lựa chọn!");
            return;
        }

        System.out.println("==== DANH SÁCH POSITION ====");
        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Name");
        System.out.println("+-----+--------------------+");

        for (Position p : positions) {
            System.out.printf(
                    "|%5d|%20s|\n",
                    p.getId(),
                    p.getName()
            );
        }

        System.out.println("+-----+--------------------+");

        Position position = null;

        while (position == null) {
            System.out.print("Nhập position id: ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.err.println("Position ID phải là số! Nhập lại.");
                continue;
            }

            int positionId = sc.nextInt();
            sc.nextLine();

            for (Position p : positions) {
                if (p.getId() == positionId) {
                    position = p;
                    break;
                }
            }

            if (position == null) {
                System.err.println("Position ID không tồn tại! Nhập lại.");
            }
        }

        // Tạo object Account, không nhập ID vì DB tự sinh
        Account account = new Account(
                email,
                username,
                fullName,
                department,
                position,
                LocalDate.now()
        );

        boolean check = accountController.save(account);

        if (check) {
            System.out.println("Thêm mới thành công!");
        } else {
            System.out.println("Thêm mới thất bại!");
        }
    }

    // Question 2: Tìm kiếm Account theo username
    public void timKiem() {

        System.out.println("==== TÌM KIẾM ACCOUNT ====");
        System.out.print("Nhập username cần tìm: ");

        String username = sc.nextLine().trim();

        List<Account> accounts =
                accountController.findByUsername(username);

        if (accounts.isEmpty()) {
            System.out.println("Không có kết quả tương ứng!");
            return;
        }

        inDanhSachAccount(accounts);
    }

    // Question 3: Hiển thị toàn bộ Account
    public void hienThiToanBo() {

        System.out.println("==== HIỂN THỊ TOÀN BỘ ACCOUNT ====");

        List<Account> accounts = accountController.findAll();

        if (accounts.isEmpty()) {
            System.out.println("Không có Account nào!");
            return;
        }

        inDanhSachAccount(accounts);
    }

    // Question 4: Xóa Account theo ID
    public void deleteById() {

        System.out.println("==== XÓA ACCOUNT THEO ID ====");

        int id;

        while (true) {
            System.out.print("Nhập ID Account cần xóa: ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.err.println("ID phải là số! Nhập lại.");
                continue;
            }

            id = sc.nextInt();
            sc.nextLine();

            if (!accountController.existById(id)) {
                System.err.println("ID không tồn tại! Nhập lại.");
                continue;
            }

            break;
        }

        boolean check = accountController.deleteById(id);

        if (check) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Xóa không thành công!");
        }
    }

    // Question 5: Update username theo ID
    public void updateUsername() {

        System.out.println("==== UPDATE USERNAME THEO ID ====");

        int id;

        while (true) {
            System.out.print("Nhập ID Account cần update: ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.err.println("ID phải là số! Nhập lại.");
                continue;
            }

            id = sc.nextInt();
            sc.nextLine();

            if (!accountController.existById(id)) {
                System.err.println("ID không tồn tại! Nhập lại.");
                continue;
            }

            break;
        }

        String username;

        while (true) {
            System.out.print("Nhập username mới: ");
            username = sc.nextLine().trim();

            if (username.length() < 5 || username.length() > 50) {
                System.err.println("Username từ 5 đến 50 ký tự! Nhập lại.");
                continue;
            }

            if (accountController.existByUsername(username)) {
                System.err.println("Username đã tồn tại! Nhập lại.");
                continue;
            }

            break;
        }

        boolean check = accountController.updateUsername(id, username);

        if (check) {
            System.out.println("Update thành công!");
        } else {
            System.out.println("Update không thành công!");
        }
    }

    // Menu riêng cho Account
    public void menu() {

        while (true) {
            System.out.println();
            System.out.println("==== QUẢN LÝ ACCOUNT ====");
            System.out.println("1. Thêm mới Account.");
            System.out.println("2. Tìm kiếm Account theo username.");
            System.out.println("3. Hiển thị toàn bộ Account.");
            System.out.println("4. Xóa Account theo ID.");
            System.out.println("5. Update username theo ID.");
            System.out.println("6. Quay lại menu chính.");
            System.out.print("Mời bạn chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    themMoi();
                    break;
                case "2":
                    timKiem();
                    break;
                case "3":
                    hienThiToanBo();
                    break;
                case "4":
                    deleteById();
                    break;
                case "5":
                    updateUsername();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Chọn sai, chọn lại!");
            }
        }
    }
}