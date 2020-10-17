package tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    // 不要忘记初始化
    private int xPos = 200;
    private int yPos = 200;


    public TankFrame() {
        setSize(800, 600);
        setTitle("tank war");
        setVisible(true);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("hello");
        g.fillRect(xPos, yPos, 50, 50);
        // 在此处使用递加的操作每次改变x，y的值来使每次调用print方法的时候使坦克动起来
        xPos += 50;
        yPos += 50;
    }
}
