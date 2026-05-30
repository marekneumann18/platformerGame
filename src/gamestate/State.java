package gamestate;

import game.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

/**
 * Base class for game states.
 *
 * @author Marek
 */
public class State {
    protected Game game;
    public State(Game game){
        this.game = game;

    }

    public Game getGame() {
        return game;
    }

    /**
     * Checks whether the mouse is inside a menu button.
     */
    public boolean isIN(MouseEvent e, MenuButton mb){
        return mb.getBounds().contains(e.getX(),e.getY());
    }

}
