package game;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;


    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();
        jFrame.setSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }
}
