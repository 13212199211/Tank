package tank;

import tank.frame.GameModel;
import tank.frame.TankFrame;
import tank.manager.PropertyMgr;

public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();


        while (true) {
            // 这里的repaint方法会自动调用paint方法
            tankFrame.repaint();
            // 延迟50ms
            try {
                Thread.sleep(Integer.parseInt(PropertyMgr.get("DELAY_TIME")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
