package com.machinelearning.game.machine.gui;

import com.machinelearning.game.machine.controller.Drawer;
import com.machinelearning.game.machine.controller.GameCore;
import com.machinelearning.game.machine.controller.Storage;
import com.machinelearning.game.machine.player.AbstractPlayer;
import org.slf4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by zuhai.jiang on 2016/9/26.
 */
public class Screen extends JFrame {

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    private GameCore core;
    private GamePanel gamePanel;
    private JMenuBar menuBar;
//    private JToolBar toolBar;
    private JFileChooser fileChooser;


    public Screen(GameCore core){
        super();
        this.core=core;
        initComponents(core);
        Drawer drawer=core.getDrawer();

        BorderLayout bord = new BorderLayout();
        this.setSize(drawer.getWidth() + 20, drawer.getHeight() + 150);
        this.getContentPane().setLayout(bord);
        this.add(BorderLayout.CENTER, gamePanel);
        this.add(BorderLayout.NORTH, menuBar);
//        this.add(BorderLayout.NORTH, toolBar);
        this.setTitle(core.getTitle());
    }

    private void initComponents(GameCore core){
        gamePanel=new GamePanel(core);
        menuBar = new JMenuBar();
        JMenu menu=new JMenu("文件");
        JMenuItem item=new JMenuItem("播放录像");
        item.addActionListener(new PlayVideoListener());
        menu.add(item);

        JMenu aiMenu = new JMenu("AI游戏");
        Map<String, AbstractPlayer> playerMap = core.getAiPlayerMap();
        for (Map.Entry<String, AbstractPlayer> ent:playerMap.entrySet()) {
            JMenuItem playItem=new JMenuItem(ent.getKey());
            playItem.addActionListener(new PlayAIListener(ent.getKey()));
            aiMenu.add(playItem);
        }
        menuBar.add(menu);
        menuBar.add(aiMenu);
//        toolBar = new JToolBar();
//        JButton button=new JButton("播放录像");
//        button.addActionListener(new PlayVideoListener());
//        toolBar.add(button);

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setDialogTitle("选择要播放的文件");
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith("."+ Storage.FILE_EXT);
            }

            @Override
            public String getDescription() {
                return ".txt|录像文件";
            }
        });
        fileChooser.setCurrentDirectory(core.getStorage().getDir());
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    class PlayVideoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser.showOpenDialog(core.getScreen());
            File file=fileChooser.getSelectedFile();
            try {
                logger.info("Play video:"+file.getName());
                core.getController().playVideo(file);
            } catch (FileNotFoundException e1) {
                logger.error("Failed to play video:"+file.getAbsolutePath(), e);
            }
        }
    }

    class PlayAIListener implements ActionListener {

        private String name;

        public PlayAIListener(String name){
            this.name = name;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            logger.info("Play AI:"+name);
            core.getController().playAI(name);
        }
    }
}
