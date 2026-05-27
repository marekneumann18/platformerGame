package gamestate;

import game.Game;
import game.LevelManager;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {
    private Player player;
    private LevelManager levelManager;
    private int yLvlOffset;
    private int maxYOffset;
    private int seconds;
    private Timer timer;

    public Playing(Game game) {
        super(game);
        init();
    }

    public void init() {
        player = new Player(400, Game.WORLD_HEIGHT - 2*Game.TILES_DEFAULT_SIZE*Game.SCALE, 50, 50);
        levelManager = new LevelManager();
        player.loadLvlData(levelManager.getLevelData());
        maxYOffset = Game.WORLD_HEIGHT - Game.GAME_HEIGHT;
        setTimer(game);
    }

    public Player getPlayer() {
        return player;
    }
    public void setTimer(Game game) {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                System.out.println("Zbývá sekund: " + seconds);



            }
        });


    }
    public void startTimer() {
        seconds = 0;
        timer.start();
    }
    public void endTimer() {
        timer.stop();
    }

    @Override
    public void update() {
        player.update(levelManager.getLevelData(),game);
        updateCamera();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, yLvlOffset);
        player.render(g, yLvlOffset);
       // g.drawString("Time: " + seconds + "s", SwingConstants.CENTER, SwingConstants.TOP);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_W:
                player.jump();
                break;
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.MENU;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;

        }
    }

    /**
     * made by AI
     */
    private void updateCamera() {
        yLvlOffset = (int) (player.getY() - Game.GAME_HEIGHT / 2);

        if (yLvlOffset < 0) {
            yLvlOffset = 0;
        } else if (yLvlOffset > maxYOffset) {
            yLvlOffset = maxYOffset;
        }
    }
}
