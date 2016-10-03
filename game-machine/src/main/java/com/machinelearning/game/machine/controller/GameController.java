package com.machinelearning.game.machine.controller;

import com.machinelearning.game.machine.action.Action;
import com.machinelearning.game.machine.model.Context;
import org.slf4j.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public abstract class GameController implements KeyListener {

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    public GameController(GameCore core){
        setCore(core);
        init();
        actionMap = new HashMap<>();
        addAction(KeyEvent.VK_S, new Action() {
            @Override
            public void doAction() {
                if (status == Status.NEW) {
                    logger.info("New-KeyPressed:" + KeyEvent.VK_S);
                    start();
                } else if (status == Status.OVER) {
                    logger.info("OVER-KeyPressed:"+KeyEvent.VK_S);
                    reset();
                    start();
                }
            }
        });
    }

    private GameCore core;
    protected Status status;
    protected Context context;
    protected Map<Integer, Action> actionMap;

    abstract public long getDefaultMoveInterval();

    public void reset() {
        logger.info("reset");
        context = initContext();
        setStatus(Status.NEW);
    }

    public void start() {
        logger.info("start game");
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

    public abstract void fail();

    public void addAction(int kv, Action action){
        actionMap.put(kv, action);
    }

    public void init(){
        logger.info("init controller");
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
}
