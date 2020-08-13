package sean.yu;

import java.awt.*;
import java.util.List;

/**
 * 定义了一组下落的方块
 *
 * 每组方块可以看成由多个小正方形方块（tList）无缝拼接而成
 *
 * @author Sean Yu
 */
public class Tetris {

    /*   ------------------ 常量 -------------------------- */
    //每个子方块的边长
    public static final int SIDE_LEN = 50;

    /**
     * 定义7种方块形状的枚举
     */
    enum Shape {
        I, O, Z, S, T, L, J;
    }

    //初始中心坐标
    private Color color;
    //方块下落速度
    private static final int SPEED = 100;

    private GameFrame frame;
    //方块默认颜色
    private static final Color DEFAULT_COLOR = Color.YELLOW;

    //组成方块的小方块
    private List<Rectangle> rList;

    public Tetris(GameFrame frame) {
        this(DEFAULT_COLOR, frame);
    }

    public Tetris(Color color, GameFrame frame) {
        this.rList = TetrisFactory.generateRandomTetris();
        this.color = color;
        this.frame = frame;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < this.rList.size(); i++) {
            Color og = g.getColor();
            g.setColor(Color.YELLOW);
            Rectangle curr = this.rList.get(i);
            int x = curr.x;
            int y = curr.y;
            int w = curr.width;
            int h = curr.height;
            g.fillRect(x, y, w, h);
            g.setColor(og);
        }
        fall(this);
    }

    private void fall(Tetris t) {
        //获得本次下落的距离
        int dis = t.distanceToBottom(t);
        //如果本次下落距离为0，则当前方块变成底部方块，然后重新生成一个从顶部下落的方块
        if (dis == 0) {
            frame.bottomRectList.addAll(this.rList);
            frame.setTetris(new Tetris(frame));
        }
        //否则，进行长度为dis的下落
        else {
            t.rList.forEach(currRect -> fall(currRect, dis));
        }
    }

    private void fall(Rectangle r, int dis) {
        r.y += dis;
    }

    /**
     * 判断本次下落的距离，
     * 如果本次"预下落"没有接触底部或边框，则正常下落（即下落距离为SPEED）
     * 如果在"预下落"过程中有方块接触到了底部方块或者边框，则调整下落距离
     *
     * @param t
     * @return
     */
    private int distanceToBottom(Tetris t) {
        //本次下落的距离
        int minDis = SPEED;
        //先判断和底部方块是是否有相交
        for (Rectangle currRect : t.rList) {
            for (Rectangle bottomRect : t.frame.bottomRectList) {
                int currDis = bottomRect.y - (currRect.y + SIDE_LEN);
                //如果底部到当前方块的垂直距离小于正常fall一次的距离（即速度），则更新本次下落的距离
                if (currDis < SPEED) {
                    minDis = Math.min(minDis, currDis);
                }
            }
        }

        if (minDis < SPEED) {
            return minDis;
        }

        //如果和底部方块没有相交，则再判断是否会触及底部边框
        for (Rectangle currRect : t.rList) {
            int currDis = GameFrame.GAME_HEIGHT - (currRect.y + SIDE_LEN);
            if (currDis < SPEED) {
                minDis = Math.min(minDis, currDis);
            }
        }
        return minDis;
    }
}
