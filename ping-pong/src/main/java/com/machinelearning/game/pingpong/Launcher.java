package com.machinelearning.game.pingpong;

import com.machinelearning.game.machine.exception.GameException;
import com.machinelearning.game.pingpong.controller.PingPongCore;

/**
 * Created by zuhai.jiang on 2016/9/26.
 */
public class Launcher {

    public static void main(String[] args) throws GameException {
        new PingPongCore();
    }

}
