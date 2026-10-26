package org.example.backend.repository.impl;


import org.example.backend.repository.IDepartmentRepository;
import org.example.entity.Department;
import org.example.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from department";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int departmentId = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                Department department = new Department(departmentId, departmentName);
                departments.add(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return departments;
    }

    @Override
    public Department findById(int id) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from department where department_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int departmentId = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                Department department = new Department(departmentId, departmentName);
                return department;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public boolean create(Department department) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into department(department_name) values (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, department.getName());
            int c = statement.executeUpdate();
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public void createAll(List<Department> departments) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into department(department_name) values (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            for (Department dep: departments) {
                statement.setString(1, dep.getName());
                statement.addBatch();// theem dư lieu cân insert vao 1 lo
            }
            statement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public boolean existByName(String name) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from department where department_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }
}