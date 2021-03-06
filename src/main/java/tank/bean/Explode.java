package tank.bean;

import tank.manager.ResourceMgr;

import java.awt.*;
import java.util.Iterator;

public class Explode extends GameObject {

    private int step = 0;

    public Explode(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void paint(Graphics g, Iterator iterator) {
        g.drawImage(ResourceMgr.explode[step++], xPos, yPos, null);
        if (step >= ResourceMgr.explode.length) {
            iterator.remove();
        }
    }
}
