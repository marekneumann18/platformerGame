package game;

import inputs.KeyboardInputs;
import player.Player;



public class Game implements Runnable {
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private GamePanel gamePanel;
    private KeyboardInputs keyboardInputs;
    private GameWindow gameWindow;

    public Game() {
        init();
        gamePanel = new GamePanel(this,player);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startLoop();
    }

    public void init() {
        player = new Player(200, 200, 50, 50);
    }

    public void startLoop() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previopusTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        while (true) {


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
    }

    public Player getPlayer() {
        return player;
    }

    private void update() {

        player.update();

    }
}
