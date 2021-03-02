package com.slava_bull.first_task.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Node {
    private long id;
    private double lat;
    private double lon;
    private String user;
    private List<Tag> tags;

    public static Node fromRS(ResultSet rs) throws SQLException {
        Node node = new Node();
        node.id = rs.getLong("id");
        node.lat = rs.getDouble("lat");
        node.lon = rs.getDouble("lon");
        node.user = rs.getString("username");
        return node;
    }

    public static Node fromXml(com.slava_bull.Node nodeXml) {
        Node node = new Node();
        node.id = nodeXml.getId().longValue();
        node.lat = nodeXml.getLat();
        node.lon = nodeXml.getLon();
        node.user = nodeXml.getUser();
        node.tags = nodeXml.getTag().stream()
                .map(t -> Tag.fromXml(t, node.id))
                .collect(Collectors.toList());
        return node;
    }
}
