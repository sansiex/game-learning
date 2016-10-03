package com.machinelearning.game.pingpong.controller;

import com.machinelearning.game.machine.controller.Drawer;
import com.machinelearning.game.machine.controller.GameController;
import com.machinelearning.game.machine.controller.GameCore;
import com.machinelearning.game.machine.controller.Storage;
import com.machinelearning.game.machine.exception.GameException;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class PingPongCore extends GameCore {

    private static PingPongCore instance;

    public PingPongCore() throws GameException {
        super();
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

    @Override
    public Storage initStorage() throws GameException {
        String dirPath="data";
        try {
            return new PingPongStorage(this, dirPath);
        } catch (GameException e) {
            logger.error("Failed to create directory:"+dirPath, e);
            throw new GameException("Failed to init storage", e);
        }
    }
}
