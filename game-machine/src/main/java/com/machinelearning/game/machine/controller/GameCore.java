package com.machinelearning.game.machine.controller;

import com.machinelearning.game.machine.exception.GameException;
import com.machinelearning.game.machine.gui.Screen;
import com.machinelearning.game.machine.model.Context;
import org.slf4j.Logger;

import java.io.File;


/**
 * Created by zuhai.jiang on 2016/10/2.
 */
public abstract class GameCore {

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    public GameCore() throws GameException {
        init();
    }

    protected Drawer drawer;
    protected GameController controller;
    protected Screen screen;
    protected Storage storage;
    protected VideoPlayer videoPlayer;

    abstract public String getTitle();
    abstract public Drawer initDrawer();
    abstract public GameController initController();
    abstract public Storage initStorage() throws GameException;

    protected void init() throws GameException {
        logger.info("init core");
        controller = initController();
        drawer = initDrawer();
        storage = initStorage();
        videoPlayer = new VideoPlayer();
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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Context getContext(){
        return controller.getContext();
    }

    public VideoPlayer getVideoPlayer() {
        return videoPlayer;
    }

    public void setVideoPlayer(VideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }
}
