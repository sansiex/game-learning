package com.machinelearning.game.machine.player.round;

import com.machinelearning.game.machine.controller.round.RoundGameController;
import com.machinelearning.game.machine.player.AbstractPlayer;
import com.machinelearning.game.machine.controller.GameController;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public abstract class RoundPlayer extends AbstractPlayer {

    public RoundPlayer(GameController controller) {
        super(controller);
    }

    @Override
    public void doPlay() {
        move();
        RoundGameController rgc = (RoundGameController) controller;
        rgc.setRoundOpen(false);
    }

    abstract protected void move();
}
