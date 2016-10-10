package com.machinelearning.game.machine.player;

import com.machinelearning.game.machine.action.Action;
import com.machinelearning.game.machine.controller.GameController;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuhai.jiang on 2016/10/4.
 */
public abstract class HumanPlayer extends AbstractPlayer implements KeyListener {

    protected Map<Integer, Action> actionMap;

    public HumanPlayer(GameController controller){
        super(controller);
        actionMap = new HashMap<>();
    }

    @Override
    public String getName() {
        return "Human";
    }

    abstract public void addAction(int key, Action action);
    abstract public Action getAction(int key);
}
