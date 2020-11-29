package tank.chain;

import tank.bean.Bullet;
import tank.bean.GameObject;
import tank.bean.Wall;

public class WallBulletCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Wall && o2 instanceof Bullet) {
            Wall wall = (Wall) o1;
            Bullet bullet = (Bullet) o2;
            if (wall.getRectangle().intersects(bullet.getRectangle())) {
                bullet.die();
            }
        } else if (o1 instanceof Bullet && o2 instanceof Wall) {
            collide(o2, o1);
        }
        return true;
    }
}
