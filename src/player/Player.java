package player;

import game.Game;
import gamestate.Gamestate;
import utilz.LoadSave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.LevelManager.IsOnFloor;
import static game.LevelManager.IsSolid;

/**
 * Represents the player character.
 *
 * @author Marek
 */
public class Player {
    protected float x, y;
    protected int width, height;

    private boolean left, right;

    int[][] lvldata;
    private float playerSpeed = 2.0f;
    private Color playerColor = Color.BLACK;
    private float gravity = 0.4f;
    private float velocityY = 0;
    private boolean inAir = false;
    private int jumpSpeed = -12;
    private boolean autojump = true;


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


    /**
     * Renders the player as a colored rectangle.
     */
    public void render(Graphics g, int yLvlOffset) {
        g.setColor(playerColor);
        g.fillRect((int) x, (int) y - yLvlOffset, width, height);

    }

    /**
     * Updates movement and checks for victory.
     */
    public void update(int[][] lvlData, Game game) {
        if (!isOnLastPlatform(x, y, lvlData)) {
            updateMoving(lvlData);
            updateJumping(lvlData);
        } else {
            wictory(game);

        }


    }

    /**
     * Finishes the run and switches to the victory state.
     */
    public void wictory(Game game) {
        game.getPlaying().endTimer();
        LoadSave.saveWictoryTime(game.getPlaying().getTotalSeconds());
        Gamestate.state = Gamestate.WICTORY;
    }


    /**
     * Applies gravity and handles landing on platforms.
     */
    public void updateJumping(int[][] lvlData) {
        if (autojump)
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

    /**
     * Starts a jump when the player is on the ground.
     */
    public void jump() {
        if (!inAir) {
            inAir = true;
            velocityY = jumpSpeed;
        }
    }

    /**
     * Checks whether the player is standing on solid ground at the next Y position.
     */
    private boolean isOnPlatform(float nextY, int[][] lvlData) {
        float feetY = nextY + height;
        return IsSolid(x, feetY, lvlData)
                || IsSolid(x + width - 1, feetY, lvlData);
    }

    /**
     * Moves the player left or right and keeps the player inside the screen.
     */
    private void updateMoving(int[][] lvlData) {

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
        if (!IsOnFloor(x, y, width, height, lvlData)) {
            inAir = true;
        }
    }

    /**
     * Checks whether the player reached the last platform.
     */
    private boolean isOnLastPlatform(float x, float y, int[][] lvlData) {
        if (y == 46 && IsOnFloor(x, y, width, height, lvlData)) {
            return true;
        }
        return false;
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

    public boolean isAutojump() {
        return autojump;
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvldata = lvlData;
//        if (!IsOnFloor(x,y,width,height,lvlData))
//            inAir = true;
    }

    public void setAutojump(boolean autojump) {
        this.autojump = autojump;
    }
}

