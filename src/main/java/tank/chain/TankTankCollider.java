package tank.chain;

import tank.bean.GameObject;
import tank.bean.Tank;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRectangle().intersects(t2.getRectangle())) {
                t1.collide();
                t2.collide();
            }
        }
        return true;
    }
}
