package com.aaa.lianxi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Tetris extends JPanel {
    //方块大小
    private static final int BlockSize = 10;
    //地图宽度
    private static final int BlockWidth = 16;
    private static final int BlockHeight = 26;
    //刷新时间
    private static final int TimeDelay = 1000;
    private static final String[] AuthorInfo = {
            "Author:", "TEST"
    };
    private boolean[][] BlockMap = new boolean[BlockHeight][BlockWidth];
    private int Score = 0;
    private boolean IsPause = false;
    static boolean[][][] Shape = Block.Shape;
    //记录下落方块位置
    private Point NowBlockPos;
    //pos坐标是以左上角为零点，y为纵轴，x为横轴
    private boolean[][] NowBlockMap, NextBlockMap;
    private int NowBlockState, NextBlockState;
    private Timer timer;

    public Tetris() {
        this.Init();
        timer = new Timer(Tetris.TimeDelay, this.TimerListener);
        timer.start();
        this.addKeyListener(this.KeyAdapter);
    }

    private void getNextBlock() {
        this.NowBlockState = this.NextBlockState;
        this.NowBlockMap = this.NextBlockMap;
        this.NextBlockState = this.CreateNewBlockState();
        this.NextBlockMap = this.getBlockMap(NextBlockState);
        this.NowBlockPos = this.CalNewBlockInitPos();
    }

    private boolean IsTouch(boolean[][] SrcNextBlockMap, Point SrcNextBlockPos) {
        for (int i = 0; i < SrcNextBlockMap.length; i++) {
            for (int j = 0; j < SrcNextBlockMap[i].length; j++) {
                if (SrcNextBlockMap[i][j]) {
                    if (SrcNextBlockPos.y + i >= BlockHeight || SrcNextBlockPos.x + j >= BlockWidth || SrcNextBlockPos.x + j < 0) {
                        return true;
                    } else {
                        if (SrcNextBlockPos.y + i < 0) continue;
                        else if (this.BlockMap[SrcNextBlockPos.y + i][SrcNextBlockPos.x + j])
                            return true;
                    }
                }
            }
        }
        return false;
    }

    //固定方块到地图
    private boolean FixBlock() {
        for (int i = 0; i < NowBlockMap.length; i++) {
            for (int j = 0; j < NowBlockMap[i].length; j++) {
                if (NowBlockMap[i][j]) {
                    //System.out.println(""+i+" "+j+"\n");
                    if (NowBlockPos.y + i < 0) {
                        return false;
                    } else {
                        BlockMap[NowBlockPos.y + i][NowBlockPos.x + j] = true;
                    }
                }
            }
        }
        return true;
    }

    private Point CalNewBlockInitPos() {
        return new Point(BlockWidth / 2 - NowBlockMap[0].length / 2, -NowBlockMap.length);
    }

    //初始化地图
    public void Init() {
        for (int i = 0; i < BlockMap.length; i++)
            for (int j = 0; j < BlockMap[i].length; j++)
                BlockMap[i][j] = false;
        Score = 0;

        NowBlockState = CreateNewBlockState();
        NowBlockMap = getBlockMap(NowBlockState);
        NextBlockState = CreateNewBlockState();
        NextBlockMap = getBlockMap(NextBlockState);
        NowBlockPos = CalNewBlockInitPos();
        repaint();
    }

    public void SetPause(boolean value) {
        IsPause = value;
        if (IsPause) {
            timer.stop();
        } else {
            timer.restart();
        }
        this.repaint();
    }

    boolean GetPause() {
        return this.IsPause;
    }

    private int CreateNewBlockState() {
        //一共有lenght种方块，每个方块通过旋转有四种状态
        int Sum = Shape.length * 4;
        return (int) (Math.random() * 1000) % Sum;
    }

    private boolean[][] getBlockMap(int BlockState) {
        return RotateBlock(Shape[BlockState / 4], BlockState % 4);
    }

    private boolean[][] RotateBlock(boolean[][] shape, int time) {
        time = (time % 4 + 4) % 4;
        if (time == 0) {
            return shape;
        }
        int height = shape[0].length;
        int width = shape.length;
        boolean[][] NextShape = new boolean[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                NextShape[i][j] = shape[width - 1 - j][i];
        return RotateBlock(NextShape, time - 1);
    }

    //图形部分
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //边界
        for (int i = 0; i < BlockHeight + 1; i++) {
            g.drawRect(0, i * BlockSize, BlockSize, BlockSize);
            g.drawRect((BlockWidth + 1) * BlockSize, i * BlockSize, BlockSize, BlockSize);
        }
        for (int i = 0; i < BlockWidth; i++) {
            g.drawRect((i + 1) * BlockSize, BlockHeight * BlockSize, BlockSize, BlockSize);
        }

        //方块
        for (int i = 0; i < NowBlockMap.length; i++) {
            for (int j = 0; j < NowBlockMap[i].length; j++) {
                if (NowBlockMap[i][j]) {
                    g.fillRect((1 + NowBlockPos.x + j) * BlockSize, (NowBlockPos.y + i) * BlockSize, BlockSize, BlockSize);
                    System.out.println("" + i + " " + j + "\n");
                }
            }
        }
        for (int i = 0; i < BlockHeight; i++)
            for (int j = 0; j < BlockWidth; j++)
                if (BlockMap[i][j])
                    g.fillRect((1 + j) * BlockSize, i * BlockSize, BlockSize, BlockSize);
        for (int i = 0; i < NextBlockMap.length; i++) {
            for (int j = 0; j < NextBlockMap[i].length; j++) {
                if (NextBlockMap[i][j])
                    //g.fillRect((NextBlockPos.x+j)*BlockSize,(NowBlockPos.y+i)*BlockSize,BlockSize,BlockSize);
                    g.fillRect(190 + j * Tetris.BlockSize, 30 + i * BlockSize, BlockSize, BlockSize);
            }
        }
        g.drawString("Score" + Score, 190, 10);
        for (int i = 0; i < AuthorInfo.length; i++) {
            g.drawString(AuthorInfo[i], 190, 100 + i * 20);
        }
        if (IsPause) {
            g.setColor(Color.WHITE);
            g.fillRect(70, 100, 50, 20);
            g.setColor(Color.BLACK);
            g.drawString("PAUSE", 75, 113);
        }
    }

    private int ClearLines() {
        int lines = 0;
        for (int i = 0; i < BlockMap.length; i++) {
            boolean IsLine = true;
            for (int j = 0; j < BlockMap[i].length; j++) {
                if (!BlockMap[i][j]) {
                    IsLine = false;
                    break;
                }
            }
            if (IsLine) {
                for (int k = i; k > 0; k--) {
                    BlockMap[k] = BlockMap[k - 1];
                }
                BlockMap[0] = new boolean[BlockWidth];
                lines++;
            }
        }
        return lines;
    }

    ActionListener TimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (IsTouch(NowBlockMap, new Point(NowBlockPos.x, NowBlockPos.y + 1))) {
                if (FixBlock()) {
                    Score += ClearLines();
                    getNextBlock();
                } else {
                    JOptionPane.showMessageDialog(Tetris.this.getParent(), "GAME OVER");
                    Init();
                }
            } else {
                NowBlockPos.y++;
            }
            repaint();
        }
    };

    //按键操作
    void UpAction() {
        boolean[][] DesBlockMap = RotateBlock(NowBlockMap, 1);
        if (!IsTouch(DesBlockMap, NowBlockPos)) {
            NowBlockMap = DesBlockMap;
        }
        repaint();
    }

    void LeftAction() {
        Point DesPoint;
        DesPoint = new Point(NowBlockPos.x - 1, NowBlockPos.y);
        if (!IsTouch(NowBlockMap, DesPoint)) {
            NowBlockPos = DesPoint;
        }
        repaint();
    }

    void RightAction() {
        Point DesPoint;
        DesPoint = new Point(NowBlockPos.x + 1, NowBlockPos.y);
        if (!IsTouch(NowBlockMap, DesPoint)) {
            NowBlockPos = DesPoint;
        }
        repaint();
    }

    java.awt.event.KeyAdapter KeyAdapter = new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("GREGEGERG");

            if (IsPause) {
                return;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    Point DesPoint;
                    DesPoint = new Point(NowBlockPos.x, NowBlockPos.y + 1);
                    while (!IsTouch(NowBlockMap, DesPoint)) {
                        NowBlockPos = DesPoint;
                        DesPoint = new Point(NowBlockPos.x, NowBlockPos.y + 1);
                    }
                    repaint();
                    break;
                case KeyEvent.VK_UP:
                    UpAction();
                    break;
                case KeyEvent.VK_LEFT:
                    LeftAction();
                    break;
                case KeyEvent.VK_RIGHT:
                    RightAction();
                    break;
            }
        }
    };
}