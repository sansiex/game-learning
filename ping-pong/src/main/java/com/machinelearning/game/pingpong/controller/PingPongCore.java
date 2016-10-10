package com.machinelearning.game.pingpong.controller;

import com.machinelearning.game.machine.controller.*;
import com.machinelearning.game.machine.exception.GameException;
import com.machinelearning.game.machine.player.AbstractPlayer;
import com.machinelearning.game.machine.player.HumanPlayer;
import com.machinelearning.game.pingpong.player.PingPongHumanPlayer;
import com.machinelearning.game.pingpong.player.PingPongRandomPlayer;
import com.machinelearning.game.pingpong.player.PingPongVideoPlayer;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class PingPongCore extends GameCore {

    private static PingPongCore instance;

    public PingPongCore() throws GameException {
        super();
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

    @Override
    protected HumanPlayer initHumanPlayer() {
        return new PingPongHumanPlayer(controller);
    }

    @Override
    protected AbstractPlayer initVideoPlayer() {
        return new PingPongVideoPlayer(controller);
    }

    @Override
    protected void initAIPlayers() {
        aiPlayerMap.put("随机AI", new PingPongRandomPlayer(controller));
    }

}
