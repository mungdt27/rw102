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
    // 2. Tìm kiếm account
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


        for (Account account :
                accounts) {

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

            System.out.printf(
                    "|%4d|%22s|%12s|%22s|\n",
                    account.getId(),
                    account.getEmail(),
                    account.getUsername(),
                    account.getFullName()
            );

            System.out.println(
                    "+----+----------------------+------------+----------------------+"
            );
        }
    }


    // =========================================
    // 3. Thêm Account
    // =========================================

    @Override
    public void themAccount() {

        System.out.println(
                "==== THÊM MỚI ACCOUNT ===="
        );

        System.out.print("Nhập id: ");
        int id = sc.nextInt();
        sc.nextLine();


        System.out.print("Nhập email: ");
        String email = sc.nextLine();


        System.out.print("Nhập username: ");
        String username = sc.nextLine();


        System.out.print("Nhập fullname: ");
        String fullName = sc.nextLine();


        System.out.print(
                "Nhập department id: "
        );

        int departmentId =
                sc.nextInt();


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
                        id,
                        email,
                        username,
                        fullName,
                        department,
                        position,
                        LocalDate.now()
                );


        boolean result =
                controller.insert(account);


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
    // 4. Xóa Account
    // =========================================

    @Override
    public void xoaAccount() {

        System.out.println(
                "==== XÓA ACCOUNT THEO USERNAME ===="
        );

        System.out.print(
                "Nhập username cần xóa: "
        );

        String username =
                sc.nextLine();


        boolean result =
                controller.deleteByUsername(
                        username
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


    // =========================================
    // 5. Update FullName
    // =========================================

    @Override
    public void updateFullName() {

        System.out.println(
                "==== UPDATE FULLNAME THEO USERNAME ===="
        );

        System.out.print(
                "Nhập username cần update: "
        );

        String username =
                sc.nextLine();


        System.out.print(
                "Nhập fullname mới: "
        );

        String fullName =
                sc.nextLine();


        boolean result =
                controller.updateFullName(
                        username,
                        fullName
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