package frontend;

import backend.IQLAccount;
import backend.IQLDepartment;
import backend.QLAccount;
import backend.QLDepartment;

import java.util.Scanner;

public class Function {

    private Scanner sc =
            new Scanner(System.in);

    private IQLAccount iqlAccount =
            new QLAccount();

    private IQLDepartment iqlDepartment =
            new QLDepartment();


    public void menu() {

        while (true) {

            System.out.println();
            System.out.println(
                    "==== MỜI BẠN CHỌN CHỨC NĂNG ===="
            );

            System.out.println(
                    "1. Hiển thị toàn bộ account."
            );

            System.out.println(
                    "2. Tìm kiếm account theo username."
            );

            System.out.println(
                    "3. Thêm mới account."
            );

            System.out.println(
                    "4. Xóa account theo username."
            );

            System.out.println(
                    "5. Update fullname theo username."
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
                    iqlAccount.hienThiAccount();
                    break;

                case "2":
                    iqlAccount.timKiemAccount();
                    break;

                case "3":
                    iqlAccount.themAccount();
                    break;

                case "4":
                    iqlAccount.xoaAccount();
                    break;

                case "5":
                    iqlAccount.updateFullName();
                    break;

                case "6":
                    iqlDepartment.hienThiDepartment();
                    break;

                case "7":
                    iqlDepartment.timKiemDepartment();
                    break;

                case "8":
                    iqlDepartment.themMoiDepartment();
                    break;

                case "9":
                    iqlDepartment.xoaDepartment();
                    break;

                case "10":
                    iqlDepartment.updateTenDepartment();
                    break;

                case "11":
                    System.out.println("Thoát.");
                    return;

                default:
                    System.out.println(
                            "Chọn sai, Chọn lại!"
                    );
            }
        }
    }
}