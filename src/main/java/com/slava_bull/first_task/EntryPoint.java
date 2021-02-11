package com.slava_bull.first_task;

import org.apache.log4j.Logger;

public class EntryPoint {

    private final static Logger log = Logger.getLogger(EntryPoint.class.getName());

    public static void main(String[] args) {
        try {
            OsmAnalytic analytic = new OsmAnalytic();
            analytic.decompress();
            analytic.printUserNodeCount();
            analytic.printUniqTags();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
