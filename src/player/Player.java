package player;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    protected float x, y;
    protected int width, height;
    private BufferedImage img;

    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadPlayer();
    }

    private void loadPlayer() {
        img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_IMAGE);
    }

    public void render(Graphics g){
        g.drawImage(img,)
    }

}
