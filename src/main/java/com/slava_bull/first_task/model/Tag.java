package com.slava_bull.first_task.model;

import lombok.Data;

@Data
public class Tag {
    private Long nodeId;
    private String key;
    private String value;

    private Tag() {
    }

    public static Tag fromXml(com.slava_bull.Tag tagXml, Long nodeId) {
        Tag tag = new Tag();
        tag.nodeId = nodeId;
        tag.key = tagXml.getK();
        tag.value = tagXml.getV();
        return tag;
    }
}
