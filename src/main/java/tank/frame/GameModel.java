package tank.frame;

import lombok.Getter;
import lombok.Setter;
import tank.bean.GameObject;
import tank.bean.Tank;
import tank.bean.Wall;
import tank.chain.ColliderChain;
import tank.enums.Dir;
import tank.enums.Group;
import tank.manager.PropertyMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public class GameModel {
    private Tank myTank = new Tank(
            Integer.parseInt(PropertyMgr.get("MY_TANK_XPOS")),
            Integer.parseInt(PropertyMgr.get("MY_TANK_YPOS")),
            Dir.UP,
            Group.GOOD);

    private ColliderChain colliderChain = ColliderChain.getInstance();
    private List<GameObject> gameObjects = new ArrayList<>();
    private List<GameObject> addObjects = new ArrayList<>();

    private static class Instance {
        private static GameModel INSTANCE = new GameModel();
    }

    private GameModel() {
        // 初始化敌放坦克
        addTank(Integer.parseInt(PropertyMgr.get("initTankCount")));
        addWall();
    }

    public static GameModel getInstance() {
        return Instance.INSTANCE;
    }

    public void paint(Graphics g) {
        /*Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bulletList.size(), 10, 60);
        g.drawString("坦克的数量:" + enemyTank.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodeList.size(), 10, 100);
        g.setColor(c);*/
        myTank.paint(g, null);
        gameObjects.addAll(addObjects);
        addObjects.clear();
        Iterator<GameObject> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            iterator.next().paint(g, iterator);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                colliderChain.collide(gameObjects.get(i), gameObjects.get(j));
            }
        }
    }

    public void addTank(int counts) {
        for (int i = 0; i < counts; i++) {
            add(new Tank(i * (TankFrame.GAME_WIDTH) / counts, 200, Dir.DOWN, Group.BAD));
        }
    }

    public void addWall() {
        add(new Wall(300, 300, 50, 500));
        add(new Wall(500, 300, 500, 50));
        add(new Wall(800, 100, 400, 50));
        add(new Wall(500, 700, 50, 400));
    }

    public void add(GameObject gameObject) {
        addObjects.add(gameObject);
    }
}
