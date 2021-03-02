package com.slava_bull.first_task.model;

import lombok.Data;

import java.util.List;

@Data
public class Osm {
    private float version;
    private String generator;
    private List<Node> nodes;
}
