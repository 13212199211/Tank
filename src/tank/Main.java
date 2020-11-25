package tank;

public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        // 初始化敌放坦克
        initEnemyTank(tankFrame, 5);

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

    private static void initEnemyTank(TankFrame tankFrame, int counts) {
        for (int i = 0; i < counts; i++) {
            tankFrame.enemyTank.add(new Tank(200 + i * 150, 200, Dir.DOWN, Group.BAD, tankFrame));
        }
    }
}
