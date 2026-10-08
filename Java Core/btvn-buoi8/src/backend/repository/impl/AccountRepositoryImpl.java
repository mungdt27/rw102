package backend.repository.impl;

import backend.repository.IAccountRepository;
import entity.Account;
import entity.Department;
import entity.Position;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl
        implements IAccountRepository {

    @Override
    public List<Account> findAll() {

        List<Account> accounts =
                new ArrayList<>();

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "SELECT " +
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

            Statement statement =
                    connection.createStatement();

            ResultSet resultSet =
                    statement.executeQuery(sql);

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String email =
                        resultSet.getString("email");

                String username =
                        resultSet.getString("username");

                String fullName =
                        resultSet.getString("full_name");

                java.sql.Date sqlDate =
                        resultSet.getDate("create_date");

                LocalDate createDate = null;

                if (sqlDate != null) {
                    createDate =
                            sqlDate.toLocalDate();
                }

                Department department = null;

                String departmentName =
                        resultSet.getString(
                                "department_name"
                        );

                if (departmentName != null) {

                    department =
                            new Department(
                                    resultSet.getInt(
                                            "department_id"
                                    ),
                                    departmentName
                            );
                }

                Position position = null;

                String positionName =
                        resultSet.getString(
                                "position_name"
                        );

                if (positionName != null) {

                    position =
                            new Position(
                                    resultSet.getInt(
                                            "position_id"
                                    ),
                                    Position.PositionName.valueOf(
                                            positionName
                                    )
                            );
                }

                Account account =
                        new Account(
                                id,
                                email,
                                username,
                                fullName,
                                department,
                                position,
                                createDate
                        );

                accounts.add(account);
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return accounts;
    }


    @Override
    public List<Account> findByUsername(
            String username
    ) {

        List<Account> accounts =
                new ArrayList<>();

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "SELECT " +
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

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    "%" + username + "%"
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String email =
                        resultSet.getString("email");

                String usernameResult =
                        resultSet.getString("username");

                String fullName =
                        resultSet.getString("full_name");

                java.sql.Date sqlDate =
                        resultSet.getDate("create_date");

                LocalDate createDate = null;

                if (sqlDate != null) {
                    createDate =
                            sqlDate.toLocalDate();
                }

                Department department = null;

                String departmentName =
                        resultSet.getString(
                                "department_name"
                        );

                if (departmentName != null) {

                    department =
                            new Department(
                                    resultSet.getInt(
                                            "department_id"
                                    ),
                                    departmentName
                            );
                }

                Position position = null;

                String positionName =
                        resultSet.getString(
                                "position_name"
                        );

                if (positionName != null) {

                    position =
                            new Position(
                                    resultSet.getInt(
                                            "position_id"
                                    ),
                                    Position.PositionName.valueOf(
                                            positionName
                                    )
                            );
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

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return accounts;
    }


    // ==========================================
    // Load toàn bộ Department
    // ==========================================

    @Override
    public List<Department> findAllDepartments() {

        List<Department> departments =
                new ArrayList<>();

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "SELECT * FROM department";

            Statement statement =
                    connection.createStatement();

            ResultSet resultSet =
                    statement.executeQuery(sql);

            while (resultSet.next()) {

                Department department =
                        new Department(
                                resultSet.getInt("id"),
                                resultSet.getString("name")
                        );

                departments.add(department);
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return departments;
    }


    // ==========================================
    // Load toàn bộ Position
    // ==========================================

    @Override
    public List<Position> findAllPositions() {

        List<Position> positions =
                new ArrayList<>();

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "SELECT * FROM `position`";

            Statement statement =
                    connection.createStatement();

            ResultSet resultSet =
                    statement.executeQuery(sql);

            while (resultSet.next()) {

                Position position =
                        new Position(
                                resultSet.getInt("id"),
                                Position.PositionName.valueOf(
                                        resultSet.getString("name")
                                )
                        );

                positions.add(position);
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return positions;
    }


    // ==========================================
    // Kiểm tra ID tồn tại
    // ==========================================

    @Override
    public boolean existById(int id) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "SELECT id " +
                            "FROM account " +
                            "WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return false;
    }


    // ==========================================
    // Kiểm tra username tồn tại
    // ==========================================

    @Override
    public boolean existByUsername(
            String username
    ) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "SELECT username " +
                            "FROM account " +
                            "WHERE username = ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    username
            );

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return false;
    }


    // ==========================================
    // Kiểm tra email tồn tại
    // ==========================================

    @Override
    public boolean existByEmail(
            String email
    ) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "SELECT email " +
                            "FROM account " +
                            "WHERE email = ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    email
            );

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return false;
    }


    // ==========================================
    // Thêm Account
    // ==========================================

    @Override
    public boolean save(Account account) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "INSERT INTO account " +
                            "(email, username, full_name, " +
                            "department_id, position_id, create_date) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    account.getEmail()
            );

            statement.setString(
                    2,
                    account.getUsername()
            );

            statement.setString(
                    3,
                    account.getFullName()
            );

            statement.setInt(
                    4,
                    account.getDepartment().getId()
            );

            statement.setInt(
                    5,
                    account.getPosition().getId()
            );

            statement.setDate(
                    6,
                    java.sql.Date.valueOf(
                            account.getCreateDate()
                    )
            );

            int c =
                    statement.executeUpdate();

            return c > 0;

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return false;
    }


    // ==========================================
    // Xóa theo ID
    // ==========================================

    @Override
    public boolean deleteById(int id) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "DELETE FROM account " +
                            "WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(
                    1,
                    id
            );

            int c =
                    statement.executeUpdate();

            return c > 0;

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return false;
    }


    // ==========================================
    // Update username theo ID
    // ==========================================

    @Override
    public boolean updateUsername(
            int id,
            String username
    ) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "UPDATE account " +
                            "SET username = ? " +
                            "WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    username
            );

            statement.setInt(
                    2,
                    id
            );

            int c =
                    statement.executeUpdate();

            return c > 0;

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return false;
    }
}