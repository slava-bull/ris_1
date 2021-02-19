package com.slava_bull.first_task.tasks;

import com.slava_bull.first_task.jaxb.NodeJaxbParser;
import com.slava_bull.first_task.util.MapUtil;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class OsmAnalytic_jaxb {
    private final static Logger log = Logger.getLogger(OsmAnalytic_jaxb.class.getName());

    private final String fromFile;
    private final String osmFile;

    public OsmAnalytic_jaxb() {
        this("RU-NVS.osm.bz2");
    }

    public OsmAnalytic_jaxb(String fromFile) {
        this(fromFile, "osm.xml");
    }

    public OsmAnalytic_jaxb(String fromFile, String osmFile) {
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
}
