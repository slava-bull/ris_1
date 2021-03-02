package com.slava_bull.first_task.dao;

import com.slava_bull.first_task.model.Node;

import java.sql.SQLException;
import java.util.List;

public interface NodeDao {
    Node getById(long nodeId) throws SQLException;

    void insertNode(Node node) throws SQLException;

    void insertPreparedNode(Node node) throws SQLException;

    void batchInsertNodes(List<Node> nodes) throws SQLException;
}
