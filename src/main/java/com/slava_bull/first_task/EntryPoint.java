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
            //service.insertNode(nodeList);
            //service.insertPreparedNodes(nodeList);
            //service.batchInsertNodes(nodeList);//157169ms
            long endTime = System.currentTimeMillis();
            System.out.println("Total execution time: " + (endTime - startTime) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBInitializer.closeConnection();
        }
    }
}
