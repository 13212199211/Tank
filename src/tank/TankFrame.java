package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    private Tank myTank = new Tank(200, 200, Dir.DOWN, this);
    private Bullet myBullet = new Bullet(300, 300, Dir.DOWN);

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
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


        myTank.paint(g);
        myBullet.print(g);


    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public Bullet getMyBullet() {
        return myBullet;
    }

    public void setMyBullet(Bullet myBullet) {
        this.myBullet = myBullet;
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
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            // 更新坦克行走的方向
            upTankDir();
        }

        private void upTankDir() {

            if (!goDown && !goUp && !goRight && !goLeft) {
                myTank.setMoving(false);
            } else {
                // 记得将坦克移动设置为true，因为默认为false
                myTank.setMoving(true);
                // 如果键盘按住了，则会将方向设置成对应的方向，如果释放了，则不对当前方向进行修改
                if (goLeft) {
                    myTank.setDir(Dir.LEFT);
                }
                if (goRight) {
                    myTank.setDir(Dir.RIGHT);
                }
                if (goUp) {
                    myTank.setDir(Dir.UP);
                }
                if (goDown) {
                    myTank.setDir(Dir.DOWN);
                }
            }
        }
    }
}
