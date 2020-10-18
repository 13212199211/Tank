package tank;

import java.awt.*;

public class Tank {
    // 不要忘记初始化
    private int xPos = 200;
    private int yPos = 200;

    // 坦克的长宽
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    // 引入方向的概念
    private Dir dir = Dir.DOWN;

    // 定义坦克移动的速度，声明为final无法进行改变
    private static final int SPEED = 5;

    // 定义坦克是否移动，用于处理坦克静止的情况
    private boolean moving = false;

    // 地图
    TankFrame tankFrame = null;

    public Tank(int xPos, int yPos, Dir dir, TankFrame tankFrame) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.green);
        // 坦克自己将自己在画布上画出来
        g.fillRect(xPos, yPos, WIDTH, HEIGHT);
        g.setColor(color);
        move();
    }

    public void move() {
        // 如果没有按任何键，则原地不动
        if (!moving) {
            return;
        }
        // 在此处使用递加的操作每次改变x，y的值来使每次调用print方法的时候使坦克动起来
        // 这里使用方向来判断一直走的路线
        switch (dir) {
            case UP:
                yPos -= SPEED;
                break;
            case DOWN:
                yPos += SPEED;
                break;
            case LEFT:
                xPos -= SPEED;
                break;
            case RIGHT:
                xPos += SPEED;
                break;
        }
    }

    public void fire() {
        Bullet bullet = new Bullet(xPos + WIDTH / 2, yPos + HEIGHT / 2, dir);
        tankFrame.setMyBullet(bullet);
    }
}
