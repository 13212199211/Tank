package tank.chain;

import tank.bean.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
