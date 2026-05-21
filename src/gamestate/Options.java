package gamestate;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Options extends State implements StateMethods {
    private JFrame optionsFrame;

    public Options(Game game) {
        super(game);
    }


    private void showOptionsWindow() {
        if (optionsFrame != null && optionsFrame.isDisplayable()) {
            optionsFrame.toFront();
            return;
        }

        optionsFrame = new JFrame("Options");
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setSize(300, 200);
        optionsFrame.setLocationRelativeTo(null);
        optionsFrame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Nastavení hry", SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);


        JPanel center = new JPanel();
        // Rozvržení pro nastavení: sloupce (label, ovládací prvek) a libovolný počet řádků
        center.setLayout(new GridLayout(0, 2, 8, 8));
        panel.add(center, BorderLayout.CENTER);

        JLabel ups = new JLabel("UPS: ");
        JComboBox<Integer> upsChanger = new JComboBox<>(new Integer[]{30, 60, 120, 144,200, 240});
        upsChanger.setSelectedItem(getGame().getUPS());
        upsChanger.addActionListener(e -> {
                    int selectedUPS = (Integer) upsChanger.getSelectedItem();
                    getGame().setUPS_SET(selectedUPS);
                });
        JLabel fps = new JLabel("FPS: ");
        JComboBox<Integer> fpsChanger = new JComboBox<>(new Integer[]{30, 60, 120, 144, 240});
        fpsChanger.setSelectedItem(getGame().getFPS());
        fpsChanger.addActionListener(e -> {
            int selectedFPS = (Integer) fpsChanger.getSelectedItem();
            getGame().setFPS_SET(selectedFPS);
        });
        JLabel playerColor = new JLabel("Barva hráče: ");
        JComboBox<String>playerChangerColor = new JComboBox<>(new String[]{"red", "green", "blue", "yellow",     "orange"});
        playerChangerColor.setSelectedItem(getGame().getPlaying().getPlayer().getPlayerColor());
        playerChangerColor.addActionListener(e -> {
            Color c = getGame().getPlaying().getPlayer().getPlayerColor();
            getGame().getPlaying().getPlayer().setColor((String) playerChangerColor.getSelectedItem());
        });

        center.add(ups);
        center.add(upsChanger);
        center.add(fps);
        center.add(fpsChanger);
        center.add(playerColor);
        center.add(playerChangerColor);
        //center.add(fps, BorderLayout.EAST);
        JButton closeBtn = new JButton("Zavřít");
        closeBtn.addActionListener(e -> {
            optionsFrame.dispose();
            Gamestate.state = Gamestate.MENU;
        });

        JPanel south = new JPanel();
        south.add(closeBtn);
        panel.add(south, BorderLayout.SOUTH);

        optionsFrame.setContentPane(panel);

        // Pokud uživatel zavře okno křížkem, přepneme stav zpět na MENU
        optionsFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                Gamestate.state = Gamestate.MENU;
            }
        });

        optionsFrame.setVisible(true);
    }
    

    @Override
    public void update() {
        showOptionsWindow();
    }

    @Override
    public void draw(Graphics g) {
        // GUI nastavení běží v samostatném JFrame, tady nic nekreslíme
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

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
