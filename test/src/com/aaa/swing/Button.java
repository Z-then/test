package com.aaa.swing;

import javax.swing.*;
import java.awt.*;

public class Button {
    public static void main(String[] args) {

        JFrame jf = new JFrame("button");
        jf.setLayout(new FlowLayout(FlowLayout.LEFT));
        jf.setBounds(400, 400, 450, 200);
        //以下为单选按钮
        JRadioButton jrb1 = new JRadioButton("男");
        JRadioButton jrb2 = new JRadioButton("女");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        jf.add(jrb1);
        jf.add(jrb2);

        //以下为多选按钮
        JCheckBox jcb1 = new JCheckBox("睡觉", true);
        JCheckBox jcb2 = new JCheckBox("吃饭", false);
        JCheckBox jcb3 = new JCheckBox("看书", false);
        JCheckBox jcb4 = new JCheckBox("打游戏", false);
        jf.add(jcb1);
        jf.add(jcb2);
        jf.add(jcb3);
        jf.add(jcb4);

        //以下为下拉选项按钮
        JComboBox cd = new JComboBox();
        cd.addItem("--学历选择--");
        cd.addItem("--研究生--");
        cd.addItem("--本科--");
        cd.addItem("--高中--");
        cd.addItem("--初中--");
        cd.addItem("--小学--");
        jf.add(cd);

        //设置窗口可见
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
