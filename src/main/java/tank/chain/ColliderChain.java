package tank.chain;

import tank.bean.GameObject;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider {
    private List<Collider> colliderList = new ArrayList<>();

    private static class ChainInstance {
        private static ColliderChain INSTANCE = new ColliderChain();
    }


    public static ColliderChain getInstance() {
        return ChainInstance.INSTANCE;
    }

    private ColliderChain() {
        colliderList.add(new BulletTankCollider());
        colliderList.add(new TankTankCollider());
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (Collider collider : colliderList) {
            boolean doNext = collider.collide(o1, o2);
            if (!doNext) {
                return false;
            }
        }
        return true;
    }
}
