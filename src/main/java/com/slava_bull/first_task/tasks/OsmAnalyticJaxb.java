package com.slava_bull.first_task.tasks;

import com.slava_bull.Node;
import com.slava_bull.first_task.jaxb.NodeJaxbParser;
import com.slava_bull.first_task.util.MapUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class OsmAnalyticJaxb {
    private final static Logger log = Logger.getLogger(OsmAnalyticJaxb.class.getName());

    private final String fromFile;
    private final String osmFile;

    public OsmAnalyticJaxb() {
        this("RU-NVS.osm.bz2");
    }

    public OsmAnalyticJaxb(String fromFile) {
        this(fromFile, "osm.xml");
    }

    public OsmAnalyticJaxb(String fromFile, String osmFile) {
        this.fromFile = fromFile;
        this.osmFile = osmFile;
    }

    public void printUserNodeCount() throws Exception {
        log.info("Count users nodes...");
        Map<String, Integer> usersNode = new HashMap<>();
        NodeJaxbParser parser = new NodeJaxbParser();
        parser.findNodes(osmFile, node ->
                usersNode.put(node.getUser(), usersNode.getOrDefault(node.getUser(), 0) + 1)
        );
        MapUtil.sortByValue(usersNode).forEach((key, value) -> System.out.printf("%s - %d%n", key, value));
        log.info("Successfully counted users nodes.");
    }

    public void nodeProcess(Consumer<Node> nodeFunction) throws Exception {
        NodeJaxbParser parser = new NodeJaxbParser();
        parser.findNodes(osmFile, nodeFunction);
    }
}
