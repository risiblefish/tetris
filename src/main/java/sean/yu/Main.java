package sean.yu;

/**
 * @author Sean Yu
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameFrame gf = new GameFrame();
        while(true) {
            gf.repaint();
            Thread.sleep(500);
        }
    }
}
