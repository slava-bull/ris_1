package com.slava_bull.first_task.tasks;

import com.slava_bull.first_task.archive.Decompressor;
import com.slava_bull.first_task.stax.OsmParser;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OsmAnalytic_stax {
    private final static Logger log = Logger.getLogger(OsmAnalytic_stax.class.getName());

    private final String fromFile;
    private final String osmFile;

    public OsmAnalytic_stax() {
        this("RU-NVS.osm.bz2");
    }

    public OsmAnalytic_stax(String fromFile) {
        this(fromFile, "osm.xml");
    }

    public OsmAnalytic_stax(String fromFile, String osmFile) {
        this.fromFile = fromFile;
        this.osmFile = osmFile;
    }

    public void decompress() throws IOException {
        log.info("Start decompressing...");
        Decompressor decompressor = new Decompressor();
        decompressor.decompressFromTo(fromFile, osmFile);
        log.info("Successfully decompressed.");
    }

    public void printUserNodeCount() throws FileNotFoundException, XMLStreamException {
        log.info("Count users nodes...");
        OsmParser parser = new OsmParser();
        parser.printUserNodeCount(osmFile);
        log.info("Successfully counted users nodes.");
    }

    public void printUniqTags() throws FileNotFoundException, XMLStreamException {
        log.info("Count uniq tags...");
        OsmParser parser = new OsmParser();
        parser.printUniqTags(osmFile);
        log.info("Successfully counted uniq tags.");
    }
}
