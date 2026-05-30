package game;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Swing panel that renders the game.
 *
 * @author Marek
 */
public class GamePanel extends JPanel {

    private Game game;
    private Player player;
    private MouseInputs mouseInputs;


    public GamePanel(Game game,Player player) {
        this.player = player;
        this.game = game;
        setPanelSize();
        setFocusable(true);
        requestFocusInWindow();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Sets the preferred panel size to the game resolution.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(Game.GAME_WIDTH,Game.GAME_HEIGHT);
        setPreferredSize(size);
    }

    /**
     * Paints the current frame.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);

    }


    public Game getGame() {
        return game;
    }
}
