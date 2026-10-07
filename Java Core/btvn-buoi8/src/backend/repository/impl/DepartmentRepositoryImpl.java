package backend.repository.impl;

import backend.repository.IDepartmentRepository;
import entity.Department;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl
        implements IDepartmentRepository {

    @Override
    public List<Department> findAll() {

        List<Department> departments =
                new ArrayList<>();

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "SELECT * FROM department";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String name =
                        resultSet.getString("name");

                Department department =
                        new Department(
                                id,
                                name
                        );

                departments.add(
                        department
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return departments;
    }


    @Override
    public List<Department> findByName(
            String name
    ) {

        List<Department> departments =
                new ArrayList<>();

        try {

            Connection connection =
                    JDBCUtils.getConnection();

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

                departments.add(
                        department
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            JDBCUtils.closeConnection();
        }

        return departments;
    }


    @Override
    public boolean insert(
            Department department
    ) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "INSERT INTO department " +
                            "(id, name) " +
                            "VALUES (?, ?)";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(
                    1,
                    department.getId()
            );

            statement.setString(
                    2,
                    department.getName()
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
    public boolean deleteById(
            int id
    ) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "DELETE FROM department " +
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


    @Override
    public boolean updateName(
            int id,
            String name
    ) {

        try {

            Connection connection =
                    JDBCUtils.getConnection();

            String sql =
                    "UPDATE department " +
                            "SET name = ? " +
                            "WHERE id = ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    name
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