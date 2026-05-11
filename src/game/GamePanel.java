package game;

import inputs.KeyboardInputs;
import player.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;
    private Player player;


    public GamePanel(Game game,Player player) {
        this.player = player;
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
    }
    private void setPanelSize() {
        Dimension size = new Dimension(600,600);

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
