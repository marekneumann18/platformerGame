package player;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_IMAGE);
    }

    public void render(Graphics g){
        g.drawImage(img,200,200,null);
    }
    public void update(){
        updateMoving();
    }
    private void updateMoving(){
        float xSpeed = 0;
        if (left)
            xSpeed -= playerSpeed;
        else if (right)
            xSpeed += playerSpeed;
    }


    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
