package tank;

import lombok.Getter;
import lombok.Setter;
import tank.fire.FireStrategy;
import tank.fire.FourDirFireStrategy;
import tank.fire.SingleFireStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Random;

@Getter
@Setter
public class Tank {
    // 不要忘记初始化
    private int xPos = 200;
    private int yPos = 200;

    // 坦克的长宽
    public static final int WIDTH = ResourceMgr.badTankD[0].getWidth();
    public static final int HEIGHT = ResourceMgr.badTankD[0].getHeight();

    // 切换图片间隔
    private static final int LIGHT_TIMES = 20;

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
    private GameModel gameModel = null;

    // 随机数
    private Random random = new Random();

    // 当前图片索引
    private int curTimes = 0;

    // 当前图片索引
    private int curPic = 0;

    // 发射策略
    private FireStrategy fireStrategy;

    public Tank(int xPos, int yPos, Dir dir, Group group, GameModel gameModel) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;
        if (group == Group.BAD) {
            fireStrategy = new SingleFireStrategy();
        } else {
            fireStrategy = new FourDirFireStrategy();
        }
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
                g.drawImage(group == Group.BAD ? loadTankPicture(ResourceMgr.badTankR) : loadTankPicture(ResourceMgr.goodTankR),
                        xPos, yPos, null);
                break;
            case LEFT:
                g.drawImage(group == Group.BAD ? loadTankPicture(ResourceMgr.badTankL) : loadTankPicture(ResourceMgr.goodTankL),
                        xPos, yPos, null);
                break;
            case UP:
                g.drawImage(group == Group.BAD ? loadTankPicture(ResourceMgr.badTankU) : loadTankPicture(ResourceMgr.goodTankU),
                        xPos, yPos, null);
                break;
            case DOWN:
                g.drawImage(group == Group.BAD ? loadTankPicture(ResourceMgr.badTankD) : loadTankPicture(ResourceMgr.goodTankD),
                        xPos, yPos, null);
                break;
        }
        move();
    }

    private BufferedImage loadTankPicture(BufferedImage[] tankPic) {
        if (curTimes / LIGHT_TIMES != 0) {
            curTimes = -1;
            // 切换图片
            curPic = 1 - curPic;
        }
        curTimes++;
        return tankPic[curPic];
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
        if (group == Group.BAD && random.nextInt(100) >= 98) {
            fire();
        }
        if (group == Group.BAD && random.nextInt(100) >= 95) {
            randomDir();
        }
        boundsCheck();
    }

    private void boundsCheck() {
        if (xPos < 0) {
            xPos = 0;
        } else if (yPos < Tank.HEIGHT / 2) {
            yPos = Tank.HEIGHT / 2;
        } else if (xPos > TankFrame.GAME_WIDTH - Tank.WIDTH) {
            xPos = TankFrame.GAME_WIDTH - Tank.WIDTH;
        } else if (yPos > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
            yPos = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }

    }


    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fireStrategy.fire(this);
    }

    public void die() {
        islive = false;
    }
}
