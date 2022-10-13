package com.aaa.lianxi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class TetrisApp {
    JFrame frame;
    Tetris tetris;
    JMenuBar menuBar;
    JMenu gameMenu, helpMenu;
    JMenuItem newGameItem, pauseItem, continueItem, exitItem;
    JButton pauseButton, newGameButton;

    TetrisApp() {
        frame = new JFrame("Game");
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        tetris = new Tetris();
        tetris.setFocusable(true);
        frame.add(tetris, BorderLayout.CENTER);
        gameMenu = new JMenu("俄罗斯方块");
        newGameItem = new JMenuItem("新游戏");
        pauseItem = new JMenuItem("暂停");
        continueItem = new JMenuItem("继续");
        exitItem = new JMenuItem("退出");
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris.Init();
            }
        });
        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris.SetPause(true);
            }
        });
        continueItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris.SetPause(false);
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gameMenu.add(newGameItem);
        gameMenu.add(pauseItem);
        gameMenu.add(continueItem);
        gameMenu.add(exitItem);
        menuBar.add(gameMenu);

        JPanel panel = new JPanel();
        newGameButton = new JButton("新游戏");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris.Init();
                tetris.requestFocus();
            }
        });
        pauseButton = new JButton("暂停");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isPause = tetris.GetPause();
                if (isPause) {
                    //  System.out.println("开");
                    pauseButton.setText("暂停");
                    tetris.SetPause(false);
                } else {
                    //  System.out.println("停");
                    pauseButton.setText("开始");
                    tetris.SetPause(true);
                }
                tetris.requestFocus();
            }
        });
        JButton upButton, rightButton, leftButton;
        upButton = new JButton("旋转");
        leftButton = new JButton("<");
        rightButton = new JButton(">");
        upButton.addActionListener(e -> {
            tetris.UpAction();
            tetris.requestFocus();
        });
        leftButton.addActionListener(e -> {
            tetris.LeftAction();
            tetris.requestFocus();
        });
        rightButton.addActionListener(e -> {
            tetris.RightAction();
            tetris.requestFocus();
        });
        panel.add(upButton);
        panel.add(leftButton);
        panel.add(rightButton);
        panel.add(newGameButton);
        panel.add(pauseButton);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setSize(320, 370);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

public class Main {
    public static void main(String[] args) {
        new TetrisApp();
    }
}