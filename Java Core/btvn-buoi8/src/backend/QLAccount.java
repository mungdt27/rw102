package backend;

import backend.controller.AccountController;
import entity.Account;
import entity.Department;
import entity.Position;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class QLAccount implements IQLAccount {

    private Scanner sc =
            new Scanner(System.in);

    private AccountController controller =
            new AccountController();


    // =========================================
    // 1. Hiển thị toàn bộ account
    // =========================================

    @Override
    public void hienThiAccount() {

        System.out.println(
                "==== HIỂN THỊ TOÀN BỘ ACCOUNT ===="
        );

        List<Account> accounts =
                controller.findAll();


        System.out.println(
                "+----+----------------------+------------+----------------------+--------------------+----------------+------------+"
        );

        System.out.printf(
                "|%4s|%22s|%12s|%22s|%20s|%16s|%12s|\n",
                "ID",
                "Email",
                "Username",
                "Full Name",
                "Department",
                "Position",
                "Create Date"
        );

        System.out.println(
                "+----+----------------------+------------+----------------------+--------------------+----------------+------------+"
        );


        for (Account account :
                accounts) {

            String departmentName = "";

            if (account.getDepartment() != null) {

                departmentName =
                        account.getDepartment().getName();
            }


            String positionName = "";

            if (account.getPosition() != null) {

                positionName =
                        account.getPosition()
                                .getName()
                                .toString();
            }


            String createDate = "";

            if (account.getCreateDate() != null) {

                createDate =
                        account.getCreateDate().toString();
            }


            System.out.printf(
                    "|%4d|%22s|%12s|%22s|%20s|%16s|%12s|\n",
                    account.getId(),
                    account.getEmail(),
                    account.getUsername(),
                    account.getFullName(),
                    departmentName,
                    positionName,
                    createDate
            );
        }


        System.out.println(
                "+----+----------------------+------------+----------------------+--------------------+----------------+------------+"
        );
    }


    // =========================================
    // 2. Tìm kiếm account theo username
    // =========================================

    @Override
    public void timKiemAccount() {

        System.out.println(
                "==== TÌM KIẾM ACCOUNT THEO USERNAME ===="
        );

        System.out.print(
                "Nhập username cần tìm: "
        );

        String username =
                sc.nextLine();


        List<Account> accounts =
                controller.findByUsername(
                        username
                );


        if (accounts.isEmpty()) {

            System.out.println(
                    "Không tìm thấy account."
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


    // =========================================
    // 3. Thêm Account
    // =========================================

    @Override
    public void themAccount() {

        System.out.println(
                "==== THÊM MỚI ACCOUNT ===="
        );

        System.out.print("Nhập email: ");
        String email =
                sc.nextLine();

        System.out.print("Nhập username: ");
        String username =
                sc.nextLine();

        System.out.print("Nhập fullname: ");
        String fullName =
                sc.nextLine();


        // Chọn Department
        System.out.println(
                "Danh sách Department:"
        );

        List<Department> departments =
                controller.findAllDepartments();

        for (Department department :
                departments) {

            System.out.println(
                    department.getId()
                            + ". "
                            + department.getName()
            );
        }


        System.out.print(
                "Nhập department id: "
        );

        int departmentId =
                sc.nextInt();


        // Chọn Position
        System.out.println(
                "Danh sách Position:"
        );

        List<Position> positions =
                controller.findAllPositions();

        for (Position position :
                positions) {

            System.out.println(
                    position.getId()
                            + ". "
                            + position.getName()
            );
        }


        System.out.print(
                "Nhập position id: "
        );

        int positionId =
                sc.nextInt();

        sc.nextLine();


        Department department =
                new Department(
                        departmentId,
                        ""
                );


        Position position =
                new Position(
                        positionId,
                        null
                );


        Account account =
                new Account(
                        email,
                        username,
                        fullName,
                        department,
                        position,
                        LocalDate.now()
                );


        // Đổi insert() thành save()
        boolean result =
                controller.save(account);


        if (result) {

            System.out.println(
                    "Thêm account thành công!"
            );

        } else {

            System.out.println(
                    "Thêm account thất bại!"
            );
        }
    }


    // =========================================
    // 4. Xóa Account theo ID
    // =========================================

    @Override
    public void xoaAccount() {

        System.out.println(
                "==== XÓA ACCOUNT THEO ID ===="
        );

        System.out.print(
                "Nhập id account cần xóa: "
        );

        int id =
                sc.nextInt();

        sc.nextLine();


        // Đổi deleteByUsername()
        // thành deleteById()
        boolean result =
                controller.deleteById(id);


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


    // =========================================
    // 5. Update username theo ID
    // =========================================

    @Override
    public void updateUsername() {

        System.out.println(
                "==== UPDATE USERNAME THEO ID ===="
        );

        System.out.print(
                "Nhập id account cần update: "
        );

        int id =
                sc.nextInt();

        sc.nextLine();


        System.out.print(
                "Nhập username mới: "
        );

        String username =
                sc.nextLine();


        // Đổi updateFullName()
        // thành updateUsername()
        boolean result =
                controller.updateUsername(
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
}