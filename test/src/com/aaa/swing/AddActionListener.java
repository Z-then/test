package com.aaa.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddActionListener {
    public static void main(String[] args) {
        JFrame jf = new JFrame("事件");
        jf.setBounds(400, 300, 400, 400);
        jf.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextArea ta = new JTextArea(20, 20);
        ta.setLineWrap(true);

        JButton jb = new JButton();
        jb.setText("发送消息");
        jb.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.append("Hello呀");
            }
        });
        jf.add(ta);
        jf.add(jb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
