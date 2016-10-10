package com.machinelearning.game.machine.controller;

import org.slf4j.Logger;

import java.io.*;

/**
 * Created by zuhai.jiang on 2016/10/3.
 */
public class VideoReader {

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    private File curFile;
    private FileReader curFileReader;
    private BufferedReader curBufferedReader;

    public void play(File file) throws FileNotFoundException {
        curFile=file;
        curFileReader = new FileReader(file);
        curBufferedReader=new BufferedReader(curFileReader);
    }

    public String readLine() {
        try {
            return curBufferedReader.readLine();
        } catch (IOException e) {
            logger.error("Failed to read file", e);
            return  null;
        }
    }

    public void close() throws IOException {
        curBufferedReader.close();
        curFileReader.close();
    }
}
