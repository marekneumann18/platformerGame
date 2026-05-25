package utilz;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadSave {
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String BUTTON_ATLAS = "button_atlas.png";
    
    private static final String CONFIG_DIR = System.getProperty("user.home") + "/.platformerGame";
    private static final String CONFIG_FILE = CONFIG_DIR + "/config.dat";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            System.out.println("špatne img");
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException f) {
                System.out.println("nazaveno");

            }

        }
        return img;
    }
    public static int[][] GetLevelData() {
        int[][] lvlData = new int[Game.WORLD_TILES_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ATLAS);
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value>=48){
                    value= 0;
                }
                lvlData[j][i] = value;
            }


        }
        return lvlData;
    }

    /**
     * Uloží FPS a UPS nastavení do souboru
     * pomoci ai
     */
    public static void saveConfig(Game game) {
        try {
            // Vytvor složku, pokud neexistuje
            Path configDirPath = Paths.get(CONFIG_DIR);
            if (!Files.exists(configDirPath)) {
                Files.createDirectories(configDirPath);
            }

            GameConfig config = new GameConfig(game.getFPS(), game.getUPS());
            
            // Serializuj objekt do souboru
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(CONFIG_FILE))) {
                oos.writeObject(config);
                System.out.println("Konfigurace uložena: " + CONFIG_FILE);
            }
        } catch (IOException e) {
            System.out.println("Chyba při ukládání konfigurace: " + e.getMessage());
        }
    }

    /**
     * Načte FPS a UPS nastavení ze souboru
     * pomoci ai
     */
    public static void loadConfig(Game game) {
        try {
            Path configPath = Paths.get(CONFIG_FILE);
            if (!Files.exists(configPath)) {
                System.out.println("Konfigurační soubor neexistuje, používám výchozí hodnoty.");
                return;
            }

            // Deserializuj objekt ze souboru
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(CONFIG_FILE))) {
                GameConfig config = (GameConfig) ois.readObject();

                if (config.getFps() > 0) {
                    game.setFPS_SET(config.getFps());
                }
                if (config.getUps() > 0) {
                    game.setUPS_SET(config.getUps());
                }

                System.out.println("Konfigurace načtena: FPS=" + config.getFps() + 
                                   ", UPS=" + config.getUps());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Chyba při čtení konfigurace: " + e.getMessage());
        }
    }

    /**
     * Vnitřní třída pro serializaci nastavení
     * ai
     */
    public static class GameConfig implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private final int fps;
        private final int ups;
        
        public GameConfig(int fps, int ups) {
            this.fps = fps;
            this.ups = ups;
        }
        
        public int getFps() {
            return fps;
        }
        
        public int getUps() {
            return ups;
        }
    }
}
