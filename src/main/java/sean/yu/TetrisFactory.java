package sean.yu;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static sean.yu.Tetris.SIDE_LEN;

/**
 * 生成下落方块的工厂
 * @author Sean Yu
 */
public class TetrisFactory {

    private static final Random RANDOM = new Random();

    /**
     * 生成一个形状随机的方块
     * 其中随机的逻辑：从7个形状种随机选取一个
     *
     * @return
     */
    public static List<Rectangle> generateRandomTetris(){
        Tetris.Shape shape = Tetris.Shape.values()[RANDOM.nextInt(Tetris.Shape.values().length)];

        switch (shape) {
            case I:
                return createShapeI();
            case O:
                return createShapeO();
            case Z:
                return createShapeZ();
            case S:
                return createShapeS();
            case T:
                return createShapeT();
            case L:
                return createShapeL();
            case J:
                return createShapeJ();
        }
        return null;
    }

    private static List<Rectangle> createShapeI() {
        List<Rectangle> res = new LinkedList<Rectangle>();
        Rectangle r1 = new Rectangle(0, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r2 = new Rectangle(SIDE_LEN, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r3 = new Rectangle(SIDE_LEN * 2, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r4 = new Rectangle(SIDE_LEN * 3, 0, SIDE_LEN, SIDE_LEN);
        res.addAll(Arrays.asList(r1, r2, r3, r4));
        return res;
    }

    private static List<Rectangle> createShapeO() {
        List<Rectangle> res = new LinkedList<Rectangle>();
        Rectangle r1 = new Rectangle(0, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r2 = new Rectangle(SIDE_LEN, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r3 = new Rectangle(0, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        Rectangle r4 = new Rectangle(SIDE_LEN, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        res.addAll(Arrays.asList(r1, r2, r3, r4));
        return res;
    }

    private static List<Rectangle> createShapeJ() {
        List<Rectangle> res = new LinkedList<Rectangle>();
        Rectangle r1 = new Rectangle(0, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r2 = new Rectangle(0, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        Rectangle r3 = new Rectangle(SIDE_LEN, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        Rectangle r4 = new Rectangle(SIDE_LEN * 2, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        res.addAll(Arrays.asList(r1, r2, r3, r4));
        return res;
    }

    private static List<Rectangle> createShapeL() {
        List<Rectangle> res = new LinkedList<Rectangle>();
        Rectangle r1 = new Rectangle(SIDE_LEN * 2, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r2 = new Rectangle(0, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        Rectangle r3 = new Rectangle(SIDE_LEN, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        Rectangle r4 = new Rectangle(SIDE_LEN * 2, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        res.addAll(Arrays.asList(r1, r2, r3, r4));
        return res;
    }

    private static List<Rectangle> createShapeZ() {
        List<Rectangle> res = new LinkedList<Rectangle>();
        Rectangle r1 = new Rectangle(0, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r2 = new Rectangle(SIDE_LEN, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r3 = new Rectangle(SIDE_LEN, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        Rectangle r4 = new Rectangle(SIDE_LEN * 2, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        res.addAll(Arrays.asList(r1, r2, r3, r4));
        return res;
    }

    private static List<Rectangle> createShapeS() {
        List<Rectangle> res = new LinkedList<Rectangle>();
        Rectangle r1 = new Rectangle(SIDE_LEN, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r2 = new Rectangle(SIDE_LEN * 2, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r3 = new Rectangle(SIDE_LEN, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        Rectangle r4 = new Rectangle(0, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        res.addAll(Arrays.asList(r1, r2, r3, r4));
        return res;
    }

    private static List<Rectangle> createShapeT() {
        List<Rectangle> res = new LinkedList<Rectangle>();
        Rectangle r1 = new Rectangle(0, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r2 = new Rectangle(SIDE_LEN, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r3 = new Rectangle(SIDE_LEN * 2, 0, SIDE_LEN, SIDE_LEN);
        Rectangle r4 = new Rectangle(SIDE_LEN, SIDE_LEN, SIDE_LEN, SIDE_LEN);
        res.addAll(Arrays.asList(r1, r2, r3, r4));
        return res;
    }
}

