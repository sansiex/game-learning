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
public abstract class Storage<T extends Record> {

    public static final Charset charset = Charset.forName("UTF-8");
    public static final String FILE_EXT="txt";

    public Storage(GameCore core, String dirPath) throws GameException {
        this.core=core;
        records=new LinkedList<>();
        dir = new File(dirPath);
        if (!dir.exists()) {
            boolean res = dir.mkdir();
            if (!res) {
                throw new GameException("Failed to create directory:"+dirPath);
            }
        }
    }

    protected GameCore core;
    protected File dir;
    protected File currentFile;
    protected List<T> records;

    abstract public String serialize(T rec);
    abstract public T deserialize(String line);

    public void createFileForCurrentGame(String prefix) throws IOException, GameException {
        int mode = core.getController().getPlayMode();
        if (GameController.MODE_VIDEO == mode) {
            return;
        }
        String date = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String fileName = prefix+"-"+date+".txt";
        String path = dir.getAbsolutePath()+File.separator+fileName;
        currentFile = new File(path);
        boolean res = currentFile.createNewFile();
        if (!res) {
            throw new GameException("Failed to create file:"+path);
        }
    }

    public void appendRecord(Context ctx){
        int mode = core.getController().getPlayMode();
        if (GameController.MODE_VIDEO == mode) {
            return;
        }
        Record rec=ctx.toRecord();
        records.add((T) rec);
    }

    public void appendToFile(String content) throws IOException {
        int mode = core.getController().getPlayMode();
        if (GameController.MODE_VIDEO == mode) {
            return;
        }
        Files.append(content, currentFile, charset);
    }

    public void persist(){
        int mode = core.getController().getPlayMode();
        if (GameController.MODE_VIDEO == mode) {
            return;
        }
        StringBuilder sb=new StringBuilder();
        for (T rec:records) {
            sb.append(serialize(rec)).append("\n");
        }
        try {
            Files.append(sb.toString(), currentFile, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        records.clear();
    }

    public List<T> getRecords() {
        return records;
    }

    public File getDir() {
        return dir;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }
}
