package com.slava_bull.first_task.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Node {
    private long id;
    private double lat;
    private double lon;
    private String user;
    private long uid;
    private boolean visible;
    private long version;
    private long changeset;
    private LocalDateTime timestamp;
    private List<Tag> tags;
}
