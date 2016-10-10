package com.machinelearning.game.machine.player;

import com.machinelearning.game.machine.controller.GameController;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public abstract class AbstractPlayer {

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    protected GameController controller;

    public AbstractPlayer(GameController controller){
        this.controller = controller;
    }
    abstract public void doPlay();
    abstract public void close() throws IOException;
    abstract public String getName();
}
