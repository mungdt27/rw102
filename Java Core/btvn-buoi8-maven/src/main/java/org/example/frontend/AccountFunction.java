package org.example.frontend;

import org.example.backend.controller.AccountController;
import org.example.backend.controller.DepartmentController;
import org.example.backend.controller.PositionController;
import org.example.entity.Account;
import org.example.entity.Department;
import org.example.entity.Position;
import org.example.utils.ScannerUtils;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AccountFunction {
    private AccountController accountController;
    private DepartmentController departmentController;
    private PositionController positionController;
    private Scanner scanner;

    public AccountFunction() {
        this.accountController = new AccountController();
        this.departmentController = new DepartmentController();
        this.positionController = new PositionController();
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Hiển thị toàn bộ account");
            System.out.println("2. Thêm account");
            System.out.println("3. Xóa account theo id");
            System.out.println("4. Update account theo id");
            System.out.println("5. Tìm kiếm theo username");
            System.out.println("6. Import file CSV");
            System.out.println("7. Thoát");
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
                    this.findByUsername();
                    break;
                case "6":
                    this.importCSV();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Chọn sai! Chọn lại!");
            }
        }
    }

    private void importCSV() {
        System.out.println("==== CHỨC NĂNG IMPORT ACCOUNT ====");
        // C:\Users\PNV\Desktop\rw102\Java Core\csv\input_department.csv
        System.out.println("Nhập đường dẫn file CSV: ");
        String path = scanner.nextLine();
        String message = accountController.importCSV(path);
        System.out.println(message);
    }

    private void findByUsername() {
        System.out.println("==== CHỨC NĂNG UPDATE ACCOUNT ====");
        System.out.println("Mời bạn nhập Username muốn tìm: ");
        String username = scanner.nextLine();

        List<Account> accounts = accountController.findByUsername(username);

        if (accounts.isEmpty()) {// ds trống
            System.out.println("Không có kết quả tương ứng");
        } else {
            System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
            System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n", "ID", "Username", "Full Name", "Email", "Department", "Position");
            System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
            for (Account acc : accounts) {
                String depName = Objects.nonNull(acc.getDepartment()) ? acc.getDepartment().getName() : "";
                String posName = Objects.nonNull(acc.getPosition()) ? acc.getPosition().getName().name() : "";
                System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n"
                        , acc.getId(), acc.getUsername(), acc.getFullName(), acc.getEmail()
                        , depName, posName);
            }
            System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        }
    }

    private void update() {
        System.out.println("==== CHỨC NĂNG UPDATE ACCOUNT ====");
        System.out.println("Mời bạn nhập ID account muốn update thông tin: ");
        int id = 0;
        while (true) {
            if (scanner.hasNextInt()) {// 1 kiem tra xem co phai la so ko
                id = scanner.nextInt();
                scanner.nextLine();
                if (id <= 0) {  // 2 kiem tra <= 0
                    System.err.println("Vui lòng nhập số > 0: ");
                } else {
                    // 3 kiem tra id  co ton tai ko
                    boolean checkExists = accountController.checkIdExists(id);
                    if (!checkExists) {
                        System.err.println("ID này không tồn tại! Nhập lại: ");
                    } else {
                        break;
                    }
                }
            } else {
                System.err.println("Vui lòng nhập số! Nhập lại: ");
                scanner.nextLine();
            }
        }

        String username;
        while (true) {
            System.out.println("Nhập username: ");
            username = scanner.nextLine();
            if (username.trim().length() < 5 || username.trim().length() > 50) {
                System.err.println("Nhập username dài từ 5-50 kí tự");
                continue;
            }
            if (accountController.checkUsernameExists(username, id)) {
                System.err.println("Username này đã tồn tại");
                continue;
            }
            break;
        }

        boolean check = accountController.update(id, username);
        if (check) {
            System.out.println("Update successfully!");
        } else {
            System.out.println("Update failed!");
        }
    }

    private void delete() {
        System.out.println("==== CHỨC NĂNG XÓA ACCOUNT ====");
        System.out.println("Mời bạn nhập ID account muốn xóa: ");
        int id = 0;
        while (true) {
            if (scanner.hasNextInt()) {// 1 kiem tra xem co phai la so ko
                id = scanner.nextInt();
                scanner.nextLine();
                if (id <= 0) {  // 2 kiem tra <= 0
                    System.err.println("Vui lòng nhập số > 0: ");
                } else {
                    // 3 kiem tra id  co ton tai ko
                    boolean checkExists = accountController.checkIdExists(id);
                    if (!checkExists) {
                        System.err.println("ID này không tồn tại! Nhập lại: ");
                    } else {
                        break;
                    }
                }
            } else {
                System.err.println("Vui lòng nhập số! Nhập lại: ");
                scanner.nextLine();
            }
        }

        // xóa
        boolean canDelete = accountController.delete(id);
        if (canDelete) {
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Delete failed!");
        }
    }

    private void create() {
        System.out.println("====CHỨC NĂNG TẠO MỚI ACCOUNT ====");
        Account account = new Account();

        while (true) {
            System.out.println("Nhập username: ");
            String username = ScannerUtils.inputText(5, 50, "Độ dài username từ 5- 50 kí tự! Nhập lại:");
            if (accountController.checkUsernameExists(username, null)) {
                System.err.println("Username này đã tồn tại");
                continue;
            }
            account.setUsername(username);
            break;
        }

        while (true) {
            System.out.println("Nhập fullname: ");
            String fullName = ScannerUtils.inputText(5, 50, "Độ dài fullname từ 5- 50 kí tự! Nhập lại:");
            account.setFullName(fullName);
            break;
        }

        while (true) {
            String email = ScannerUtils.inputEmail();
            if (accountController.checkEmailExists(email, null)) {
                System.err.println("email này đã tồn tại");
                continue;
            }
            account.setEmail(email);
            break;
        }

        List<Department> departments = departmentController.findAll();

        while (true) {
            Department department = null;
            System.out.println("Chọn ID phòng ban muốn thêm vào: ");

            for (Department dep : departments) {
                System.out.printf("ID: %s - Name: %s\n", dep.getId(), dep.getName());
            }
            if (scanner.hasNextInt()) {
                int depId = scanner.nextInt();
                scanner.nextLine();
                if (depId <= 0) {
                    System.err.println("DepartmentID phải lớn hơn 0! Nhập lại: ");
                    continue;
                }

                // tìm depId có tồn tại ko
                for (Department dep : departments) {
                    if (dep.getId() == depId) {
                        department = dep;
                        break;
                    }
                }
                if (Objects.isNull(department)) {
                    System.out.println("DepartmentID ko tồn tại! Nhập lại: ");
                } else {
                    account.setDepartment(department);
                    break;
                }
            } else {
                System.out.println("Vui lòng nhập số. Chọn lại phòng ban!");
                scanner.nextLine();
            }
        }

        List<Position> positions = positionController.findAll();
        while (true) {
            Position position = null;
            System.out.println("Chọn ID chức vụ muốn thêm vào: ");
            for (Position pos : positions) {
                System.out.printf("ID: %s - Name: %s\n", pos.getId(), pos.getName());
            }
            int posID = ScannerUtils.inputInt(false, 1, 5, "Vui lòng nhập số nguyên và lớn hơn 0! ");
            for (Position pos : positions) {
                if (pos.getId() == posID) {
                    position = pos;
                    break;
                }
            }
            if (Objects.isNull(position)) {
                System.out.println("PositionID ko tồn tại. Nhập lại: ");
            } else {
                account.setPosition(position);
                break;
            }

        }

        boolean check = accountController.create(account);// username, fullName, email,depID, posID
        if (check) {
            System.out.println("Create successfully!");
        } else {
            System.out.println("Create failed!");
        }
    }

    private void findAll() {
        System.out.println("==== HIỂN THỊ TOÀN BỘ ACCOUNT ====");
        List<Account> accounts = accountController.findAll();
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n", "ID", "Username", "Full Name", "Email", "Department", "Position");
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        for (Account acc : accounts) {
            String depName = Objects.nonNull(acc.getDepartment()) ? acc.getDepartment().getName() : "";
            String posName = Objects.nonNull(acc.getPosition()) ? acc.getPosition().getName().name() : "";
            System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n"
                    , acc.getId(), acc.getUsername(), acc.getFullName(), acc.getEmail()
                    , depName, posName);
        }
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
    }
}