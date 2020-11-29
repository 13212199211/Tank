package tank.bean;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Iterator;

@Getter
@Setter
public abstract class GameObject {
    protected int xPos;
    protected int yPos;

    public abstract void paint(Graphics g, Iterator iterator);

}
