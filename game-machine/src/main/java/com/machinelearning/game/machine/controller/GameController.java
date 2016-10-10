package com.machinelearning.game.machine.controller;

import com.machinelearning.game.machine.action.Action;
import com.machinelearning.game.machine.exception.GameException;
import com.machinelearning.game.machine.model.Context;
import com.machinelearning.game.machine.player.AbstractPlayer;
import org.slf4j.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public abstract class GameController {

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    public static final int MODE_HUMAN=0;
    public static final int MODE_VIDEO=1;
    public static final int MODE_AI=2;

    public GameController(GameCore core){
        setCore(core);
        init();
    }

    protected GameCore core;
    protected Status status;
    protected int playMode;
    protected Context context;

    abstract public long getDefaultMoveInterval();

    public void reset() {
        logger.info("reset");
        context = initContext();
        setStatus(Status.NEW);
    }

    public void start() {
        logger.info("start game--MODE:"+playMode);
        try {
            AbstractPlayer player = core.getCurrentPlayer();
            String name = player.getName();
            core.getStorage().createFileForCurrentGame(name);
        } catch (Exception e) {
            logger.error("Failed to create file", e);
        }
        setStatus(Status.RUNNING);
        if (MODE_HUMAN == playMode) {
            startHumanGame();
        } else {
            startAutoGame();
        }
    }

    public void end() {
        logger.info("end");
        setStatus(Status.OVER);
        try {
            core.getCurrentPlayer().close();
        } catch (IOException e) {
            logger.error("Failed to end game", e);
            System.exit(1);
        }
        endGame();
    }

    abstract protected Context initContext();
    abstract public void startHumanGame();
    abstract public void startAutoGame();
    abstract public void endGame();
    abstract public void pause();
    abstract public void playVideo(File file) throws FileNotFoundException;
    abstract public void playAI(String name);

    public abstract void fail();

    public void init(){
        logger.info("init controller");
        playMode=MODE_HUMAN;
        status = Status.NEW;
        context = initContext();
    }




    public GameCore getCore() {
        return core;
    }

    public void setCore(GameCore core) {
        this.core = core;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getPlayMode() {
        return playMode;
    }

    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }
}
