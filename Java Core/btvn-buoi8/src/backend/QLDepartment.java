package backend;

import backend.controller.DepartmentController;
import entity.Department;

import java.util.List;
import java.util.Scanner;

public class QLDepartment
        implements IQLDepartment {

    private Scanner sc =
            new Scanner(System.in);

    private DepartmentController controller =
            new DepartmentController();


    @Override
    public void hienThiDepartment() {

        System.out.println(
                "==== HIỂN THỊ DEPARTMENT ===="
        );

        List<Department> departments =
                controller.findAll();


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


    @Override
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
                controller.findByName(name);


        if (departments.isEmpty()) {

            System.out.println(
                    "Không tìm thấy department."
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


    @Override
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
                controller.insert(
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


    @Override
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
                controller.deleteById(id);


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


    @Override
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
                controller.updateName(
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
}