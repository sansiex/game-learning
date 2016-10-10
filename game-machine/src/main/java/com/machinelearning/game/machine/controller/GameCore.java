package com.machinelearning.game.machine.controller;

import com.machinelearning.game.machine.exception.GameException;
import com.machinelearning.game.machine.gui.Screen;
import com.machinelearning.game.machine.model.Context;
import com.machinelearning.game.machine.player.AbstractPlayer;
import com.machinelearning.game.machine.player.HumanPlayer;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

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
    protected HumanPlayer humanPlayer;
    protected AbstractPlayer videoPlayer;
    protected Map<String, AbstractPlayer> aiPlayerMap;
    protected AbstractPlayer currentPlayer;

    abstract public String getTitle();
    abstract public Drawer initDrawer();
    abstract public GameController initController();
    abstract public Storage initStorage() throws GameException;
    abstract protected HumanPlayer initHumanPlayer();
    abstract protected AbstractPlayer initVideoPlayer();
    abstract protected void initAIPlayers();

    protected void init() throws GameException {
        logger.info("init core");
        controller = initController();
        drawer = initDrawer();
        storage = initStorage();

        aiPlayerMap = new HashMap<>();
        AbstractPlayer videoPlayer = initVideoPlayer();
        setVideoPlayer(videoPlayer);

        HumanPlayer humanPlayer = initHumanPlayer();
        setHumanPlayer(humanPlayer);

        initAIPlayers();
        screen = new Screen(this);
        screen.getGamePanel().addKeyListener(humanPlayer);
        screen.setVisible(true);
    }

    public AbstractPlayer getAIPlayer(String name) {
        return aiPlayerMap.get(name);
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

    public Context getContext() {
        return controller.getContext();
    }

    public HumanPlayer getHumanPlayer() {
        return humanPlayer;
    }

    public void setHumanPlayer(HumanPlayer humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    public AbstractPlayer getVideoPlayer() {
        return videoPlayer;
    }

    public void setVideoPlayer(AbstractPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }

    public Map<String, AbstractPlayer> getAiPlayerMap() {
        return aiPlayerMap;
    }

    public void setAiPlayerMap(Map<String, AbstractPlayer> aiPlayerMap) {
        this.aiPlayerMap = aiPlayerMap;
    }

    public AbstractPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(AbstractPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
