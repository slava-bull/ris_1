package com.slava_bull.first_task.stax;

import com.slava_bull.first_task.util.MapUtil;

import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class OsmParser {

    public void printUserNodeCount(String xmlFilePath) throws XMLStreamException, FileNotFoundException {
        StaxStreamProcessor processor = new StaxStreamProcessor(new FileInputStream(xmlFilePath));
        Map<String, Integer> usersNode = new HashMap<>();
        while (processor.startElement("node", "osm")) {
            String user = processor.getAttribute("user");
            usersNode.put(user, usersNode.getOrDefault(user, 0) + 1);
        }
        MapUtil.sortByValue(usersNode).forEach((key, value) -> System.out.printf("%s - %d%n", key, value));
    }

    public void printUniqTags(String xmlFilePath) throws XMLStreamException, FileNotFoundException {
        StaxStreamProcessor processor = new StaxStreamProcessor(new FileInputStream(xmlFilePath));
        Map<String, Integer> usersNode = new HashMap<>();
        while (processor.startElement("node", "osm")) {
            while (processor.startElement("tag", "node")) {
                String key = processor.getAttribute("k");
                usersNode.put(key, usersNode.getOrDefault(key, 0) + 1);
            }
        }
        MapUtil.sortByValue(usersNode).forEach((key, value) -> System.out.printf("%s - %d%n", key, value));
    }
}
