package tank;

public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        while (true) {

            // 这里的repaint方法会自动调用paint方法
            tankFrame.repaint();

            // 延迟50ms
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
