package com.aaa.test;

import javax.swing.*;

public class SwingLoginExample {
    public static void main(String[] args) {
        //创建JFrame实例
        JFrame frame = new JFrame("Login Example");
        //Setting the width and height of frame
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建面板，这个类似于HTML的div标签
        //我们可以创建多个面板并在JFrame中的指定位置
        //面板中我们可以添加文本字段，按钮及其他组件

        JPanel panel = new JPanel();
        //添加模板
        frame.add(panel);
        //调用用户定义的方法并添加组件到面板
        placeComponents(panel);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        //设置布局为null
        panel.setLayout(null);
        //创建JLabel
        JLabel userLabel = new JLabel("User:");
        //这个方法定义了组件位置
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        //创建文本域用于用户输入
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        //输入密码的文本域
        JLabel passwordLabel = new JLabel("password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordTest = new JPasswordField(20);
        passwordTest.setBounds(100, 50, 165, 25);
        panel.add(passwordTest);

        //创建登陆按钮
        JButton login = new JButton("login");
        login.setBounds(10, 80, 80, 25);
        panel.add(login);
    }
}
