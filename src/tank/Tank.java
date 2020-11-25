package tank;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

public class Tank {
    // 不要忘记初始化
    private int xPos = 200;
    private int yPos = 200;

    // 坦克的长宽
    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();

    // 引入方向的概念
    private Dir dir = Dir.DOWN;

    // 定义坦克移动的速度，声明为final无法进行改变
    private static final int SPEED = 5;

    // 定义坦克是否移动，用于处理坦克静止的情况
    private boolean moving = true;

    // 坦克是否存活
    private boolean islive = true;

    // 组别，防止敌方坦克自己发射子弹把自己打死
    private Group group = Group.BAD;

    // 地图
    TankFrame tankFrame = null;

    public Tank(int xPos, int yPos, Dir dir, Group group, TankFrame tankFrame) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g, Iterator<Tank> iterator) {
        if (!islive) {
            // 这里移除掉的话，下次再画就不会画出来了，因此眼前呈现的也就是坦克消失了
            if (iterator != null) {
                iterator.remove();
            }
            //return;
        }
        switch (dir) {
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, xPos, yPos, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL, xPos, yPos, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, xPos, yPos, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, xPos, yPos, null);
                break;
        }
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
        if (new Random().nextInt(10) >= 8) {
            fire();
        }
    }

    public void fire() {
        Bullet bullet = new Bullet(xPos + (WIDTH - Bullet.WIDTH) / 2, yPos + (HEIGHT - Bullet.HEIGHT) / 2, dir, group, tankFrame);
        tankFrame.bulletList.add(bullet);
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void die() {
        islive = false;
    }
}
