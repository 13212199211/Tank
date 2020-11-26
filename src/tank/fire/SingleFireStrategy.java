package tank.fire;

import tank.Bullet;
import tank.Tank;

public class SingleFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        Bullet bullet = new Bullet(tank.getxPos() + (Tank.WIDTH - Bullet.WIDTH) / 2,
                tank.getyPos() + (Tank.HEIGHT - Bullet.HEIGHT) / 2,
                tank.getDir(), tank.getGroup(), tank.getTankFrame());
    }
}
