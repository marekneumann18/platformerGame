package player;

import game.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;

public class Player {
    protected float x, y;
    protected int width, height;
    private BufferedImage img;
    private boolean left, right;
    private float playerSpeed = 2.0f;

    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadPlayer();
    }

    private void loadPlayer() {
        img = removeBackground(LoadSave.GetSpriteAtlas(LoadSave.PLAYER_IMAGE));
    }

    private BufferedImage removeBackground(BufferedImage source) {
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int rgb = source.getRGB(x, y);
                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;

                if (isGrayBackground(red, green, blue)) {
                    result.setRGB(x, y, 0);
                } else {
                    result.setRGB(x, y, 0xff000000 | (rgb & 0x00ffffff));
                }
            }
        }

        return result;
    }

    private boolean isGrayBackground(int red, int green, int blue) {
        int max = Math.max(red, Math.max(green, blue));
        int min = Math.min(red, Math.min(green, blue));

        return max - min < 20 && red > 120 && green > 120 && blue > 120;
    }

    public void render(Graphics g) {
        g.drawImage(img, (int) x, (int) y, width, height, null);
    }

    public void update() {
        updateMoving();
    }

    private void updateMoving() {
        float xSpeed = 0;
        if (left) {

            x -= playerSpeed;
            System.out.println(x);
            if (x < 0) {
                x = 0;
            }
        } else if (right) {
            x += playerSpeed;
            System.out.println(x);
            if (x + width+2 > Game.GAME_WIDTH) {
                x = Game.GAME_WIDTH-width;
            }
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
