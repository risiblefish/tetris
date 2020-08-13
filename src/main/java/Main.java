/**
 * @author Sean Yu
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        GFrame gf = new GFrame();
        while(true) {
            gf.repaint();
            Thread.sleep(500);
        }
    }
}
