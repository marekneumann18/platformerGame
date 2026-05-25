package gamestate;

import game.Game;
import game.LevelManager;
import player.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {
    private Player player;
    private LevelManager levelManager;
    private int yLvlOffset;
    private int maxYOffset;

    public Playing(Game game) {
        super(game);
        init();
    }

    public void init() {
        player = new Player(400, 600, 50, 50);
        levelManager = new LevelManager();
        maxYOffset = Game.WORLD_HEIGHT - Game.GAME_HEIGHT;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        player.update(levelManager.getLevelData());
        updateCamera();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, yLvlOffset);
        player.render(g, yLvlOffset);

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

    private void updateCamera() {
        yLvlOffset = (int) (player.getY() - Game.GAME_HEIGHT / 2);

        if (yLvlOffset < 0) {
            yLvlOffset = 0;
        } else if (yLvlOffset > maxYOffset) {
            yLvlOffset = maxYOffset;
        }
    }
}
