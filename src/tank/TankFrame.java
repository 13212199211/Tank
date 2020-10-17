package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    // 不要忘记初始化
    private int xPos = 200;
    private int yPos = 200;
    // 引入方向的概念
    Dir dir = Dir.DOWN;
    // 定义坦克移动的速度
    private static final int speed = 4;


    public TankFrame() {
        setSize(800, 600);
        setTitle("tank war");
        setVisible(true);
        setResizable(false);

        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(xPos, yPos, 50, 50);
        // 在此处使用递加的操作每次改变x，y的值来使每次调用print方法的时候使坦克动起来
        // 这里使用方向来判断一直走的路线
        switch (dir) {
            case UP:
                yPos -= speed;
                break;
            case DOWN:
                yPos += speed;
                break;
            case LEFT:
                xPos -= speed;
                break;
            case RIGHT:
                xPos += speed;
                break;
        }
    }

    class MyKeyListener extends KeyAdapter {
        // 定义四个将要走的方向
        boolean goUp = false;
        boolean goDown = false;
        boolean goLeft = false;
        boolean goRight = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    goUp = true;
                    break;
                case KeyEvent.VK_DOWN:
                    goDown = true;
                    break;
                case KeyEvent.VK_LEFT:
                    goLeft = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    goRight = true;
                    break;
                default:
                    break;
            }
            // 更新坦克行走的方向
            upTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    goUp = false;
                    break;
                case KeyEvent.VK_DOWN:
                    goDown = false;
                    break;
                case KeyEvent.VK_LEFT:
                    goLeft = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    goRight = false;
                    break;
                default:
                    break;
            }
            // 更新坦克行走的方向
            upTankDir();
        }

        private void upTankDir() {
            // 如果键盘按住了，则会将方向设置成对应的方向，如果释放了，则不对但钱方向进行修改
            if (goLeft) {
                dir = Dir.LEFT;
            }
            if (goRight) {
                dir = Dir.RIGHT;
            }
            if (goUp) {
                dir = Dir.UP;
            }
            if (goDown) {
                dir = Dir.DOWN;
            }
        }
    }
}
