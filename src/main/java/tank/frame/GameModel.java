package tank.frame;

import lombok.Getter;
import lombok.Setter;
import tank.bean.Bullet;
import tank.bean.Explode;
import tank.bean.Tank;
import tank.enums.Dir;
import tank.enums.Group;
import tank.manager.PropertyMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public class GameModel {
    private final Tank myTank = new Tank(
            Integer.parseInt(PropertyMgr.get("MY_TANK_XPOS")),
            Integer.parseInt(PropertyMgr.get("MY_TANK_YPOS")),
            Dir.UP,
            Group.GOOD,
            this);

    private List<Tank> enemyTank = new ArrayList<Tank>();
    private List<Bullet> bulletList = new ArrayList<Bullet>();
    private List<Explode> explodeList = new ArrayList<Explode>();

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        /*g.drawString("子弹的数量:" + bulletList.size(), 10, 60);
        g.drawString("坦克的数量:" + enemyTank.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodeList.size(), 10, 100);*/
        g.setColor(c);
        myTank.paint(g, null);
        Iterator<Tank> enemyTankIte = enemyTank.iterator();
        while (enemyTankIte.hasNext()) {
            enemyTankIte.next().paint(g, enemyTankIte);
        }
        Iterator<Bullet> iterator = bulletList.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(g, iterator);
        }
        for (Bullet bullet : bulletList) {
            for (Tank tank : enemyTank) {
                bullet.collideWith(tank);
            }
        }
        Iterator<Explode> explodeIter = explodeList.iterator();
        while (explodeIter.hasNext()) {
            explodeIter.next().paint(g, explodeIter);
        }
    }

    public void addTank(int counts) {
        for (int i = 0; i < counts; i++) {
            enemyTank.add(new Tank(200 + i * 150, 200, Dir.DOWN, Group.BAD, this));
        }
    }
}
