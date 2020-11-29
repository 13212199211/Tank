package tank.bean;

import lombok.Getter;
import lombok.Setter;
import tank.enums.Dir;
import tank.enums.Group;
import tank.frame.GameModel;
import tank.frame.TankFrame;
import tank.manager.ResourceMgr;

import java.awt.*;
import java.util.Iterator;

@Getter
@Setter
public class Bullet extends GameObject {
    // 定义子弹的长宽
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    // 定义子弹的方向
    private Dir dir;

    // 定义子弹的速度
    private static final int SPEED = 10;

    private Group group = Group.BAD;
    // 子弹是否存活
    private Boolean islive = true;

    private Rectangle rectangle = null;

    public Bullet(int xPos, int yPos, Dir dir, Group group) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.group = group;
        this.rectangle = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g, Iterator iterator) {
        if (!islive) {
            iterator.remove();
            return;
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
        rectangle.setLocation(xPos, yPos);
        if (xPos < 0 || xPos >= TankFrame.GAME_WIDTH || yPos < 0 || yPos >= TankFrame.GAME_HEIGHT) {
            islive = false;
        }
    }

    public void die() {
        islive = false;
    }


}
