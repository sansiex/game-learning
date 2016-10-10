package com.machinelearning.game.pingpong.player;

import com.machinelearning.game.machine.controller.GameController;
import com.machinelearning.game.machine.player.round.RoundVideoPlayer;
import com.machinelearning.game.pingpong.controller.PingPongController;
import com.machinelearning.game.pingpong.model.PingPongRecord;

import java.awt.event.KeyEvent;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public class PingPongVideoPlayer extends RoundVideoPlayer {
    public PingPongVideoPlayer(GameController controller) {
        super(controller);
    }

    @Override
    protected void moveByVideo(String line) {
        PingPongController c = (PingPongController) controller;
        PingPongRecord record = (PingPongRecord) c.getCore().getStorage().deserialize(line);
        if (KeyEvent.VK_LEFT == record.getMove()) {
            c.moveLeft();
        } else if (KeyEvent.VK_RIGHT == record.getMove()) {
            c.moveRight();
        }
    }
}
