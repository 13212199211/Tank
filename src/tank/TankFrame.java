package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 1500;
    public static final int GAME_HEIGHT = 1000;

    private Tank myTank = new Tank(500, 800, Dir.UP, this);
    protected List<Tank> enemyTank = new ArrayList<Tank>();
    private List<Bullet> bulletList = new ArrayList<Bullet>();

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

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bulletList.size(), 10, 60);
        g.drawString("坦克的数量:" + enemyTank.size(), 10, 80);
        g.setColor(c);
        myTank.paint(g, null);
        Iterator<Tank> enemyTankIte = enemyTank.iterator();
        while (enemyTankIte.hasNext()) {
            enemyTankIte.next().paint(g, enemyTankIte);
        }
        Iterator<Bullet> iterator = bulletList.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(g, iterator);
        }
        for (Bullet bullet : bulletList) {
            for (Tank tank : enemyTank) {
                bullet.collideWith(tank);

            }
        }
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
