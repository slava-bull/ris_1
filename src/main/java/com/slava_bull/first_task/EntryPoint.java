package com.slava_bull.first_task;

import com.slava_bull.first_task.db.DBInitializer;
import com.slava_bull.first_task.model.Node;
import com.slava_bull.first_task.service.NodeService;
import com.slava_bull.first_task.tasks.OsmAnalyticJaxb;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntryPoint {

    private final static Logger log = Logger.getLogger(EntryPoint.class.getName());

    public static void main(String[] args) throws SQLException {
        try {
            DBInitializer.init();

            NodeService service = new NodeService();
            OsmAnalyticJaxb jaxb = new OsmAnalyticJaxb();
            List<Node> nodeList = new ArrayList<>();
            jaxb.nodeProcess(node -> nodeList.add(Node.fromXml(node)));

            long startTime = System.currentTimeMillis();
            //service.insertNode(nodeList); // 5 min 70К/5КК
            //service.insertPreparedNodes(nodeList); // 18 min 250К/5КК - устал ждать :)
            //service.batchInsertNodes(nodeList); // 129 079ms ~2min
            long endTime = System.currentTimeMillis();
            System.out.println("Total execution time: " + (endTime - startTime) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBInitializer.closeConnection();
        }
    }
}
