package com.slava_bull.first_task.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Tag {
    private Long nodeId;
    private String key;
    private String value;

    private Tag() {
    }

    public static Tag fromRS(ResultSet rs) throws SQLException {
        Tag tag = new Tag();
        tag.nodeId = rs.getLong("nodeid");
        tag.key = rs.getString("key");
        tag.value = rs.getString("value");
        return tag;
    }

    public static Tag fromXml(com.slava_bull.Tag tagXml, Long nodeId) {
        Tag tag = new Tag();
        tag.nodeId = nodeId;
        tag.key = tagXml.getK();
        tag.value = tagXml.getV();
        return tag;
    }
}
