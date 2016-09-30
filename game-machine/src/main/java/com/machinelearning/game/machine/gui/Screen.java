package com.machinelearning.game.machine.gui;

import com.machinelearning.game.machine.controller.Game;

import javax.swing.*;

/**
 * Created by zuhai.jiang on 2016/9/26.
 */
public class Screen extends JFrame {

    private GamePanel gamePanel;

    public Screen(Game game){
        super();
        this.setSize(800, 600);
        this.getContentPane().setLayout(null);
        initComponents(game);
        this.add(gamePanel);
        this.setTitle("Ping-Pong");
    }

    private void initComponents(Game game){
        gamePanel=new GamePanel(game.getDrawer());
    }

    public static void main(String[] args) {
//        Screen screen = new Screen();
//        screen.setVisible(true);
    }
}
