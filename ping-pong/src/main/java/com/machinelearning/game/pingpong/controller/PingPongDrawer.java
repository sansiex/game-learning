package com.machinelearning.game.pingpong.controller;

import com.machinelearning.game.machine.controller.Drawer;
import com.machinelearning.game.machine.model.Context;
import com.machinelearning.game.pingpong.model.PingPongContext;

import java.awt.*;
import java.io.IOException;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class PingPongDrawer extends Drawer {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final int BALL_RADIUS = 5;
    public static final int RACKET_LENGTH = 50;
    public static final int RAKET_HEIGHT = 5;
    public static final int RACKET_Y = 550;

    private static final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);

    public PingPongDrawer(PingPongCore core){
        super(core);
    }

    public void draw(Graphics graphics, Context context) {
        Graphics2D g = (Graphics2D) graphics;
        PingPongContext ctx = (PingPongContext) context;
        g.setColor(Color.blue);
        g.fillRect(ctx.getRacketX(), RACKET_Y, RACKET_LENGTH, RAKET_HEIGHT);
        g.fillOval(ctx.getBallx() - BALL_RADIUS, ctx.getBally() - BALL_RADIUS, BALL_RADIUS * 2, BALL_RADIUS * 2);

        g.setFont(font);
        if (ctx.getResult() == PingPongController.RESULT_FAIL) {
            g.drawString("FAIL", 100, 100);
        } else if (ctx.getResult() == PingPongController.RESULT_SUCCEED) {
            g.drawString("SUCCESS", 100, 100);
        }

        printDebug(g);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
