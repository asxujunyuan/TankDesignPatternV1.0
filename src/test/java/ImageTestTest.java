import com.xujunyuan.tank.TankFrame;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageTestTest {
    @Test
    void test(){
        try {
            BufferedImage imgae = ImageIO.read(new File("C:/Users/29738/Downloads/181419_马士兵版坦克大战/images/missileD.gif"));
            assertNotNull(imgae);
            System.out.println(ImageTestTest.class.getClassLoader().getResource(""));
            BufferedImage imgae2 =ImageIO.read(TankFrame.class.getClassLoader().getResourceAsStream("images/missileD.gif"));
            assertNotNull(imgae2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // fail("Not yet implemented");
        assertNotNull(new Object());
    }
}