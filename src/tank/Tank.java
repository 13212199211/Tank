package tank;

import java.awt.*;

public class Tank {
    // 不要忘记初始化
    private int xPos = 200;
    private int yPos = 200;
    // 引入方向的概念
    private Dir dir = Dir.DOWN;
    // 定义坦克移动的速度，声明为final无法进行改变
    private static final int speed = 4;
    // 定义坦克是否移动，用于处理坦克静止的情况
    private boolean moving = false;

    public Tank(int xPos, int yPos, Dir dir) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
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
        // 坦克自己将自己在画布上画出来
        g.fillRect(xPos, yPos, 50, 50);
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
                yPos -= speed;
                break;
            case DOWN:
                yPos += speed;
                break;
            case LEFT:
                xPos -= speed;
                break;
            case RIGHT:
                xPos += speed;
                break;
        }
    }
}
