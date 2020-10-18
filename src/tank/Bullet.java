package tank;

import java.awt.*;

public class Bullet {
    // 定义子弹的长宽
    private static int width = 30;
    private static int height = 30;

    // 定义子弹的位置
    private int xPos;
    private int yPos;

    // 定义子弹的方向
    private Dir dir;

    // 定义子弹的速度
    private static final int SPEED = 5;

    public Bullet(int xPos, int yPos, Dir dir) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
    }

    public void print(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillOval(xPos, yPos, width, height);
        g.setColor(color);
        move();
    }

    public void move() {
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

}
