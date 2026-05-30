package gamestate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Common behavior required by state implementations.
 *
 * @author Marek
 */
public interface StateMethods {
    /**
     * Updates the state logic.
     */
    public void update();

    /**
     * Draws the state to the screen.
     */
    public void draw(Graphics g);

    /**
     * Handles mouse click input.
     */
    public void mouseClicked(MouseEvent e);

    /**
     * Handles mouse press input.
     */
    public void mousePressed(MouseEvent e);

    /**
     * Handles mouse release input.
     */
    public void mouseReleased(MouseEvent e);

    /**
     * Handles mouse move input.
     */
    public void mouseMoved(MouseEvent e);

    /**
     * Handles key press input.
     */
    void keyPressed(KeyEvent e);

    /**
     * Handles key release input.
     */
    void keyReleased(KeyEvent e);
}
