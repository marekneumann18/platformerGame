package gamestate;

import game.Game;
import utilz.LoadSave;

import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

/**
 * Victory window displayed after completing a run.
 *
 * @author Marek
 */
public class VictoryWindow {
    JFrame wictoryFrame;
    private boolean wasShown;
    private Game game;


    public VictoryWindow(Game game) {
        this.game = game;
    }

    /**
     * Shows the victory window once per completed run.
     */
    public void showVictoryWindow() {
        if (wasShown) {
            return;
        }

        wasShown = true;
        wictoryFrame = new JFrame("Victory");
        wictoryFrame.setSize(320, 420);
        wictoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wictoryFrame.setLocationRelativeTo(null);
        wictoryFrame.setResizable(false);

        JPanel content = new JPanel(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new GridLayout(0, 1));
        JLabel titleLabel = new JLabel("Victory", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        JLabel wictoryLabel = new JLabel(
                "Time: " + LoadSave.formatTime(game.getPlaying().getTotalSeconds()),
                SwingConstants.CENTER
        );
        headerPanel.add(titleLabel);
        headerPanel.add(wictoryLabel);
        content.add(headerPanel, BorderLayout.NORTH);

        JPanel leaderboardPanel = new JPanel(new GridLayout(0, 1));
        leaderboardPanel.add(new JLabel("Leaderboard", SwingConstants.CENTER));
        TreeSet<Integer> leaderboard = LoadSave.loadLeaderboardTimes();
        if (leaderboard.isEmpty()) {
            leaderboardPanel.add(new JLabel("No times yet", SwingConstants.CENTER));
        } else {
            int place = 1;

            for (Integer time : leaderboard) {
                leaderboardPanel.add(new JLabel(place + ". " + LoadSave.formatTime(time)));
                if (place == 5) break;
                place++;
            }

        }
        content.add(leaderboardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());

        JButton playAgain = new JButton("Play Again");
        playAgain.setFont(new Font("Arial", Font.BOLD, 10));
        playAgain.addActionListener(e -> {
            wictoryFrame.dispose();
            game.restartGame();
        });
        buttonPanel.add(playAgain, BorderLayout.EAST);
        content.add(buttonPanel, BorderLayout.SOUTH);

        wictoryFrame.setContentPane(content);
        wictoryFrame.setVisible(true);
    }

    /**
     * Updates the victory UI when the game reaches the victory state.
     */
    public void update() {
        showVictoryWindow();
    }

    /**
     * Clears the window state so it can be shown again after a restart.
     */
    public void reset() {
        wasShown = false;
        wictoryFrame = null;
    }

}
