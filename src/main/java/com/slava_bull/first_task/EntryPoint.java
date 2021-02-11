package com.slava_bull.first_task;

import org.apache.log4j.Logger;

import java.io.IOException;

public class EntryPoint {

    final static Logger log = Logger.getLogger(EntryPoint.class.getName());

    public static void main(String[] args) throws IOException {
        try {
            log.info("Start decompressing...");
            Decompressor decompressor = new Decompressor();
            decompressor.decompressFromTo("RU-NVS.osm.bz2", "osm.xml");
            log.info("Successfully decompressed.");
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw e;
        }
    }
}
