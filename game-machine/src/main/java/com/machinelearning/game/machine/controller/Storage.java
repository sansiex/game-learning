package com.machinelearning.game.machine.controller;

import com.google.common.io.Files;
import com.machinelearning.game.machine.exception.GameException;
import com.machinelearning.game.machine.model.Context;
import com.machinelearning.game.machine.model.Record;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zuhai.jiang on 2016/10/2.
 */
public abstract class Storage {
    public Storage(String dirPath) throws GameException {
        records=new LinkedList<>();
        dir = new File(dirPath);
        if (!dir.exists()) {
            boolean res = dir.mkdir();
            if (!res) {
                throw new GameException("Failed to create directory:"+dirPath);
            }
        }
    }

    protected File dir;
    protected File currentFile;

    protected List<Record> records;

    abstract String store(Context context);

    abstract void load(String rec, Context context);

    abstract String serialize(Record rec);

    abstract Record deserialize(String str);

    protected void createFile() throws IOException, GameException {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String fileName = date+".txt";
        String path = dir.getAbsolutePath()+File.pathSeparator+fileName;
        currentFile = new File(path);
        boolean res = currentFile.createNewFile();
        if (!res) {
            throw new GameException("Failed to create file:"+path);
        }
    }

    public void persist(){
        StringBuilder sb=new StringBuilder();
        for (Record rec:records) {
            sb.append(serialize(rec)).append("\n");
        }
        try {
            Files.append(sb.toString(), currentFile, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        records.clear();
    }
}
