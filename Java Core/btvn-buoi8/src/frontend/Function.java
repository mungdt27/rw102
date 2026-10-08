package frontend;

import backend.controller.AccountController;
import backend.controller.DepartmentController;
import entity.Account;
import entity.Department;
import entity.Position;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Function {

    private AccountController accountController;
    private DepartmentController departmentController;
    private Scanner sc;

    public Function() {

        this.accountController =
                new AccountController();

        this.departmentController =
                new DepartmentController();

        this.sc =
                new Scanner(System.in);
    }


    // ==================================================
    // ACCOUNT
    // ==================================================

    // Question 1:
    // Thêm mới account + validation
    public void themMoi() {

        System.out.println(
                "==== THÊM MỚI ACCOUNT ===="
        );


        // ==========================================
        // Username
        // ==========================================

        System.out.print("Nhập username: ");

        String username;

        while (true) {

            username =
                    sc.nextLine().trim();


            // Check độ dài
            if (username.length() < 5
                    || username.length() > 50) {

                System.err.println(
                        "Username từ 5 đến 50 kí tự! Nhập lại"
                );

                continue;
            }


            // Check unique
            boolean check =
                    accountController.existByUsername(
                            username
                    );

            if (check) {

                System.err.println(
                        "Username này đã tồn tại! Nhập lại"
                );

                continue;
            }


            break;
        }


        // ==========================================
        // Email
        // ==========================================

        System.out.print("Nhập email: ");

        String email;

        while (true) {

            email =
                    sc.nextLine().trim();


            // Check độ dài
            if (email.length() < 5
                    || email.length() > 50) {

                System.err.println(
                        "Email từ 5 đến 50 kí tự! Nhập lại"
                );

                continue;
            }


            // Check format email
            if (!email.matches(
                    "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
            )) {

                System.err.println(
                        "Email không đúng định dạng! Nhập lại"
                );

                continue;
            }


            // Check unique
            boolean check =
                    accountController.existByEmail(
                            email
                    );

            if (check) {

                System.err.println(
                        "Email này đã tồn tại! Nhập lại"
                );

                continue;
            }


            break;
        }


        // ==========================================
        // FullName
        // ==========================================

        System.out.print("Nhập fullname: ");

        String fullName;

        while (true) {

            fullName =
                    sc.nextLine().trim();


            if (fullName.length() < 5
                    || fullName.length() > 50) {

                System.err.println(
                        "FullName từ 5 đến 50 kí tự! Nhập lại"
                );

                continue;
            }


            break;
        }


        // ==========================================
        // Department
        // ==========================================

        List<Department> departments =
                accountController.findAllDepartments();


        System.out.println(
                "==== DANH SÁCH DEPARTMENT ===="
        );

        System.out.println(
                "+-----+--------------------+"
        );

        System.out.printf(
                "|%5s|%20s|\n",
                "ID",
                "Name"
        );

        System.out.println(
                "+-----+--------------------+"
        );


        for (Department department :
                departments) {

            System.out.printf(
                    "|%5d|%20s|\n",
                    department.getId(),
                    department.getName()
            );
        }


        System.out.println(
                "+-----+--------------------+"
        );


        Department department = null;

        while (department == null) {

            System.out.print(
                    "Nhập department id: "
            );


            if (!sc.hasNextInt()) {

                sc.nextLine();

                System.err.println(
                        "Department id phải là số! Nhập lại"
                );

                continue;
            }


            int departmentId =
                    sc.nextInt();

            sc.nextLine();


            for (Department d :
                    departments) {

                if (d.getId() ==
                        departmentId) {

                    department =
                            d;

                    break;
                }
            }


            if (department == null) {

                System.err.println(
                        "Department id không tồn tại! Nhập lại"
                );
            }
        }


        // ==========================================
        // Position
        // ==========================================

        List<Position> positions =
                accountController.findAllPositions();


        System.out.println(
                "==== DANH SÁCH POSITION ===="
        );

        System.out.println(
                "+-----+--------------------+"
        );

        System.out.printf(
                "|%5s|%20s|\n",
                "ID",
                "Name"
        );

        System.out.println(
                "+-----+--------------------+"
        );


        for (Position position :
                positions) {

            System.out.printf(
                    "|%5d|%20s|\n",
                    position.getId(),
                    position.getName()
            );
        }


        System.out.println(
                "+-----+--------------------+"
        );


        Position position = null;

        while (position == null) {

            System.out.print(
                    "Nhập position id: "
            );


            if (!sc.hasNextInt()) {

                sc.nextLine();

                System.err.println(
                        "Position id phải là số! Nhập lại"
                );

                continue;
            }


            int positionId =
                    sc.nextInt();

            sc.nextLine();


            for (Position p :
                    positions) {

                if (p.getId() ==
                        positionId) {

                    position =
                            p;

                    break;
                }
            }


            if (position == null) {

                System.err.println(
                        "Position id không tồn tại! Nhập lại"
                );
            }
        }


        // ==========================================
        // Tạo Account
        // ID tự sinh
        // ==========================================

        Account account =
                new Account(
                        email,
                        username,
                        fullName,
                        department,
                        position,
                        LocalDate.now()
                );


        boolean check =
                accountController.save(
                        account
                );


        if (check) {

            System.out.println(
                    "Thêm mới thành công!"
            );

        } else {

            System.out.println(
                    "Thêm mới thất bại!"
            );
        }
    }


    // ==================================================
    // Question 2:
    // Tìm kiếm account
    // ==================================================

    public void timKiem() {

        System.out.println(
                "==== TÌM KIẾM ACCOUNT ===="
        );

        System.out.print(
                "Nhập username cần tìm: "
        );

        String username =
                sc.nextLine();


        List<Account> accounts =
                accountController.findByUsername(
                        username
                );


        if (accounts.isEmpty()) {

            System.out.println(
                    "Không có kết quả tương ứng!"
            );

            return;
        }


        System.out.println(
                "+----+----------------------+------------+----------------------+"
        );

        System.out.printf(
                "|%4s|%22s|%12s|%22s|\n",
                "ID",
                "Email",
                "Username",
                "Full Name"
        );

        System.out.println(
                "+----+----------------------+------------+----------------------+"
        );


        for (Account account :
                accounts) {

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


    // ==================================================
    // Question 3:
    // Hiển thị toàn bộ account
    // ==================================================

    public void hienThiToanBo() {

        List<Account> accounts =
                accountController.findAll();


        System.out.println(
                "==== HIỂN THỊ TOÀN BỘ ACCOUNT ===="
        );

        System.out.println(
                "+----+----------------------+------------+----------------------+"
        );

        System.out.printf(
                "|%4s|%22s|%12s|%22s|\n",
                "ID",
                "Email",
                "Username",
                "Full Name"
        );

        System.out.println(
                "+----+----------------------+------------+----------------------+"
        );


        for (Account account :
                accounts) {

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


    // ==================================================
    // Question 4:
    // Xóa account theo ID
    // ==================================================

    public void deleteById() {

        System.out.println(
                "==== XÓA ACCOUNT THEO ID ===="
        );


        int id;


        while (true) {

            System.out.print(
                    "Nhập id account cần xóa: "
            );


            if (!sc.hasNextInt()) {

                sc.nextLine();

                System.err.println(
                        "Id phải là số! Nhập lại"
                );

                continue;
            }


            id =
                    sc.nextInt();

            sc.nextLine();


            boolean check =
                    accountController.existById(id);


            if (!check) {

                System.err.println(
                        "Id không tồn tại! Nhập lại"
                );

                continue;
            }


            break;
        }


        boolean result =
                accountController.deleteById(
                        id
                );


        if (result) {

            System.out.println(
                    "Xóa thành công!"
            );

        } else {

            System.out.println(
                    "Xóa không thành công!"
            );
        }
    }


    // ==================================================
    // Question 5:
    // Update username theo ID
    // ==================================================

    public void updateUsername() {

        System.out.println(
                "==== UPDATE USERNAME THEO ID ===="
        );


        // ==========================================
        // Check ID
        // ==========================================

        int id;


        while (true) {

            System.out.print(
                    "Nhập id account cần update: "
            );


            if (!sc.hasNextInt()) {

                sc.nextLine();

                System.err.println(
                        "Id phải là số! Nhập lại"
                );

                continue;
            }


            id =
                    sc.nextInt();

            sc.nextLine();


            boolean check =
                    accountController.existById(id);


            if (!check) {

                System.err.println(
                        "Id không tồn tại! Nhập lại"
                );

                continue;
            }


            break;
        }


        // ==========================================
        // Check username mới
        // ==========================================

        System.out.print(
                "Nhập username mới: "
        );


        String username;


        while (true) {

            username =
                    sc.nextLine().trim();


            if (username.length() < 5
                    || username.length() > 50) {

                System.err.println(
                        "Username từ 5 đến 50 kí tự! Nhập lại"
                );

                continue;
            }


            boolean check =
                    accountController.existByUsername(
                            username
                    );


            if (check) {

                System.err.println(
                        "Username đã tồn tại! Nhập lại"
                );

                continue;
            }


            break;
        }


        boolean result =
                accountController.updateUsername(
                        id,
                        username
                );


        if (result) {

            System.out.println(
                    "Update thành công!"
            );

        } else {

            System.out.println(
                    "Update không thành công!"
            );
        }
    }


    // ==================================================
    // DEPARTMENT
    // ==================================================

    public void hienThiDepartment() {

        List<Department> departments =
                departmentController.findAll();


        System.out.println(
                "==== HIỂN THỊ DEPARTMENT ===="
        );

        System.out.println(
                "+-----+--------------------+"
        );

        System.out.printf(
                "|%5s|%20s|\n",
                "ID",
                "Department Name"
        );

        System.out.println(
                "+-----+--------------------+"
        );


        for (Department department :
                departments) {

            System.out.printf(
                    "|%5d|%20s|\n",
                    department.getId(),
                    department.getName()
            );
        }


        System.out.println(
                "+-----+--------------------+"
        );
    }


    public void timKiemDepartment() {

        System.out.println(
                "==== TÌM KIẾM DEPARTMENT ===="
        );

        System.out.print(
                "Nhập tên department cần tìm: "
        );

        String name =
                sc.nextLine();


        List<Department> departments =
                departmentController.findByName(
                        name
                );


        if (departments.isEmpty()) {

            System.out.println(
                    "Không có kết quả tương ứng!"
            );

            return;
        }


        System.out.println(
                "+-----+--------------------+"
        );

        System.out.printf(
                "|%5s|%20s|\n",
                "ID",
                "Department Name"
        );

        System.out.println(
                "+-----+--------------------+"
        );


        for (Department department :
                departments) {

            System.out.printf(
                    "|%5d|%20s|\n",
                    department.getId(),
                    department.getName()
            );
        }


        System.out.println(
                "+-----+--------------------+"
        );
    }


    public void themMoiDepartment() {

        System.out.println(
                "==== THÊM MỚI DEPARTMENT ===="
        );

        System.out.print(
                "Nhập id department: "
        );

        int id =
                sc.nextInt();

        sc.nextLine();


        System.out.print(
                "Nhập tên department: "
        );

        String name =
                sc.nextLine();


        Department department =
                new Department(
                        id,
                        name
                );


        boolean result =
                departmentController.insert(
                        department
                );


        if (result) {

            System.out.println(
                    "Thêm department thành công!"
            );

        } else {

            System.out.println(
                    "Thêm department thất bại!"
            );
        }
    }


    public void xoaDepartment() {

        System.out.println(
                "==== XÓA DEPARTMENT THEO ID ===="
        );

        System.out.print(
                "Nhập id department cần xóa: "
        );

        int id =
                sc.nextInt();

        sc.nextLine();


        boolean result =
                departmentController.deleteById(
                        id
                );


        if (result) {

            System.out.println(
                    "Xóa department thành công!"
            );

        } else {

            System.out.println(
                    "Xóa department không thành công!"
            );
        }
    }


    public void updateTenDepartment() {

        System.out.println(
                "==== UPDATE TÊN DEPARTMENT THEO ID ===="
        );

        System.out.print(
                "Nhập id department cần update: "
        );

        int id =
                sc.nextInt();

        sc.nextLine();


        System.out.print(
                "Nhập tên department mới: "
        );

        String name =
                sc.nextLine();


        boolean result =
                departmentController.updateName(
                        id,
                        name
                );


        if (result) {

            System.out.println(
                    "Update department thành công!"
            );

        } else {

            System.out.println(
                    "Update department không thành công!"
            );
        }
    }


    // ==================================================
    // MENU
    // ==================================================

    public void menu() {

        while (true) {

            System.out.println(
                    "==== MỜI BẠN CHỌN CHỨC NĂNG ===="
            );

            System.out.println(
                    "1. Thêm mới account."
            );

            System.out.println(
                    "2. Tìm kiếm account theo username."
            );

            System.out.println(
                    "3. Hiển thị toàn bộ account."
            );

            System.out.println(
                    "4. Xóa account theo id."
            );

            System.out.println(
                    "5. Update username theo id."
            );

            System.out.println(
                    "6. Hiển thị department."
            );

            System.out.println(
                    "7. Tìm kiếm department theo tên."
            );

            System.out.println(
                    "8. Thêm mới department."
            );

            System.out.println(
                    "9. Xóa department theo id."
            );

            System.out.println(
                    "10. Update tên phòng ban theo id."
            );

            System.out.println(
                    "11. Thoát."
            );


            String choice =
                    sc.nextLine();


            switch (choice) {

                case "1":
                    this.themMoi();
                    break;

                case "2":
                    this.timKiem();
                    break;

                case "3":
                    this.hienThiToanBo();
                    break;

                case "4":
                    this.deleteById();
                    break;

                case "5":
                    this.updateUsername();
                    break;

                case "6":
                    this.hienThiDepartment();
                    break;

                case "7":
                    this.timKiemDepartment();
                    break;

                case "8":
                    this.themMoiDepartment();
                    break;

                case "9":
                    this.xoaDepartment();
                    break;

                case "10":
                    this.updateTenDepartment();
                    break;

                case "11":
                    System.out.println("Thoát.");
                    System.exit(0);

                default:
                    System.out.println(
                            "Chọn sai, Chọn lại!"
                    );
            }
        }
    }
}