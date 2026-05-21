package player;

import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    protected float x, y;
    protected int width, height;

    private boolean left, right;
    private float playerSpeed = 2.0f;
    private Color playerColor = Color.BLACK;
    private float gravity = 0.5f;
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


    public void render(Graphics g) {
        g.setColor(playerColor);
        g.fillRect((int) x, (int) y, width, height);

    }

    public void update() {
        updateMoving();
        updateJumping();
    }

    public void updateJumping() {
        jump();
        if (inAir) {
            velocityY += gravity;
            y += velocityY;
            if (y + height >= Game.GAME_HEIGHT) {
                y = Game.GAME_HEIGHT - height;
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
}

