package com.machinelearning.game.pingpong.player;

import com.machinelearning.game.machine.action.PlayingAction;
import com.machinelearning.game.machine.controller.GameController;
import com.machinelearning.game.machine.player.round.RoundHumanPlayer;
import com.machinelearning.game.pingpong.controller.PingPongController;

import java.awt.event.KeyEvent;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public class PingPongHumanPlayer extends RoundHumanPlayer {

    public PingPongHumanPlayer(GameController controller) {
        super(controller);
        PingPongController c = (PingPongController) controller;
        addAction(KeyEvent.VK_LEFT, new PlayingAction() {
            @Override
            public void doAction() {
                c.moveLeft();
            }
        });
        addAction(KeyEvent.VK_RIGHT, new PlayingAction() {
            @Override
            public void doAction() {
                c.moveRight();
            }
        });
    }

}
