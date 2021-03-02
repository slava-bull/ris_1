package com.slava_bull.first_task.service;

import com.slava_bull.first_task.dao.NodeDao;
import com.slava_bull.first_task.dao.impl.NodeDaoImpl;
import com.slava_bull.first_task.model.Node;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class NodeService {

    private final NodeDao nodeDao = new NodeDaoImpl();

    private final static Logger log = Logger.getLogger(NodeService.class.getName());

    public void insertNode(List<Node> nodeList) {
        nodeList.forEach(node -> {
            try {
                nodeDao.insertNode(node);
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage());
            }
        });
    }

    public void insertPreparedNodes(List<Node> nodeList) {
        nodeList.forEach(node -> {
            try {
                nodeDao.insertPreparedNode(node);
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage());
            }
        });
    }

    public void batchInsertNodes(List<Node> nodeList) throws Exception {
        nodeDao.batchInsertNodes(nodeList);
    }
}
