package game;

import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Loads level data and draws level tiles.
 *
 * @author Marek
 */
public class LevelManager {
    private int[][] lvldata;
    private BufferedImage[] levelSprite;

    public LevelManager() {
        importOutsideSprites();
        this.lvldata = loadLevelFromCSV("level_data.csv");
    }

    /**
     * Loads level data from a CSV file into a tile grid.
     */
    public int[][] loadLevelFromCSV(String fileName) {
        int[][] lvlData = new int[42][26];

        try {

            InputStream is = getClass().getResourceAsStream("/" + fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int y = 0; y < 42; y++) {
                String line = br.readLine();
                if (line == null) break;


                String[] values = line.split(",");

                for (int x = 0; x < 26; x++) {
                    int id = Integer.parseInt(values[x].trim());

                    if (id == -1) {
                        lvlData[y][x] = 11;
                    } else {
                        lvlData[y][x] = id;
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lvlData;
    }

    /**
     * Draws the current level with the given vertical offset.
     */
    public void draw(Graphics g, int yLvlOffset){
        for (int j = 0; j < 42; j++) {
            for (int i = 0; i < 26; i++) {
                int index = lvldata[j][i];


                if (index >= 0 && index < 48) {
                    g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j - yLvlOffset, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }
        }

    }

    public int[][] getLevelData() {
        return lvldata;
    }

    /**
     * Loads the tile atlas used for the level background.
     */
    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }


    /**
     * Checks whether a tile position is solid.
     */
    public static boolean IsSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if (y < 0 || y >= Game.WORLD_HEIGHT)
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value >= 48 || value < 0 || value != 11)
            return true;
        return false;

    }

    /**
     * Checks whether the rectangle is standing on the floor.
     */
    public static boolean IsOnFloor(float x,float y,int width,int height, int[][] lvlData) {
        if (!IsSolid(x, y + height , lvlData) && !IsSolid(x + width, y+ height+1, lvlData)) {
                return false;
            }

        return true;
    }



}
