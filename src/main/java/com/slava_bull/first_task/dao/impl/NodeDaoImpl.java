package com.slava_bull.first_task.dao.impl;

import com.slava_bull.first_task.dao.NodeDao;
import com.slava_bull.first_task.db.DBInitializer;
import com.slava_bull.first_task.model.Node;

import java.sql.*;
import java.util.List;

public class NodeDaoImpl implements NodeDao {

    private static final String SQL_GET_BY_ID = "select * " +
                                                "from nodes " +
                                                "where id = ?";

    private static final String SQL_INSERT = "insert into nodes(id, lat, lon, username) " +
                                             "values (?, ?, ?, ?)";

    private static void prepareStatement(PreparedStatement statement, Node node) throws SQLException {
        int i = 1;
        statement.setLong(i++, node.getId());
        statement.setDouble(i++, node.getLat());
        statement.setDouble(i++, node.getLon());
        statement.setString(i++, node.getUser());
    }

    @Override
    public Node getById(long nodeId) throws SQLException {
        Connection connection = DBInitializer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID);
        statement.setLong(1, nodeId);
        ResultSet resultSet = statement.executeQuery(SQL_GET_BY_ID);
        return resultSet.next() ? Node.fromRS(resultSet) : null;
    }

    @Override
    public void insertNode(Node node) throws SQLException {
        Connection connection = DBInitializer.getConnection();
        Statement statement = connection.createStatement();
        String sql = String.format("insert into nodes(id, lon, lat, username) values(%d, %f, %f, %s)",
                node.getId(), node.getLon(), node.getLat(), node.getUser());
        statement.execute(sql);
    }

    @Override
    public void insertPreparedNode(Node node) throws SQLException {
        Connection connection = DBInitializer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        prepareStatement(statement, node);
        statement.execute();
    }

    @Override
    public void batchInsertNodes(List<Node> nodes) throws SQLException {
        Connection connection = DBInitializer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        for (Node node : nodes) {
            prepareStatement(statement, node);
            statement.addBatch();
        }
        statement.executeBatch();
    }
}
