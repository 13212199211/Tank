package tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 坦克图片资源加载类
 */
public class ResourceMgr {
    public static BufferedImage[] goodTankL = new BufferedImage[2];
    public static BufferedImage[] goodTankR = new BufferedImage[2];
    public static BufferedImage[] goodTankU = new BufferedImage[2];
    public static BufferedImage[] goodTankD = new BufferedImage[2];

    public static BufferedImage[] badTankL = new BufferedImage[2];
    public static BufferedImage[] badTankR = new BufferedImage[2];
    public static BufferedImage[] badTankU = new BufferedImage[2];
    public static BufferedImage[] badTankD = new BufferedImage[2];

    public static BufferedImage bulletL;
    public static BufferedImage bulletR;
    public static BufferedImage bulletU;
    public static BufferedImage bulletD;

    public static BufferedImage[] explode = new BufferedImage[16];

    static {
        try {
            goodTankU[0] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL[0] = ImageUtil.rotateImage(goodTankU[0], -90);
            goodTankR[0] = ImageUtil.rotateImage(goodTankU[0], 90);
            goodTankD[0] = ImageUtil.rotateImage(goodTankU[0], 180);

            goodTankU[1] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
            goodTankL[1] = ImageUtil.rotateImage(goodTankU[1], -90);
            goodTankR[1] = ImageUtil.rotateImage(goodTankU[1], 90);
            goodTankD[1] = ImageUtil.rotateImage(goodTankU[1], 180);

            badTankU[0] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL[0] = ImageUtil.rotateImage(badTankU[0], -90);
            badTankR[0] = ImageUtil.rotateImage(badTankU[0], 90);
            badTankD[0] = ImageUtil.rotateImage(badTankU[0], 180);

            badTankU[1] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
            badTankL[1] = ImageUtil.rotateImage(badTankU[1], -90);
            badTankR[1] = ImageUtil.rotateImage(badTankU[1], 90);
            badTankD[1] = ImageUtil.rotateImage(badTankU[1], 180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            for (int i = 1; i < 17; i++) {
                explode[i - 1] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + i + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
