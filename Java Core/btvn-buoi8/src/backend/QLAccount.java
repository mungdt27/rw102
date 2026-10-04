package backend;

import entity.Account;
import entity.Department;
import entity.Position;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLAccount implements IQLAccount {
    private Scanner sc = new Scanner(System.in);

    // Question 1: Hiển thị toàn bộ account
    @Override
    public void hienThiAccount() {
        System.out.println("==== HIỂN THỊ TOÀN BỘ ACCOUNT ====");

        List<Account> accounts = new ArrayList<>();

        try {
            // B1: Lấy kết nối
            Connection connection = JDBCUtils.getConnection();

            // B2: Tạo SQL
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

                            "LEFT JOIN `position` p " +
                            "ON a.position_id = p.id";

            // B3: PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);

            // B4: Execute query
            ResultSet resultSet = statement.executeQuery();

            // B5: Đọc dữ liệu
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String fullName = resultSet.getString("full_name");

                // Create date
                java.sql.Date sqlDate = resultSet.getDate("create_date");
                LocalDate createDate = null;

                if (sqlDate != null) {
                    createDate = sqlDate.toLocalDate();
                }

                // Department
                Department department = null;
                String departmentName = resultSet.getString("department_name");

                if (departmentName != null) {
                    int departmentId = resultSet.getInt("department_id");
                    department = new Department(departmentId, departmentName);
                }

                // Position
                Position position = null;
                String positionName = resultSet.getString("position_name");

                if (positionName != null) {
                    int positionId = resultSet.getInt("position_id");
                    Position.PositionName positionEnum = Position.PositionName.valueOf(positionName);
                    position = new Position(positionId, positionEnum);
                }

                // Tạo Account
                Account account = new Account(id, email, username, fullName, department, position, createDate);
                accounts.add(account);
            }

            // B6: Đóng
            resultSet.close();
            statement.close();
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    // Question 2: Tìm kiếm account theo username
    @Override
    public void timKiemAccount() {
        System.out.println("==== TÌM KIẾM ACCOUNT THEO USERNAME ====");
        System.out.print("Nhập username cần tìm: ");
        String username = sc.nextLine();

        List<Account> accounts = new ArrayList<>();

        try {
            Connection connection = JDBCUtils.getConnection();
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
                            "LEFT JOIN `position` p " +
                            "ON a.position_id = p.id " +
                            "WHERE a.username LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + username + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String usernameResult = resultSet.getString("username");
                String fullName = resultSet.getString("full_name");
                java.sql.Date sqlDate = resultSet.getDate("create_date");
                LocalDate createDate = null;
                if (sqlDate != null) {
                    createDate = sqlDate.toLocalDate();
                }

                Department department = null;
                String departmentName = resultSet.getString("department_name");
                if (departmentName != null) {
                    department = new Department(resultSet.getInt("department_id"), departmentName);
                }

                Position position = null;
                String positionName = resultSet.getString("position_name");
                if (positionName != null) {
                    Position.PositionName positionEnum = Position.PositionName.valueOf(positionName);
                    position = new Position(resultSet.getInt("position_id"), positionEnum);
                }

                Account account = new Account(
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
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (accounts.isEmpty()) {
            System.out.println("Không tìm thấy account.");
            return;
        }

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

    // Question 3: Thêm mới account
    @Override
    public void themAccount() {
        System.out.println("==== THÊM MỚI ACCOUNT ====");
        System.out.print("Nhập id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập email: ");
        String email = sc.nextLine();
        System.out.print("Nhập username: ");
        String username = sc.nextLine();
        System.out.print("Nhập fullname: ");
        String fullName = sc.nextLine();
        System.out.print("Nhập department id: ");
        int departmentId = sc.nextInt();
        System.out.print("Nhập position id: ");
        int positionId = sc.nextInt();
        sc.nextLine();

        String sql = "INSERT INTO account " +
                        "(id, email, username, full_name, " +
                        "department_id, position_id, create_date) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, email);
            statement.setString(3, username);
            statement.setString(4, fullName);
            statement.setInt(5, departmentId);
            statement.setInt(6, positionId);
            statement.setDate(7, java.sql.Date.valueOf(LocalDate.now()));

            int c = statement.executeUpdate();

            if (c > 0) {
                System.out.println("Thêm account thành công!");
            } else {
                System.out.println("Thêm account thất bại!");
            }

            statement.close();
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Question 4: Xóa account theo username
    @Override
    public void xoaAccount() {
        System.out.println("==== XÓA ACCOUNT THEO USERNAME ====");
        System.out.print("Nhập username cần xóa: ");
        String username = sc.nextLine();
        String sql = "DELETE FROM account " + "WHERE username = ?";

        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            int c = statement.executeUpdate();

            if (c > 0) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Xóa không thành công!");
            }

            statement.close();
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Question 5: Update fullname theo username
    @Override
    public void updateFullName() {
        System.out.println("==== UPDATE FULLNAME THEO USERNAME ====");
        System.out.print("Nhập username cần update: ");
        String username = sc.nextLine();
        System.out.print("Nhập fullname mới: ");
        String fullName = sc.nextLine();

        String sql = "UPDATE account " + "SET full_name = ? " + "WHERE username = ?";

        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fullName);
            statement.setString(2, username);

            int c = statement.executeUpdate();

            if (c > 0) {
                System.out.println("Update thành công!");
            } else {
                System.out.println("Update không thành công!");
            }

            statement.close();
            JDBCUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}