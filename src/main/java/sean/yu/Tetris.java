package sean.yu;

import java.awt.*;
import java.util.List;

import static sean.yu.GameFrame.GAME_HEIGHT;
import static sean.yu.GameFrame.GAME_WIDTH;

/**
 * 定义了一组下落的方块
 * <p>
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
    private static final int SPEED = 10;

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
        extraMove();
        fall();
    }

    /**
     * 对一组方块进行一次下落
     */
    private void fall() {
        //获得本次下落的距离
        int dis = this.distanceToBottom(this);
        //如果本次下落距离为0，则当前方块变成底部方块，然后重新生成一个从顶部下落的方块
        if (dis == 0) {
            frame.bottomRectList.addAll(this.rList);
            frame.setTetris(new Tetris(frame));
        }
        //否则，进行长度为dis的下落
        else {
            this.rList.forEach(currRect -> fall(currRect, dis));
        }
    }

    //对每个小方块进行一次下落
    private void fall(Rectangle r, int dis) {
        r.y += dis;
    }


    /**
     * 如果按了左右方向键，则进行额外的左右移动
     */
    private void extraMove() {
        //如果按了左方向键
        if (this.frame.bL) {
            //并且没按右方向键
            if (!this.frame.bR) {
                moveLeft(this);
            }
        }
        //如果没按左方向键，但按了右方向键
        else if (this.frame.bR) {
            moveRight(this);
        }
    }

    private void moveLeft(Tetris t) {
        int dis = distanceToLeftBound(t);
        t.rList.forEach(r -> r.x -= dis);
    }

    private void moveRight(Tetris t) {
        int dis = distanceToRightBound(t);
        t.rList.forEach(r -> r.x += dis);
    }


    private int distanceToLeftBound(Tetris t) {
        int minDis = SPEED;
        for (Rectangle currRect : t.rList) {
            //每个子方块到右边框的距离为：当前子方块的x
            if (currRect.x < SPEED) {
                minDis = Math.min(minDis, currRect.x);
            }
        }
        return minDis;
    }

    private int distanceToRightBound(Tetris t) {
        int minDis = SPEED;
        for (Rectangle currRect : t.rList) {
            //每个子方块到右边框的距离为：边框宽度 - (当前子方块的x + 子方块边长）
            int currDis = GAME_WIDTH - (currRect.x + SIDE_LEN);
            if (currDis < SPEED) {
                minDis = Math.min(minDis, currDis);
            }
        }
        return minDis;
    }

    /**
     * 判断本次下落的距离
     * 如果本次"预下落"没有接触底部或边框，则正常下落（即下落距离为SPEED）
     * 如果在"预下落"过程中有方块接触到了底部方块或者边框，则调整下落距离
     *
     * @param t
     * @return
     */
    private int distanceToBottom(Tetris t) {
        //如果按了向下键，则获取额外2倍下落速度
        int extraSpeed = this.frame.bD ? SPEED + SPEED : 0;
        //本次下落的速度
        int speed = SPEED + extraSpeed;
        //本次下落的距离
        int minDis = speed;
        //先判断和底部方块是是否有相交
        for (Rectangle currRect : t.rList) {
            for (Rectangle bottomRect : t.frame.bottomRectList) {
                //判断当前方块是否在底部方块的上方
                if (isHorizontalIntersect(currRect, bottomRect)) {
                    //如果是，再计算下落到底部方块的距离
                    int currDis = bottomRect.y - (currRect.y + SIDE_LEN);
                    //如果底部到当前方块的垂直距离小于正常fall一次的距离（即速度），则更新本次下落的距离
                    if (currDis < speed) {
                        minDis = Math.min(minDis, currDis);
                    }
                }
            }
        }

        if (minDis < speed) {
            return minDis;
        }

        //如果下落方块的每一个子方块都和底部所有方块没有相交，则再判断是否会触及底部边框
        for (Rectangle currRect : t.rList) {
            int currDis = GAME_HEIGHT - (currRect.y + SIDE_LEN);
            if (currDis < speed) {
                minDis = Math.min(minDis, currDis);
            }
        }
        return minDis;
    }

    /**
     * 判断2个正方形在y相同时，是否在水平部分有重合
     * <p>
     * 判断方法：
     * 将当前下落正方形的水平方向的一条边记为Lc : (xc1,xc2)
     * 将bottom正方形的水平方向的一条边记为Lb : (xb1,xb2)
     * 则Lc 和 Lb 不相交的条件是： Lc在Lb的左侧（xc2 <= xb1）或右侧 (xc1 >= xb2)
     * 则相交的条件为 ： 不相交条件取反
     *
     * @param curr
     * @param bottom
     * @return
     */
    private boolean isHorizontalIntersect(Rectangle curr, Rectangle bottom) {
        int xc1 = curr.x;
        int xc2 = curr.x + SIDE_LEN;
        int xb1 = bottom.x;
        int xb2 = bottom.x + SIDE_LEN;
        boolean isNotIntersact = (xc2 <= xb1) || (xc1 >= xb2);
        return !isNotIntersact;
    }
}
