package com.machinelearning.game.pingpong.controller;

import com.machinelearning.game.machine.controller.Drawer;
import com.machinelearning.game.machine.controller.GameController;
import com.machinelearning.game.pingpong.model.Status;
import com.machinelearning.game.pingpong.util.MoveUtil;

import java.awt.event.KeyListener;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class PingPoingController implements GameController {

    private static PingPoingController instance;

    public static final int DEFAULT_SPEED=10;
    public static final int MOVE_INTERVAL = 100;

    private PingPoingController(){
        //init drawer
        drawer=new PingPongDrawer();
        //init game status
        status=new Status();
        status.setBallx(PingPongDrawer.WIDTH / 2);
        status.setBally(PingPongDrawer.RACKET_Y-PingPongDrawer.BALL_RADIUS);
        double[] direction= MoveUtil.getRandomDirection();
        status.setDirx(direction[0]);
        status.setDiry(direction[1]);
        status.setSpeed(DEFAULT_SPEED);
        status.setRacketLen(PingPongDrawer.RACKET_LENGTH);
        status.setRacketX(PingPongDrawer.WIDTH/2-PingPongDrawer.RACKET_LENGTH/2);
    }

    public PingPoingController getInstance(){
        if (instance==null) {
            instance=new PingPoingController();
        }
        return instance;
    }

    private PingPongDrawer drawer;

    private Status status;

    @Override
    public String getTitle() {
        return "乒乓球";
    }

    @Override
    public Drawer getDrawer() {
        return drawer;
    }

    @Override
    public KeyListener getKeyListener() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public void pause() {

    }
}
