package gamestate;

import game.Game;

import javax.swing.*;
import java.awt.*;

public class WictoryWindow {
    JFrame wictoryFrame;
    private boolean wasShown;
    private Game game;

    public WictoryWindow(Game game) {
        this.game = game;
    }

    public void showWictoryWindow() {
        if (wasShown) {
            return;
        }

        wasShown = true;
        wictoryFrame = new JFrame();
        wictoryFrame.setSize(300,300);
        wictoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wictoryFrame.setLocationRelativeTo(null);
        wictoryFrame.setResizable(false);

        JPanel wictoryPanel = new JPanel();
        wictoryPanel.setLayout(new GridLayout());
        wictoryFrame.add(wictoryPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        JButton playAgain = new JButton("Play Again");
        playAgain.setFont(new Font("Arial", Font.BOLD,10));
        playAgain.addActionListener(e -> {
            wictoryFrame.dispose();
            game.restartGame();
        });
        wictoryFrame.add(buttonPanel,BorderLayout.SOUTH);
        buttonPanel.add(playAgain,BorderLayout.EAST);

        wictoryFrame.setVisible(true);
    }

    public void update() {
        showWictoryWindow();
    }

    public void reset() {
        wasShown = false;
        wictoryFrame = null;
    }

}
