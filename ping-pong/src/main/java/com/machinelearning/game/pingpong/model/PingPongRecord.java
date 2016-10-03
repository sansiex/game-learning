package com.machinelearning.game.pingpong.model;

import com.machinelearning.game.machine.model.Record;

/**
 * Created by zuhai.jiang on 2016/10/3.
 */
public class PingPongRecord extends Record {
    private int x;
    private int y;
    private int speed;
    private double dx;
    private double dy;
    private int racketX;
    private int move;
    private int label;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getRacketX() {
        return racketX;
    }

    public void setRacketX(int racketX) {
        this.racketX = racketX;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
