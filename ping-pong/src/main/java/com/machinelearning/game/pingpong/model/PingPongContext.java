package com.machinelearning.game.pingpong.model;

import com.machinelearning.game.machine.model.Context;
import com.machinelearning.game.machine.model.Record;
import com.machinelearning.game.machine.model.round.RoundContext;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class PingPongContext extends RoundContext {
    private int ballx;
    private int bally;
    private double dirx;
    private double diry;
    private int speed;
    private int racketX;
    private int racketLen;
    //0-unknown;1-suc;2-fail
    private int move;
    private int result;

    public int getBallx() {
        return ballx;
    }

    public void setBallx(int ballx) {
        this.ballx = ballx;
    }

    public int getBally() {
        return bally;
    }

    public void setBally(int bally) {
        this.bally = bally;
    }

    public double getDirx() {
        return dirx;
    }

    public void setDirx(double dirx) {
        this.dirx = dirx;
    }

    public double getDiry() {
        return diry;
    }

    public void setDiry(double diry) {
        this.diry = diry;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRacketX() {
        return racketX;
    }

    public void setRacketX(int racketX) {
        this.racketX = racketX;
    }

    public int getRacketLen() {
        return racketLen;
    }

    public void setRacketLen(int racketLen) {
        this.racketLen = racketLen;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public Record toRecord() {
        PingPongRecord rec = new PingPongRecord();
        rec.setX(ballx);
        rec.setY(bally);
        rec.setDx(dirx);
        rec.setDy(diry);
        rec.setRacketX(racketX);
        rec.setSpeed(speed);
        rec.setMove(move);
        rec.setRoundCount(roundCount);
        return rec;
    }

    @Override
    public void update(Record rec) {
        PingPongRecord r= (PingPongRecord) rec;
        setBallx(r.getX());
        setBally(r.getY());
        setDirx(r.getDx());
        setDiry(r.getDy());
        setMove(r.getMove());
        setRacketX(r.getRacketX());
        setSpeed(r.getSpeed());
    }
}
