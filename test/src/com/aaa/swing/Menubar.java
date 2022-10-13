package com.aaa.swing;

import javax.swing.*;
import java.awt.*;

public class Menubar {
    public static void main(String[] args) {
        JFrame jf = new JFrame("菜单栏");
        jf.setBounds(400, 300, 400, 600);
        jf.setLayout(new FlowLayout(FlowLayout.LEFT));

        //设置一个菜单栏
        JMenuBar mr = new JMenuBar();
        //菜单选项
        JMenu menu = new JMenu("File");
        JMenu menu1 = new JMenu("Edit");
        JMenu menu2 = new JMenu("View");
        //菜单下的选项
        JMenuItem item1 = new JMenuItem("Open");
        JMenuItem item2 = new JMenuItem("Setting");
        JMenuItem item3 = new JMenuItem("Cut");
        JMenuItem item4 = new JMenuItem("Copy");
        JMenuItem item5 = new JMenuItem("Tool Window");
        JMenuItem item6 = new JMenuItem("Show Siblings");
        mr.add(menu);
        mr.add(menu1);
        mr.add(menu2);
        menu.add(item1);
        menu.add(item2);
        menu1.add(item3);
        menu1.add(item4);
        menu2.add(item5);
        menu2.add(item6);
        jf.add(mr);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
