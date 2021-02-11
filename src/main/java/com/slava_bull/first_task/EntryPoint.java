package com.slava_bull.first_task;

import org.apache.log4j.Logger;

public class EntryPoint {

    final static Logger log = Logger.getLogger(EntryPoint.class.getName());

    public static void main(String[] args) {
        log.debug("Log: hello world");
        System.out.println("Hello world");
    }
}
