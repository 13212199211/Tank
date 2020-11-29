package tank.fire;

import tank.Bullet;
import tank.Tank;

public class SingleFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        Bullet bullet = new Bullet(tank.getXPos() + (Tank.WIDTH - Bullet.WIDTH) / 2,
                tank.getYPos() + (Tank.HEIGHT - Bullet.HEIGHT) / 2,
                tank.getDir(), tank.getGroup(), tank.getGameModel());
    }
}
