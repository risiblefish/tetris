import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Sean Yu
 */
public class TetrisListFactory {

    public static final int SIDE_LEN = 50;
    private static final Random RANDOM = new Random();

    enum Shape {
        I, O, Z, S, T, L, J;
    }

    public static List<Rectangle> generateRandomTetris(){
        Shape shape = Shape.values()[RANDOM.nextInt(Shape.values().length)];

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

