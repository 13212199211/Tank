package tank.fire;

import tank.enums.Dir;
import tank.bean.Bullet;
import tank.bean.Tank;

public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            Bullet bullet = new Bullet(tank.getXPos() + (Tank.WIDTH - Bullet.WIDTH) / 2,
                    tank.getYPos() + (Tank.HEIGHT - Bullet.HEIGHT) / 2,
                    dir, tank.getGroup());
        }
    }
}
