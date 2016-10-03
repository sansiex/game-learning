package com.machinelearning.game.pingpong.controller;

import com.machinelearning.game.machine.controller.Drawer;
import com.machinelearning.game.machine.controller.GameController;
import com.machinelearning.game.machine.controller.round.RoundGameCore;
import com.machinelearning.game.machine.model.Context;
import com.machinelearning.game.pingpong.model.PingPongContext;
import com.machinelearning.game.pingpong.util.MoveUtil;

import java.awt.event.KeyListener;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class PingPongCore extends RoundGameCore {

    private static PingPongCore instance;

    public PingPongCore(){
        //init drawer
        drawer=new PingPongDrawer(this);

    }

    private PingPongDrawer drawer;



    @Override
    public String getTitle() {
        return "乒乓球";
    }

    @Override
    public Drawer initDrawer() {
        return new PingPongDrawer(this);
    }

    @Override
    public GameController initController() {
        return new PingPongController(this);
    }
}
