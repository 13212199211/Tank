package tank.fire;

import tank.Bullet;
import tank.Dir;
import tank.Tank;

public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            Bullet bullet = new Bullet(tank.getXPos() + (Tank.WIDTH - Bullet.WIDTH) / 2,
                    tank.getYPos() + (Tank.HEIGHT - Bullet.HEIGHT) / 2,
                    dir, tank.getGroup(), tank.getGameModel());
        }
    }
}
