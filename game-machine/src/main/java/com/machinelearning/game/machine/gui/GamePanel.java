package com.machinelearning.game.machine.gui;

import com.machinelearning.game.machine.controller.Drawer;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;

/**
 * Created by zuhai.jiang on 2016/9/30.
 */
public class GamePanel extends JPanel {

    public static final int FRAME_RATE=60;

    private boolean running=false;

    private Drawer drawer;

    public GamePanel(Drawer drawer){
        setDrawer(drawer);
        setSize(drawer.getWidth(), drawer.getHeight());
//        setBorder(new SoftBevelBorder(1, Color.BLACK, Color.BLACK));
        setBackground(Color.BLACK);
        setFocusable(true);
        startDrawing();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        drawer.draw(g0);
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

    public void start(){
        running=true;
    }

    public void stop(){
        running=false;
    }

    private void startDrawing(){
        long interval = 1000/FRAME_RATE;
        java.util.Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running) {
                    repaint();
                }
            }
        }, 1000, interval);
    }
}
