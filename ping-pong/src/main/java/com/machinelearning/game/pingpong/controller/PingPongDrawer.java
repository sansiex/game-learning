package com.machinelearning.game.pingpong.controller;

import com.machinelearning.game.machine.controller.Drawer;

import java.awt.*;
import java.io.IOException;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class PingPongDrawer implements Drawer {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final int BALL_RADIUS = 5;
    public static final int RACKET_LENGTH = 50;
    public static final int RAKET_HEIGHT = 5;
    public static final int RACKET_Y = 550;


    public void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;

    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
