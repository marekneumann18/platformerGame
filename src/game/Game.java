package game;

import gamestate.Gamestate;
import gamestate.Menu;
import gamestate.Options;
import gamestate.Playing;
import inputs.KeyboardInputs;
import player.Player;
import utilz.LoadSave;

import java.awt.*;
import java.util.Timer;


public class Game implements Runnable {
    private Thread gameThread;
    private  int FPS_SET = 120;
    private  int UPS_SET = 200;
    private GamePanel gamePanel;
    private Player player;
    private KeyboardInputs keyboardInputs;
    private GameWindow gameWindow;
    private boolean running = false;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    public final static int WORLD_TILES_HEIGHT = 42;
    public final static int WORLD_HEIGHT = TILES_SIZE * WORLD_TILES_HEIGHT;
    private Menu menu;
    private Playing playing;
    private Options options;



    public Game() {
        init();
        gamePanel = new GamePanel(this, player);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocusInWindow();
        startLoop();
    }

    public void init() {
        menu = new Menu(this);
        playing = new Playing(this);
        options = new Options(this);
        LoadSave.loadConfig(this);

    }

    public void startLoop() {
        gameThread = new Thread(this);
        running = true;
        gameThread.start();

    }

    @Override
    public void run() {
        long previopusTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        while (running) {
            double timePerFrame = 1000000000.0 / FPS_SET;
            double timePerUpdate = 1000000000.0 / UPS_SET;

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previopusTime) / timePerUpdate;
            deltaF += (currentTime - previopusTime) / timePerFrame;
            previopusTime = currentTime;
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }
        // Po ukončení hlavní smyčky ukončíme JVM, aby se proces opravdu zastavil
        System.exit(0);
    }

    public Player getPlayer() {
        return player;
    }



    public void update() {
        switch (Gamestate.state) {
            case PLAYING -> {
                playing.update();
            }
            case MENU -> {
                menu.update();
            }
            case QUIT -> {
                if (gameWindow != null) {
                    gameWindow.close();
                }
                running = false;
            }
            case OPTIONS -> {
                options.update();
            }

        }
    }


    public void render(Graphics g) {
        switch (Gamestate.state) {
            case PLAYING -> {
                playing.draw(g);
            }
            case MENU -> {
                menu.draw(g);
            }
            case OPTIONS -> {
                options.draw(g);
            }
        }
    }

    public void setFPS_SET(int FPS_SET) {
        this.FPS_SET = FPS_SET;
        LoadSave.saveConfig(this);
    }

    public int getFPS() {
        return FPS_SET;
    }

    public int getUPS() {
        return UPS_SET;
    }

    public void setUPS_SET(int UPS_SET) {
        this.UPS_SET = UPS_SET;
        LoadSave.saveConfig(this);
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
