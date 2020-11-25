package tank;

import java.awt.*;
import java.util.Iterator;

public class Bullet {
    // 定义子弹的长宽
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    // 定义子弹的位置
    private int xPos;
    private int yPos;

    // 定义子弹的方向
    private Dir dir;

    // 定义子弹的速度
    private static final int SPEED = 10;

    // 地图
    private TankFrame tankFrame = null;

    private Group group = Group.BAD;
    // 子弹是否存活
    private Boolean islive = true;

    public Bullet(int xPos, int yPos, Dir dir, Group group, TankFrame tankFrame) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void print(Graphics g, Iterator<Bullet> iterator) {
        if (!islive) {
            iterator.remove();
        }
        switch (dir) {
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, xPos, yPos, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, xPos, yPos, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, xPos, yPos, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, xPos, yPos, null);
                break;
        }
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
        if (xPos < 0 || xPos >= TankFrame.GAME_WIDTH || yPos < 0 || yPos >= TankFrame.GAME_HEIGHT) {
            islive = false;
        }
    }

    public void collideWith(Tank tank) {
        if (group == tank.getGroup()) {
            return;
        }
        Rectangle bulletRec = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
        Rectangle tankRec = new Rectangle(tank.getxPos(), tank.getyPos(), tank.WIDTH, tank.HEIGHT);

        if (bulletRec.intersects(tankRec)) {
            tank.die();
            this.die();
            tankFrame.explodeList.add(new Explode(tank.getxPos(), tank.getyPos()));
        }

    }

    private void die() {
        islive = false;
    }


}
