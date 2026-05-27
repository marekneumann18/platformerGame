package gamestate;

import game.Game;
import ui.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static game.Game.GAME_WIDTH;
import static game.Game.SCALE;

public class Menu extends State implements StateMethods {

    private MenuButton[] buttons = new MenuButton[3];


    public Menu(Game game) {
        super(game);
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(GAME_WIDTH / 2, (int) (150 * SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(GAME_WIDTH / 2, (int) (220 * SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(GAME_WIDTH / 2, (int) (290 * SCALE), 2, Gamestate.QUIT);

    }

    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (MenuButton mb : buttons)
            mb.draw(g);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIN(e, mb))
                mb.setMousePressed(true);

        }

    }

    private void resetButtons() {
        for (MenuButton mb : buttons)
            mb.resetBools();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIN(e, mb)) {
                if (mb.isMousePressed())
                    mb.apllyGameState();
                if (Gamestate.state == Gamestate.PLAYING) {
                    game.getPlaying().startTimer();
                }
                break;
            }
        }
        resetButtons();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);

        for (MenuButton mb : buttons)
            if (isIN(e, mb)) {
                mb.setMouseOver(true);
                break;
            }


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            game.getPlaying().startTimer();
            Gamestate.state = Gamestate.PLAYING;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}
