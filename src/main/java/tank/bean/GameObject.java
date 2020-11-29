package tank.bean;

import java.awt.*;
import java.util.Iterator;

public abstract class GameObject {
    protected int xPos;
    protected int yPos;

    public abstract void paint(Graphics g, Iterator iterator);

}
