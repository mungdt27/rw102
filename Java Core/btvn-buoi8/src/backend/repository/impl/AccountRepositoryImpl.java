package backend.repository.impl;

import backend.repository.IAccountRepository;
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

public class AccountRepositoryImpl implements IAccountRepository {

    @Override
    public List<Account> findAll() {
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
                            "ON a.position_id = p.id";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
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


                Account account = new Account(id, email, username, fullName, department, position, createDate);
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

                    Position.PositionName positionEnum =
                            Position.PositionName.valueOf(
                                    positionName
                            );

                    position =
                            new Position(
                                    resultSet.getInt(
                                            "position_id"
                                    ),
                                    positionEnum
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


    @Override
    public boolean insert(
            Account account
    ) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "INSERT INTO account " +
                            "(id, email, username, full_name, " +
                            "department_id, position_id, create_date) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(
                    1,
                    account.getId()
            );

            statement.setString(
                    2,
                    account.getEmail()
            );

            statement.setString(
                    3,
                    account.getUsername()
            );

            statement.setString(
                    4,
                    account.getFullName()
            );

            statement.setInt(
                    5,
                    account.getDepartment().getId()
            );

            statement.setInt(
                    6,
                    account.getPosition().getId()
            );

            statement.setDate(
                    7,
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


    @Override
    public boolean deleteByUsername(
            String username
    ) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "DELETE FROM account " +
                            "WHERE username = ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    username
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


    @Override
    public boolean updateFullName(
            String username,
            String fullName
    ) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "UPDATE account " +
                            "SET full_name = ? " +
                            "WHERE username = ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    fullName
            );

            statement.setString(
                    2,
                    username
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