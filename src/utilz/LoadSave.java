package utilz;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;

/**
 * Handles loading and saving of game resources and persistent data.
 *
 * @author Marek
 */
public class LoadSave {
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String BUTTON_ATLAS = "button_atlas.png";

    private static final String CONFIG_DIR = System.getProperty("user.home") + "/.platformerGame";
    private static final String CONFIG_FILE = CONFIG_DIR + "/config.dat";
    private static final String LEADERBOARD_FILE = CONFIG_DIR + "/leaderboard.csv";

    /**
     * Loads a sprite atlas image from the classpath.
     *
     * @param fileName the resource file name
     * @return the loaded sprite atlas, or {@code null} if loading fails
     */
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Failed to load sprite atlas.");
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException f) {
                System.out.println("Failed to close sprite stream.");
            }
        }
        return img;
    }

    /**
     * Saves a new victory time to the leaderboard file.
     *
     * @param seconds the completion time in seconds
     */
    public static void saveWictoryTime(int seconds) {
        TreeSet<Integer> leaderboard = loadLeaderboardTimes();
        leaderboard.add(seconds);

        try {
            Path configDirPath = Paths.get(CONFIG_DIR);
            if (!Files.exists(configDirPath)) {
                Files.createDirectories(configDirPath);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEADERBOARD_FILE))) {
                for (Integer time : leaderboard) {
                    writer.write(String.valueOf(time));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error while saving leaderboard: " + e.getMessage());
        }
    }

    /**
     * Loads all saved leaderboard times from disk.
     *
     * @return sorted leaderboard times
     */
    public static TreeSet<Integer> loadLeaderboardTimes() {
        TreeSet<Integer> leaderboard = new TreeSet<>();
        Path leaderboardPath = Paths.get(LEADERBOARD_FILE);

        if (!Files.exists(leaderboardPath)) {
            return leaderboard;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(LEADERBOARD_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    leaderboard.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException ignored) {
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading leaderboard: " + e.getMessage());
        }

        return leaderboard;
    }

    /**
     * Formats a time in seconds as mm:ss.
     *
     * @param totalSeconds the time in seconds
     * @return the formatted time string
     */
    public static String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Saves FPS and UPS settings to disk.
     * bz ai
     *
     * @param game the active game instance
     */
    public static void saveConfig(Game game) {
        try {
            Path configDirPath = Paths.get(CONFIG_DIR);
            if (!Files.exists(configDirPath)) {
                Files.createDirectories(configDirPath);
            }

            GameConfig config = new GameConfig(game.getFPS(), game.getUPS());

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(CONFIG_FILE))) {
                oos.writeObject(config);
                System.out.println("Configuration saved: " + CONFIG_FILE);
            }
        } catch (IOException e) {
            System.out.println("Error while saving configuration: " + e.getMessage());
        }
    }

    /**
     * Loads FPS and UPS settings from disk.
     * by ai
     *
     * @param game the active game instance
     */
    public static void loadConfig(Game game) {
        try {
            Path configPath = Paths.get(CONFIG_FILE);
            if (!Files.exists(configPath)) {
                System.out.println("Configuration file does not exist, using default values.");
                return;
            }

            // Deserialize the config file.
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(CONFIG_FILE))) {
                GameConfig config = (GameConfig) ois.readObject();

                if (config.getFps() > 0) {
                    game.setFPS_SET(config.getFps());
                }
                if (config.getUps() > 0) {
                    game.setUPS_SET(config.getUps());
                }

                System.out.println("Configuration loaded: FPS=" + config.getFps() +
                        ", UPS=" + config.getUps());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reading configuration: " + e.getMessage());
        }
    }

    /**
     * Serializable configuration holder for FPS and UPS values.
     * by ai
     *
     * @author Marek
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
