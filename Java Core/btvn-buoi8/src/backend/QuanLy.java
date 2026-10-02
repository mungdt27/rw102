package backend;

import entity.Account;
import entity.Department;
import entity.Position;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLy implements IQuanLy {
    private List<Account> accountList;
    private List<Department> departmentList;

    private Scanner sc = new Scanner(System.in);

    public QuanLy() {
        accountList = new ArrayList<>();
        departmentList = new ArrayList<>();
    }

    // Question 1: Hiển thị toàn bộ account
    @Override
    public void hienThiToanBoAccount() {
        System.out.println("==== HIỂN THỊ TOÀN BỘ ACCOUNT ====");
        List<Account> accounts = new ArrayList<>();
        try {
            // B1: Lấy dữ liệu từ DB
            String url = "jdbc:mysql://localhost:3306/btvn_buoi8";
            String username = "root";
            String password = "";
            // B2: Tạo kết nối đến Database
            Connection connection = DriverManager.getConnection(url, username, password);
            // B3: Tạo SQL
            String sql = "SELECT " +
                            "a.id, " +
                            "a.email, " +
                            "a.username, " +
                            "a.full_name, " +
                            "a.create_date, " +
                            "d.id AS department_id, " +
                            "d.name AS department_name, " +
                            "p.id AS position_id, " +
                            "p.name AS position_name " +
                            "FROM account a " +
                            "LEFT JOIN department d " +
                            "ON a.department_id = d.id " +
                            "LEFT JOIN position p " +
                            "ON a.position_id = p.id";

            // B4: Tạo PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);

            // B5: Thực hiện query
            ResultSet resultSet = statement.executeQuery();

            // B6: Chuyển dữ liệu từ DB thành object
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String usernameAccount = resultSet.getString("username");
                String fullName = resultSet.getString("full_name");
                java.sql.Date sqlDate = resultSet.getDate("create_date");
                LocalDate createDate = sqlDate != null ? sqlDate.toLocalDate() : null;

                // Department
                Department department = null;
                int departmentId = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                if (!resultSet.wasNull()) {
                    department = new Department(departmentId, departmentName);
                }

                // Position
                Position position = null;
                int positionId = resultSet.getInt("position_id");
                String positionName = resultSet.getString("position_name");
                if (!resultSet.wasNull()) {
                    Position.PositionName positionEnum = Position.PositionName.valueOf(positionName);
                    position = new Position(positionId, positionEnum);
                }
                Account account = new Account(id, email, usernameAccount, fullName, department, position, createDate);
                accounts.add(account);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Lưu list lấy từ DB
        accountList = accounts;

        // Hiển thị dạng bảng
        System.out.println("+----+----------------------+------------+----------------------+--------------------+----------------+------------+");
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
        System.out.println("+----+----------------------+------------+----------------------+--------------------+----------------+------------+");

        for (Account account : accountList) {
            String departmentName = "";
            if (account.getDepartment() != null) {
                departmentName = account.getDepartment().getName();
            }
            String positionName = "";
            if (account.getPosition() != null) {
                positionName = account.getPosition().getName().toString();
            }
            String createDate = "";
            if (account.getCreateDate() != null) {
                createDate = account.getCreateDate().toString();
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

        System.out.println("+----+----------------------+------------+----------------------+--------------------+----------------+------------+");
    }

    // Question 2: Tìm kiếm account theo username
    @Override
    public void timKiemAccountTheoUsername() {
        System.out.println("==== TÌM KIẾM ACCOUNT THEO USERNAME ====");
        System.out.print("Nhập username cần tìm: ");
        String usernameAccount = sc.nextLine();
        List<Account> accounts = new ArrayList<>();

        try {
            // B1: Kết nối DB
            String url = "jdbc:mysql://localhost:3306/btvn_buoi8";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            // B2: SQL tìm kiếm gần đúng
            String sql = "SELECT " +
                            "a.id, " +
                            "a.email, " +
                            "a.username, " +
                            "a.full_name, " +
                            "a.create_date, " +
                            "d.id AS department_id, " +
                            "d.name AS department_name, " +
                            "p.id AS position_id, " +
                            "p.name AS position_name " +
                            "FROM account a " +
                            "LEFT JOIN department d " +
                            "ON a.department_id = d.id " +
                            "LEFT JOIN position p " +
                            "ON a.position_id = p.id " +
                            "WHERE a.username LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + usernameAccount + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String usernameResult = resultSet.getString("username");
                String fullName = resultSet.getString("full_name");
                java.sql.Date sqlDate = resultSet.getDate("create_date");
                LocalDate createDate = sqlDate != null ? sqlDate.toLocalDate() : null;

                Department department = null;
                int departmentId = resultSet.getInt("department_id");

                String departmentName = resultSet.getString("department_name");

                if (!resultSet.wasNull()) {
                    department = new Department(departmentId, departmentName);
                }

                Position position = null;
                int positionId = resultSet.getInt("position_id");
                String positionName = resultSet.getString("position_name");
                if (!resultSet.wasNull()) {
                    Position.PositionName positionEnum = Position.PositionName.valueOf(positionName);
                    position = new Position(positionId, positionEnum);
                }

                Account account =
                        new Account(
                                id,
                                email,
                                usernameResult,
                                fullName,
                                department,
                                position,
                                createDate
                        );

                accounts.add(account);
            }


            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


        if (accounts.isEmpty()) {

            System.out.println(
                    "Không tìm thấy account."
            );

            return;
        }

        // Hiển thị kết quả
        System.out.println("+----+----------------------+------------+----------------------+--------------------+----------------+------------+");
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
        System.out.println("+----+----------------------+------------+----------------------+--------------------+----------------+------------+");

        for (Account account : accounts) {
            String departmentName = "";
            if (account.getDepartment() != null) {
                departmentName = account.getDepartment().getName();
            }

            String positionName = "";
            if (account.getPosition() != null) {
                positionName = account.getPosition().getName().toString();
            }

            String createDate = "";
            if (account.getCreateDate() != null) {
                createDate = account.getCreateDate().toString();
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
        System.out.println("+----+----------------------+------------+----------------------+--------------------+----------------+------------+");
    }

    // Question 3: Hiển thị department

    @Override
    public void hienThiDepartment() {
        System.out.println("==== HIỂN THỊ DEPARTMENT ====");
        List<Department> departments = new ArrayList<>();

        try {
            String url = "jdbc:mysql://localhost:3306/btvn_buoi8";
            String username = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, username, password);

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
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        departmentList = departments;
        System.out.println("+-----+--------------------+");

        System.out.printf(
                "|%5s|%20s|\n",
                "ID",
                "Department Name"
        );

        System.out.println("+-----+--------------------+");

        for (Department department : departmentList) {
            System.out.printf(
                    "|%5d|%20s|\n",
                    department.getId(),
                    department.getName()
            );
        }


        System.out.println("+-----+--------------------+");
    }

    // Question 4: Tìm kiếm department theo tên

    @Override
    public void timKiemDepartmentTheoTen() {

        System.out.println(
                "==== TÌM KIẾM DEPARTMENT THEO TÊN ===="
        );

        System.out.print(
                "Nhập tên department cần tìm: "
        );

        String name =
                sc.nextLine();


        List<Department> departments =
                new ArrayList<>();

        try {

            String url =
                    "jdbc:mysql://localhost:3306/btvn_buoi8";
            String username = "root";
            String password = "";


            Connection connection =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );


            String sql =
                    "SELECT * " +
                            "FROM department " +
                            "WHERE name LIKE ?";


            PreparedStatement statement =
                    connection.prepareStatement(sql);


            statement.setString(
                    1,
                    "%" + name + "%"
            );


            ResultSet resultSet =
                    statement.executeQuery();


            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String departmentName =
                        resultSet.getString("name");


                Department department =
                        new Department(
                                id,
                                departmentName
                        );


                departments.add(department);
            }


            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


        if (departments.isEmpty()) {

            System.out.println("Không tìm thấy department.");

            return;
        }

        System.out.println("+-----+--------------------+");

        System.out.printf(
                "|%5s|%20s|\n",
                "ID",
                "Department Name"
        );
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
}