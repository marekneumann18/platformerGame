package utilz;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_IMAGE = "cartoon_eid_chocolate_box_5.jpg";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String BUTTON_ATLAS = "button_atlas.png";

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
//    public static int[][] GetLevelData() {
//        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
//        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
//        for (int j = 0; j < img.getHeight(); j++) {
//            for (int i = 0; i < img.getWidth(); i++) {
//                Color color = new Color(img.getRGB(i, j));
//                int value = color.getRed();
//                if (value>=48){
//                    value= 0;
//                }
//                lvlData[j][i] = value;
//            }
//
//
//        }
//        return lvlData;
//    }



}
