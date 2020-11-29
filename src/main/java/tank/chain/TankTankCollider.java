package tank.chain;

import tank.bean.GameObject;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        return false;
    }
}
