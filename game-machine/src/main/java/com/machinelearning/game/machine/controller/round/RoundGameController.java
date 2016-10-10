package com.machinelearning.game.machine.controller.round;

import com.machinelearning.game.machine.controller.*;
import com.machinelearning.game.machine.model.Record;
import com.machinelearning.game.machine.model.round.RoundContext;
import com.machinelearning.game.machine.player.AbstractPlayer;
import com.machinelearning.game.machine.player.round.RoundHumanPlayer;
import com.machinelearning.game.machine.player.round.RoundVideoPlayer;

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
    protected int roundCount;

    public RoundGameController(GameCore core) {
        super(core);
    }

    @Override
    public void start() {
        roundCount = 1;
        super.start();
    }

    @Override
    public void startHumanGame() {
        logger.info("start human playing game");
        currentInterval = getDefaultMoveInterval();
        RoundHumanPlayer player = (RoundHumanPlayer) core.getHumanPlayer();
        core.setCurrentPlayer(player);
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
    public void startAutoGame() {
        logger.info("start auto playing game");
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

    public void startNewRound() {
        RoundContext rc = (RoundContext) context;
        rc.setRoundCount(roundCount);
        beforeRoundStart();
        getCore().repaint();
        if (Status.OVER == status) {
            return;
        }
        logger.info("start new round:"+roundCount);
        setRoundOpen(true);
        AbstractPlayer player = core.getCurrentPlayer();
        player.doPlay();
        roundCount++;
    }

    @Override
    public void playVideo(File file) throws FileNotFoundException {
        RoundVideoPlayer player = (RoundVideoPlayer) getCore().getVideoPlayer();
        player.playFile(file);
        setPlayMode(MODE_VIDEO);
        context = initContext();
        String line = player.readLine();
        Record rec = core.getStorage().deserialize(line);
        core.getContext().update(rec);
        core.setCurrentPlayer(core.getVideoPlayer());
        start();
    }

    @Override
    public void playAI(String name) {
        AbstractPlayer ai = core.getAIPlayer(name);
        core.setCurrentPlayer(ai);
        setPlayMode(MODE_AI);
        context = initContext();
        start();
    }

    public boolean isRoundOpen() {
        return roundOpen;
    }

    public void setRoundOpen(boolean roundOpen) {
        this.roundOpen = roundOpen;
    }

    public long getCurrentInterval() {
        return currentInterval;
    }

    public void setCurrentInterval(long currentInterval) {
        this.currentInterval = currentInterval;
    }

    abstract public void beforeRoundStart();
}
