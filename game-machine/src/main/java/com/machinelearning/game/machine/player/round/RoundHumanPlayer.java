package com.machinelearning.game.machine.player.round;

import com.machinelearning.game.machine.action.Action;
import com.machinelearning.game.machine.action.PlayingAction;
import com.machinelearning.game.machine.controller.GameController;
import com.machinelearning.game.machine.controller.Status;
import com.machinelearning.game.machine.controller.round.RoundGameController;
import com.machinelearning.game.machine.player.HumanPlayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public abstract class RoundHumanPlayer extends HumanPlayer {

    public RoundHumanPlayer(GameController controller) {
        super(controller);
        actionMap = new HashMap<>();
        addAction(KeyEvent.VK_S, new Action() {
            @Override
            public void doAction() {
                Status status=controller.getStatus();
                if (status == Status.NEW) {
                    logger.info("New-KeyPressed:" + KeyEvent.VK_S);
                    controller.setPlayMode(GameController.MODE_HUMAN);
                    controller.start();
                } else if (status == Status.OVER) {
                    logger.info("OVER-KeyPressed:"+KeyEvent.VK_S);
                    controller.reset();
                    controller.setPlayMode(GameController.MODE_HUMAN);
                    controller.start();
                }
            }
        });
    }

    @Override
    public void addAction(int kv, Action action){
        actionMap.put(kv, action);
    }

    @Override
    public Action getAction(int key) {
        return actionMap.get(key);
    }

    @Override
    public void doPlay() {

    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Action action = actionMap.get(e.getKeyCode());
        if (action == null) {
            return;
        } else if (action instanceof PlayingAction) {
            RoundGameController c = (RoundGameController) controller;
            boolean roundOpen = c.isRoundOpen();
            if (!roundOpen || GameController.MODE_HUMAN != controller.getPlayMode()) {
                return;
            }
            action.doAction();
            c.setRoundOpen(false);
            if (c.getCurrentInterval() == 0) {
                c.startNewRound();
            }
        } else {
            action.doAction();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
