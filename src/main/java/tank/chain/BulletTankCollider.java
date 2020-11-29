package tank.chain;

import tank.bean.Bullet;
import tank.bean.Explode;
import tank.bean.GameObject;
import tank.bean.Tank;
import tank.frame.GameModel;

import java.awt.*;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            collideWith(bullet, tank);
            return false;
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            Tank tank = (Tank) o1;
            Bullet bullet = (Bullet) o2;
            collideWith(bullet, tank);
            return false;
        }
        return true;
    }

    public void collideWith(Bullet bullet, Tank tank) {
        if (bullet.getGroup() == tank.getGroup()) {
            return;
        }
        Rectangle bulletRec = bullet.getRectangle();
        Rectangle tankRec = tank.getRectangle();

        if (bulletRec.intersects(tankRec)) {
            tank.die();
            bullet.die();
            GameModel.getInstance().add(new Explode(tank.getXPos(), tank.getYPos()));
        }
    }
}
