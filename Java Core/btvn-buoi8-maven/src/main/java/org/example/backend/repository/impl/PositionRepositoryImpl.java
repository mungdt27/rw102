package org.example.backend.repository.impl;


import org.example.backend.repository.IPositionRepository;
import org.example.entity.Position;
import org.example.entity.PositionName;
import org.example.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PositionRepositoryImpl implements IPositionRepository {
    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from position";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int positionId = resultSet.getInt("position_id");
                PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                Position position = new Position(positionId, positionName);
                positions.add(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeConnection(connection);
        }
        return positions;
    }

    @Override
    public Position findById(int id) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from position where position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int positionId = resultSet.getInt("position_id");
                String positionName = resultSet.getString("position_name");
                PositionName posName = PositionName.valueOf(positionName);
                Position position = new Position(positionId, posName);
                return position;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return null;
    }
}