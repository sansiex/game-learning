package com.machinelearning.game.pingpong.player;

import com.machinelearning.game.machine.controller.GameController;
import com.machinelearning.game.machine.player.round.RoundPlayer;
import com.machinelearning.game.pingpong.controller.PingPongController;

import java.io.IOException;
import java.util.Random;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public class PingPongRandomPlayer extends RoundPlayer {

    protected Random rand;

    public PingPongRandomPlayer(GameController controller) {
        super(controller);
        rand = new Random(System.currentTimeMillis());
    }

    @Override
    protected void move() {
        PingPongController c = (PingPongController) controller;
        int v=rand.nextInt()%3;
        if (0==v) {
            c.moveLeft();
        } else if (1==v) {
            c.moveRight();
        } else {
            c.stay();
        }
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public String getName() {
        return "Random";
    }
}
