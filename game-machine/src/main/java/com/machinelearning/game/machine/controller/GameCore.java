package com.machinelearning.game.machine.controller;

import com.machinelearning.game.machine.gui.Screen;
import com.machinelearning.game.machine.model.Context;
import org.slf4j.Logger;


/**
 * Created by zuhai.jiang on 2016/10/2.
 */
public abstract class GameCore {

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    public GameCore() {
        init();
    }

    protected Drawer drawer;
    protected GameController controller;
    protected Screen screen;

    abstract public String getTitle();

    abstract public Drawer initDrawer();

    abstract public GameController initController();

    protected void init() {
        logger.info("init core");
        controller = initController();
        drawer = initDrawer();
        screen = new Screen(this);
        screen.getGamePanel().addKeyListener(controller);
        screen.setVisible(true);
    }

    public void repaint() {
        screen.repaint();
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public Context getContext(){
        return controller.getContext();
    }
}
