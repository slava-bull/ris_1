package com.slava_bull.first_task.dao;

import com.slava_bull.first_task.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagDao {
    List<Tag> getAllByNodeId(long nodeId) throws SQLException;

    void insertTag(Tag tag) throws SQLException;

    void insertPreparedTag(Tag tag) throws SQLException;

    void batchInsertTags(List<Tag> tags) throws SQLException;
}
