package com.machinelearning.game.machine.gui;

import com.machinelearning.game.machine.controller.Drawer;
import com.machinelearning.game.machine.controller.GameCore;

import javax.swing.*;

/**
 * Created by zuhai.jiang on 2016/9/26.
 */
public class Screen extends JFrame {

    private GamePanel gamePanel;

    public Screen(GameCore core){
        super();
        initComponents(core);
        Drawer drawer=core.getDrawer();
        this.setSize(drawer.getWidth()+20, drawer.getHeight()+50);
        this.getContentPane().setLayout(null);
        this.add(gamePanel);
        this.setTitle(core.getTitle());
    }

    private void initComponents(GameCore core){
        gamePanel=new GamePanel(core);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
