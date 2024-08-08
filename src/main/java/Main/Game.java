package Main;

import Entity.Player;
import Utilz.Data;
import Utilz.LoadSave;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JPanel implements Runnable {

    // Window sett
    public static final float SCALE = 1.5f;//1.5
    public static final int DEFAULT_TILE_SIZE = 32;
    public static final int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    public static final int WINDOW_WIDTH = 10 * TILE_SIZE;
    public static final int WINDOW_HEIGHT = 12 * TILE_SIZE;

    // System
    private int FPS = 60;
    private int UPS = 100;
    private Thread thread;
    public Random random;
    public Data data;
    public EntitySpawner eSpawner;
    public CollisionChecker collisionCh;
    public Listeners listeners;
    public UI ui;
    // Entities
    public Player player;

    // state
    public GameStates gameState;

    public Game() {
        initClasses();

        gameState = GameStates.menu;

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(listeners);
        addMouseListener(listeners);
        addMouseMotionListener(listeners);
    }

    private void initClasses() {
        eSpawner = new EntitySpawner(this);
        collisionCh = new CollisionChecker(this);
        listeners = new Listeners(this);
        ui = new UI(this);

        random = new Random();
        data = LoadSave.LoadData();

        player = new Player(this, WINDOW_WIDTH/2-TILE_SIZE/2);
    }

    public void reset() {
        eSpawner.setDefVar();
        player.reset();
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double timeBeetwenUpdate = 1000000000/UPS;
        double timeBeetwenRepaint = 1000000000/FPS;
        double deltaU = 0;
        double deltaR = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        int fps = 0;
        int ups = 0;

        while (thread != null) {
            currentTime = System.nanoTime();

            deltaU += (currentTime - lastTime) / timeBeetwenUpdate;
            deltaR += (currentTime - lastTime) / timeBeetwenRepaint;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
                ups++;
            }
            if (deltaR >= 1){
                repaint();
                deltaR--;
                fps++;
            }

            if (timer >= 1000000000) {
                System.out.println("UPS: " + ups + " FPS: " + fps);
                ups = 0;
                fps = 0;
                timer = 0;
            }
        }
    }

    private void update() {

        if (gameState == GameStates.playing) {
            player.update();
            eSpawner.update();
        }
        ui.update();
    }

    public void paintComponent(Graphics g) {

        ui.draw(g);
//        if (gameState == GameStates.playing) {
//            player.draw(g);  in ui
//
//            eSpawner.draw(g);
//        }

        g.dispose();
    }
}
