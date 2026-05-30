package ui;

import gamestate.Gamestate;
import gamestate.Gamestate;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.Buttons.*;

/**
 * Reusable menu button component.
 *
 * @author Marek
 */
public class MenuButton {
    private int xPos, yPos, row, index;
    private Gamestate gamestate;
    private BufferedImage[] images;
    private int xOffCenter = B_WIDTH / 2;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;



    public MenuButton(int xPos, int yPos, int row, Gamestate gamestate) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.row = row;
        this.gamestate = gamestate;
        loadImg();
        initBounds();
    }

    /**
     * Initializes the clickable button bounds.
     */
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    /**
     * Loads the button sprite sheet.
     */
    private void loadImg() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.BUTTON_ATLAS);
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, row * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);

        }

    }

    /**
     * Draws the button in its current visual state.
     */
    public void draw(Graphics g) {

        g.drawImage(images[index], xPos - xOffCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    /**
     * Updates the button image index based on hover and press state.
     */
    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Applies the associated game state.
     */
    public void apllyGameState() {
        Gamestate.state = gamestate;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     * Resets mouse interaction flags.
     */
    public void resetBools() {
        mousePressed = false;
        mouseOver = false;
    }
}

