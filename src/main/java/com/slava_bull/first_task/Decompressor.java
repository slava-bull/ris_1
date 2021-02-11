package com.slava_bull.first_task;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decompressor {

    public void decompressFromTo(String fromFilePath, String toFilePath) throws IOException {
        FileInputStream in = new FileInputStream(fromFilePath);
        FileOutputStream out = new FileOutputStream(toFilePath);
        BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
        int buffersize = 2048;
        final byte[] buffer = new byte[buffersize];
        int n = 0;
        while (-1 != (n = bzIn.read(buffer))) {
            out.write(buffer, 0, n);
        }
        out.close();
        bzIn.close();
    }
}
