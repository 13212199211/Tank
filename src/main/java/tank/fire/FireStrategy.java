package tank.fire;

import tank.bean.Tank;

/**
 * 开火策略模式
 */
public interface FireStrategy {
    void fire(Tank tank);
}
