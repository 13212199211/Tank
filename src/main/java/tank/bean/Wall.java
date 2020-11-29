package tank.bean;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Iterator;

@Getter
@Setter
public class Wall extends GameObject {
    
    private int width;
    private int height;
    private Rectangle rectangle;

    public Wall(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(xPos, yPos, width, height);
    }

    @Override
    public void paint(Graphics g, Iterator iterator) {
        Color color = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(xPos, yPos, width, height);
        g.setColor(color);
    }
}
