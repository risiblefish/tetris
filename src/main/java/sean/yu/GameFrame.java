package sean.yu;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 19:18
 **/
public class GameFrame extends Frame {

    //游戏界面大小
    static final int GAME_WIDTH = 200;
    static final int GAME_HEIGHT = 400;

    //当前下落的方块
    private Tetris tetris = new Tetris(this);

    //底部静止的方块
    public final List<Rectangle> bottomRectList = new ArrayList();

    //当前得分
    public int score = 0;

    //是否按下了方向键
    boolean bL = false;
    boolean bR = false;
    boolean bD = false;

    public GameFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        addKeyListener(new TankKeyListener());
        setTitle("tetris");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //getter and setter
    public void setTetris(Tetris tetris) {
        this.tetris = tetris;
    }

    Image offScreenImage = null;

    /**
     * 设置双缓冲防止屏幕刷新导致的闪烁
     * <p>
     * 闪烁的原因是：paint中的g在进行复杂计算时，跟不上肉眼的节奏
     * update方法会在系统调用paint方法之前被系统调用
     * 这里大致实现方式是： 先在update中画出与游戏窗口大小相同的图，用黑色填充，然后用update的g来paint坦克和子弹
     * 最后用drawImage将缓存中的图一次性画出
     * 即Paint是边画边填充，update是一次性画到缓存里，然后再从缓存中一次性画出来
     *
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            this.offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //绘制游戏实时画面
    @Override
    public void paint(Graphics g) {
        //绘制得分
        //todo

        //绘制当前下落方块
        tetris.paint(g);

        //绘制底部方块
        for (int i = 0; i < bottomRectList.size(); i++) {
            Color og = g.getColor();
            g.setColor(Color.RED);
            Rectangle curr = bottomRectList.get(i);
            int x = curr.x;
            int y = curr.y;
            int w = curr.width;
            int h = curr.height;
            g.fillRect(x, y, w, h);
            g.setColor(og);
        }
    }

    /**
     * 处理对键盘的监听
     */
    class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    repaint();
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    repaint();
                    bD = true;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
        }
    }
}
