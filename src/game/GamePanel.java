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
        Dimension size = new Dimension(400,400);

        setPreferredSize(size);



    }


    public Game getGame() {
        return game;
    }
}
