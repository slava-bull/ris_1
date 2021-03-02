package com.slava_bull.first_task.dao.impl;

import com.slava_bull.first_task.dao.TagDao;
import com.slava_bull.first_task.db.DBInitializer;
import com.slava_bull.first_task.model.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImpl implements TagDao {

    private static final String SQL_GET_BY_ID = "select nodeid, key, value " +
                                                "from tags " +
                                                "where nodeid = ?";

    private static final String SQL_INSERT = "insert into tags(nodeid, key, value) " +
                                             "values (?, ?, ?)";

    private static void prepareStatement(PreparedStatement statement, Tag tag) throws SQLException {
        int i = 1;
        statement.setLong(i++, tag.getNodeId());
        statement.setString(i++, tag.getKey());
        statement.setString(i++, tag.getValue());
    }

    @Override
    public List<Tag> getAllByNodeId(long nodeId) throws SQLException {
        Connection connection = DBInitializer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID);
        statement.setLong(1, nodeId);
        ResultSet resultSet = statement.executeQuery(SQL_GET_BY_ID);
        List<Tag> tags = new ArrayList<>();
        while (resultSet.next()) {
            tags.add(Tag.fromRS(resultSet));
        }
        return tags;
    }

    @Override
    public void insertTag(Tag tag) throws SQLException {
        Connection connection = DBInitializer.getConnection();
        Statement statement = connection.createStatement();
        String sql = String.format("insert into tags(nodeid, key, value) values (%d, %s, %s)",
                tag.getNodeId(), tag.getKey(), tag.getValue());
        statement.execute(sql);
    }

    @Override
    public void insertPreparedTag(Tag tag) throws SQLException {
        Connection connection = DBInitializer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        prepareStatement(statement, tag);
        statement.execute();
    }

    @Override
    public void batchInsertTags(List<Tag> tags) throws SQLException {
        Connection connection = DBInitializer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        for (Tag tag : tags) {
            prepareStatement(statement, tag);
            statement.addBatch();
        }
        statement.executeBatch();
    }
}
