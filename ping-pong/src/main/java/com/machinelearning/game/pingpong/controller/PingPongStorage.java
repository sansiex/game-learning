package com.machinelearning.game.pingpong.controller;

import com.machinelearning.game.machine.controller.Storage;
import com.machinelearning.game.machine.exception.GameException;
import com.machinelearning.game.machine.model.Record;
import com.machinelearning.game.pingpong.model.PingPongRecord;

/**
 * Created by zuhai.jiang on 2016/10/3.
 */
public class PingPongStorage extends Storage<PingPongRecord> {

    public PingPongStorage(PingPongCore core, String dirPath) throws GameException {
        super(core, dirPath);
    }

    @Override
    public String serialize(PingPongRecord r) {
        StringBuilder sb=new StringBuilder();
        sb.append(r.getX()).append("\t");
        sb.append(r.getY()).append("\t");
        sb.append(r.getSpeed()).append("\t");
        sb.append(r.getDx()).append("\t");
        sb.append(r.getDy()).append("\t");
        sb.append(r.getRacketX()).append("\t");
        sb.append(r.getMove()).append("\t");
        sb.append(r.getLabel());
        return sb.toString();
    }

    @Override
    public PingPongRecord deserialize(String line) {
        String[] arr = line.split("\t");
        PingPongRecord rec=new PingPongRecord();
        rec.setX(Integer.parseInt(arr[0]));
        rec.setY(Integer.parseInt(arr[1]));
        rec.setSpeed(Integer.parseInt(arr[2]));
        rec.setDx(Double.parseDouble(arr[3]));
        rec.setDy(Double.parseDouble(arr[4]));
        rec.setRacketX(Integer.parseInt(arr[5]));
        rec.setMove(Integer.parseInt(arr[6]));
        rec.setLabel(Integer.parseInt(arr[7]));
        return rec;
    }
}
