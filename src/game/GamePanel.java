package game;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import player.Player;

import javax.swing.*;
import java.awt.*;

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
    private void setPanelSize() {
        Dimension size = new Dimension(Game.GAME_WIDTH,Game.GAME_HEIGHT);

        setPreferredSize(size);



    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        updateRectangle();
//        g.setColor(color);
//        g.fillRect((int) xDelta, (int) yDelta, 200, 50);
//        repaint();
        game.render(g);

    }


    public Game getGame() {
        return game;
    }
}
