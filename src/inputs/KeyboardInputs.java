package inputs;

import game.GamePanel;
import gamestate.Gamestate;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Routes keyboard input to the active state.
 *
 * @author Marek
 */
public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Routes key press events to the active state.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }

    }

    /**
     * Routes key release events to the active state.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;

        }


    }
}
