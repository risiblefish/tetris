import java.awt.*;
import java.util.List;

/**
 * @author Sean Yu
 */
public class Tetris {

    //初始中心坐标
    private Color color;
    private static final int SPEED = 100;
    private GFrame frame;
    private boolean alive = true;
    private static final Color DEFAULT_COLOR = Color.YELLOW;

    private List<Rectangle> tList;

    public Tetris(GFrame frame) {
        this(DEFAULT_COLOR, frame);
    }

    public Tetris(Color color, GFrame frame) {
        this.tList = TetrisListFactory.generateRandomTetris();
        this.color = color;
        this.frame = frame;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < this.tList.size(); i++) {
            Color og = g.getColor();
            g.setColor(Color.YELLOW);
            Rectangle curr = this.tList.get(i);
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
//        System.out.println("本次下落距离 ： " + dis);
        //如果本次下落距离为0，则当前方块变成底部方块，然后重新生成一个从顶部下落的方块
        if (dis == 0) {
            frame.bottomRectList.addAll(this.tList);
            frame.setTetris(new Tetris(frame));
        }
        //否则，进行长度为dis的下落
        else {
            t.tList.forEach(currRect -> fall(currRect, dis));
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
        for (Rectangle currRect : t.tList) {
            for (Rectangle bottomRect : t.frame.bottomRectList) {
                int currDis = bottomRect.y - (currRect.y + TetrisListFactory.SIDE_LEN);
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
        for (Rectangle currRect : t.tList) {
            int currDis = GFrame.GAME_HEIGHT - (currRect.y + TetrisListFactory.SIDE_LEN);
            if (currDis < SPEED) {
                minDis = Math.min(minDis, currDis);
            }
        }
        return minDis;
    }


//
//    private void move() {
//        if (!moving) {
//            return;
//        }
//        switch (dir) {
//            case LEFT:
//                x -= SPEED;
//                break;
//            case RIGHT:
//                x += SPEED;
//                break;
//            case UP:
//                y -= SPEED;
//                break;
//            case DOWN:
//                y += SPEED;
//                break;
//            default:
//                break;
//        }
//        if (this.group == BAD) {
//            performRobotAction();
//        }
//    }
}
