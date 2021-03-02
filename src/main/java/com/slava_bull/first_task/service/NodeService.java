package com.slava_bull.first_task.service;

import com.slava_bull.first_task.dao.NodeDao;
import com.slava_bull.first_task.dao.TagDao;
import com.slava_bull.first_task.dao.impl.NodeDaoImpl;
import com.slava_bull.first_task.dao.impl.TagDaoImpl;
import com.slava_bull.first_task.model.Node;
import com.slava_bull.first_task.model.Tag;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class NodeService {

    private final NodeDao nodeDao = new NodeDaoImpl();
    private final TagDao tagDao = new TagDaoImpl();

    private final static Logger log = Logger.getLogger(NodeService.class.getName());

    public void insertNode(List<Node> nodeList) {
        nodeList.forEach(node -> {
            try {
                nodeDao.insertNode(node);
                for (Tag tag : node.getTags()) {
                    tagDao.insertTag(tag);
                }
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage());
            }
        });
    }

    public void insertPreparedNodes(List<Node> nodeList) {
        nodeList.forEach(node -> {
            try {
                nodeDao.insertPreparedNode(node);
                for (Tag tag : node.getTags()) {
                    tagDao.insertPreparedTag(tag);
                }
            } catch (SQLException e) {
                log.error(e.getLocalizedMessage());
            }
        });
    }

    public void batchInsertNodes(List<Node> nodeList) throws Exception {
        nodeDao.batchInsertNodes(nodeList);
        List<Tag> tags = nodeList.stream()
                .flatMap(node -> node.getTags().stream())
                .collect(Collectors.toList());
        tagDao.batchInsertTags(tags);
    }
}
