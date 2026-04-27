package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_IMAGE = "cartoon_eid_chocolate_box_5.jpg";
    public static final String LEVEL_ATLAS = "";
    public static final String LEVEL_ONE_DATA = "";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            System.out.println("špatne img");
        } finally {
            try {
                is.close();
            } catch (IOException f) {
                System.out.println("nazaveno");

            }

        }
        return img;
    }
}
