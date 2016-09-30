package com.machinelearning.game.machine.controller;

import java.awt.event.KeyListener;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public interface GameController {

    String getTitle();

    Drawer getDrawer();

    KeyListener getKeyListener();

    void start();

    void end();

    void pause();

}
