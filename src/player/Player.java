package player;

import game.Game;
import java.awt.*;

import static game.LevelManager.IsSolid;

public class Player {
    protected float x, y;
    protected int width, height;

    private boolean left, right;
    private float playerSpeed = 2.0f;
    private Color playerColor = Color.BLACK;
    private float gravity = 0.4f;
    private float velocityY = 0;
    private boolean inAir = false;
    private int jumpSpeed = -12;


    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public Color getPlayerColor() {
        return playerColor;
    }


    public void setColor(String colorName) {

        switch (colorName) {
            case "red":
                playerColor = Color.RED;
                break;
            case "blue":
                playerColor = Color.BLUE;
                break;
            case "green":
                playerColor = Color.GREEN;
                break;
            case "yellow":
                playerColor = Color.YELLOW;
                break;
            case "orange":
                playerColor = Color.ORANGE;
                break;
            default:
                playerColor = Color.BLACK;
        }


    }


    public void render(Graphics g, int yLvlOffset) {
        g.setColor(playerColor);
        g.fillRect((int) x, (int) y - yLvlOffset, width, height);

    }

    public void update(int[][] lvlData) {
        updateMoving();
        updateJumping(lvlData);
    }

    public void updateJumping(int[][] lvlData) {
        jump();
        if (inAir) {
            velocityY += gravity;
            float nextY = y + velocityY;

            if (velocityY > 0 && isOnPlatform(nextY, lvlData)) {
                int tileY = (int) ((nextY + height) / Game.TILES_SIZE);
                y = tileY * Game.TILES_SIZE - height;
                inAir = false;
                velocityY = 0;
                return;
            }

            y = nextY;

            if (y + height >= Game.WORLD_HEIGHT) {
                y = Game.WORLD_HEIGHT - height;
                inAir = false;
                velocityY = 0;
            }
        }

    }

    public void jump() {
        if (!inAir) {
            inAir = true;
            velocityY = jumpSpeed;
        }
    }

    private boolean isOnPlatform(float nextY, int[][] lvlData) {
        float feetY = nextY + height;
        return IsSolid(x, feetY, lvlData)
                || IsSolid(x + width - 1, feetY, lvlData);
    }

    private void updateMoving() {

        if (left) {

            x -= playerSpeed;
            if (x < 0) {
                x = 0;
            }
        } else if (right) {
            x += playerSpeed;
            if (x + width + 2 > Game.GAME_WIDTH) {
                x = Game.GAME_WIDTH - width;
            }
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public float getY() {
        return y;
    }
}

