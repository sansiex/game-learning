package com.machinelearning.game.machine.controller;

import com.machinelearning.game.machine.model.Context;

import java.awt.*;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public abstract class Drawer {

    public Drawer(GameCore core) {
        this.core= core;
    }

    private GameCore core;

    public abstract void draw(Graphics graphics, Context context);

    public abstract int getWidth();

    public abstract int getHeight();

    public GameCore getCore() {
        return core;
    }

    public void setCore(GameCore core) {
        this.core = core;
    }
}
