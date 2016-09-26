package com.machinelearning.game.machine.gui;

import javax.swing.*;

/**
 * Created by zuhai.jiang on 2016/9/26.
 */
public class Screen extends JFrame {

    private JLabel label;
    private JButton button;

    public Screen(){
        super();
        this.setSize(800, 600);
        this.getContentPane().setLayout(null);
        initComponents();
        this.add(label);
        this.add(button);
        this.setTitle("Ping-Pong");
    }

    private void initComponents(){
        label=new JLabel();
        label.setText("label");
        label.setBounds(100, 100, 100, 50);

        button=new JButton();
        button.setBounds(100, 150, 100, 50);
        button.setText("button");
    }

    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.setVisible(true);
    }
}
