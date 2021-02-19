package com.slava_bull.first_task;

import com.slava_bull.first_task.tasks.OsmAnalytic_jaxb;
import org.apache.log4j.Logger;

public class EntryPoint {

    private final static Logger log = Logger.getLogger(EntryPoint.class.getName());

    public static void main(String[] args) {
        try {
            OsmAnalytic_jaxb analytic = new OsmAnalytic_jaxb();
            analytic.printUserNodeCount();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
