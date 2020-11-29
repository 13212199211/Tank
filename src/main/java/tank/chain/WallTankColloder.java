package tank.chain;

import tank.bean.GameObject;
import tank.bean.Tank;
import tank.bean.Wall;

public class WallTankColloder implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Wall && o2 instanceof Tank) {
            Wall wall = (Wall) o1;
            Tank tank = (Tank) o2;
            if (wall.getRectangle().intersects(tank.getRectangle())) {
                tank.collide();
            }
        } else if (o1 instanceof Tank && o2 instanceof Wall) {
            collide(o2, o1);
        }
        return true;
    }
}
