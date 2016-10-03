package com.machinelearning.game.machine.controller;

import com.machinelearning.game.machine.action.Action;
import com.machinelearning.game.machine.exception.GameException;
import com.machinelearning.game.machine.model.Context;
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
public abstract class GameController implements KeyListener {

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    public static final int MODE_HUMAN=0;
    public static final int MODE_VIDEO=1;
    public static final int MODE_AI=2;

    public GameController(GameCore core){
        setCore(core);
        init();
        actionMap = new HashMap<>();
        addAction(KeyEvent.VK_S, new Action() {
            @Override
            public void doAction() {
                if (status == Status.NEW) {
                    logger.info("New-KeyPressed:" + KeyEvent.VK_S);
                    setPlayMode(MODE_HUMAN);
                    start();
                } else if (status == Status.OVER) {
                    logger.info("OVER-KeyPressed:"+KeyEvent.VK_S);
                    reset();
                    setPlayMode(MODE_HUMAN);
                    start();
                }
            }
        });
    }

    private GameCore core;
    protected Status status;
    protected int playMode;
    protected Context context;
    protected Map<Integer, Action> actionMap;

    abstract public long getDefaultMoveInterval();

    public void reset() {
        logger.info("reset");
        context = initContext();
        setStatus(Status.NEW);
    }

    public void start() {
        logger.info("start game--MODE:"+playMode);
        try {
            core.getStorage().createFileForCurrentGame();
        } catch (Exception e) {
            logger.error("Failed to create file", e);
        }
        setStatus(Status.RUNNING);
        startGame();
    }

    public void end() {
        logger.info("end");
        setStatus(Status.OVER);
        endGame();
    }

    abstract protected Context initContext();
    abstract public void startGame();
    abstract public void endGame();
    abstract public void pause();
    abstract public void playVideo(File file) throws FileNotFoundException;

    public abstract void fail();

    public void addAction(int kv, Action action){
        actionMap.put(kv, action);
    }

    public void init(){
        logger.info("init controller");
        playMode=MODE_HUMAN;
        status = Status.NEW;
        context = initContext();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Action action = actionMap.get(e.getKeyCode());
        if (action != null) {
            action.doAction();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
