package test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class test02 {
    @Test
    public void test01() {
        try {
            BufferedImage read = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(""));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
