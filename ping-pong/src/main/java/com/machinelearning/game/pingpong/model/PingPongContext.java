package com.machinelearning.game.pingpong.model;

import com.machinelearning.game.machine.model.Context;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class PingPongContext extends Context {
    private int ballx;
    private int bally;
    private double dirx;
    private double diry;
    private int speed;
    private int racketX;
    private int racketLen;
    //0-unknown;1-suc;2-fail
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}