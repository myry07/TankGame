package TankGame.main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

import static TankGame.util.Constant.*;

/**
 * @author myry
 * @date 2024-11-27 15:11
 * JFrame 前端窗口
 */


public class TankeGame extends JFrame {

    MyPanel mp = null;//初始化 在构造器中new
    Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        TankeGame f = new TankeGame();
    }

    public TankeGame() throws IOException {
        System.out.println("请输入 1:新游戏 2:继续上局");
        String key = s.next();

        mp = new MyPanel(key);
        //将mp放入Thread 并启动
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);//添加面板 游戏的绘图区域
        //窗口是否可见
        this.setVisible(true);
        //窗口大小
        this.setSize(FRAM_WIDTH, FRAM_HEIGHT);
        //窗口标题
        this.setTitle(FRAM_TITLE);
        //窗口初始化位置
        this.setLocation(FRAM_X, FRAM_Y);

        this.addKeyListener(mp);//增加监听事件 JFrame监听mp
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在JFrame中 增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("监听到窗口关闭");
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
