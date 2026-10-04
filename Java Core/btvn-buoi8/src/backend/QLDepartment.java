package backend;

import entity.Department;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLDepartment implements IQLDepartment {

    private Scanner sc = new Scanner(System.in);

    // Question 1: Hiển thị department
    @Override
    public void hienThiDepartment() {
        System.out.println("==== HIỂN THỊ DEPARTMENT ====");
        List<Department> departments = new ArrayList<>();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM department";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Department department = new Department(id, name);
                departments.add(department);
            }

            resultSet.close();
            statement.close();
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    // Question 2: Tìm kiếm department theo tên
    @Override
    public void timKiemDepartment() {
        System.out.println("==== TÌM KIẾM DEPARTMENT THEO TÊN ====");
        System.out.print("Nhập tên department cần tìm: ");
        String name = sc.nextLine();

        List<Department> departments = new ArrayList<>();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT * " + "FROM department " + "WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String departmentName = resultSet.getString("name");
                Department department = new Department(id, departmentName);
                departments.add(department);
            }

            resultSet.close();
            statement.close();
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (departments.isEmpty()) {
            System.out.println("Không tìm thấy department.");
            return;
        }

        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Department Name");
        System.out.println("+-----+--------------------+");

        for (Department department : departments) {
            System.out.printf("|%5d|%20s|\n", department.getId(), department.getName());
        }

        System.out.println("+-----+--------------------+");
    }

    // Question 3: Thêm mới department
    @Override
    public void themMoiDepartment() {
        System.out.println("==== THÊM MỚI DEPARTMENT ====");
        System.out.print("Nhập id department: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập tên department: ");
        String name = sc.nextLine();
        String sql = "INSERT INTO department " + "(id, name) " + "VALUES (?, ?)";

        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);

            int c = statement.executeUpdate();

            if (c > 0) {
                System.out.println("Thêm department thành công!");
            } else {
                System.out.println("Thêm department thất bại!");
            }

            statement.close();
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Question 4: Xóa department theo id

    @Override
    public void xoaDepartment() {
        System.out.println("==== XÓA DEPARTMENT THEO ID ====");
        System.out.print("Nhập id department cần xóa: ");
        int id = sc.nextInt();
        sc.nextLine();
        String sql = "DELETE FROM department " + "WHERE id = ?";

        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int c = statement.executeUpdate();

            if (c > 0) {
                System.out.println("Xóa department thành công!");
            } else {
                System.out.println("Xóa department không thành công!");
            }

            statement.close();
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Question 5: Update tên phòng ban theo id
    @Override
    public void updateTenDepartment() {
        System.out.println("==== UPDATE TÊN DEPARTMENT THEO ID ====");
        System.out.print("Nhập id department cần update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập tên department mới: ");
        String name = sc.nextLine();
        String sql = "UPDATE department " + "SET name = ? " + "WHERE id = ?";

        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);

            int c = statement.executeUpdate();

            if (c > 0) {
                System.out.println("Update department thành công!");
            } else {
                System.out.println("Update department không thành công!");
            }

            statement.close();
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}