package com.machinelearning.game.machine.controller.round;

import com.machinelearning.game.machine.action.Action;
import com.machinelearning.game.machine.action.PlayingAction;
import com.machinelearning.game.machine.controller.GameController;
import com.machinelearning.game.machine.controller.GameCore;
import com.machinelearning.game.machine.controller.Status;
import com.machinelearning.game.machine.controller.VideoPlayer;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zuhai.jiang on 2016/10/2.
 */
public abstract class RoundGameController extends GameController {

    protected transient boolean roundOpen = true;
    protected long currentInterval;
    protected Timer timer;

    public RoundGameController(GameCore core) {
        super(core);
    }

    abstract protected void moveByVideo(String moveLine);
    abstract protected void initByVideo(VideoPlayer player);
    abstract protected void moveByAI();

    @Override
    public void keyPressed(KeyEvent e) {
        Action action = actionMap.get(e.getKeyCode());
        if (action == null) {
            return;
        } else if (action instanceof PlayingAction) {
            if (!roundOpen || MODE_HUMAN != playMode) {
                return;
            }
            action.doAction();
            roundOpen = false;
            if (currentInterval == 0) {
                startNewRound();
            }
        } else {
            action.doAction();
        }
    }

    @Override
    public void startGame() {
        logger.info("start playing game");
        currentInterval = getDefaultMoveInterval();
        if (currentInterval > 0) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (status == Status.RUNNING) {
                        startNewRound();
                    }
                }
            }, 0, currentInterval);
        } else {
            startNewRound();
        }
    }

    @Override
    public void endGame() {
        logger.info("end playing game");
        timer.cancel();
        int res = timer.purge();
        logger.info("removed {} timer tasks", res);
        timer = null;
        setRoundOpen(false);
    }

    private void startNewRound() {
        //logger.info("start new round");
        beforeRoundStart();
        getCore().repaint();
        setRoundOpen(true);
        if (MODE_VIDEO == playMode) {
            String moveLine= null;
            moveLine = getCore().getVideoPlayer().readLine();
            if (moveLine == null) {
                end();
            } else {
                moveByVideo(moveLine);
            }
        } else if (MODE_AI == playMode ) {
            moveByAI();
        }
    }

    @Override
    public void playVideo(File file) throws FileNotFoundException {
        VideoPlayer player = getCore().getVideoPlayer();
        player.play(file);
        setPlayMode(MODE_VIDEO);
        context = initContext();
        initByVideo(player);
        start();
    }

    public boolean isRoundOpen() {
        return roundOpen;
    }

    public void setRoundOpen(boolean roundOpen) {
        this.roundOpen = roundOpen;
    }

    abstract public void beforeRoundStart();
}
