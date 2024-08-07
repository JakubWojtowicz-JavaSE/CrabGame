package ui;

import Main.Game;
import Main.GameStates;
import Utilz.Constants;
import Utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    private Game game;
    private Font font;
    private UrmButton[] buttons;
    private int BUTTONS_IN_MENU = 3;
    private int BUTTONS_IN_DEATH_S = 2;
    private static int PLAY_B = 0;
    private static int OPTIONS_B = 1;
    private static int QUIT_B = 2;
    private Image backgroundImg;
    private Image menuImg;
    private Image healthBar;
    private Image deathScreenImg;

    public UI(Game game) {
        this.game = game;

        setFonts();
        setImgs();
        createButtons();
    }

    private void setFonts() {
        try {
            InputStream is = getClass().getResourceAsStream("/Font.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
//        font = new Font("/COPRGTB.TTF", Font.TRUETYPE_FONT, (int) (15*Game.SCALE));
    }

    private void setImgs() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.BASKGROUND_IMG);
        menuImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_IMG);
        healthBar = LoadSave.GetSpriteAtlas(LoadSave.HEALTH_BAR_IMG);
        deathScreenImg = LoadSave.GetSpriteAtlas(LoadSave.DEATH_SCREEN);
    }

    private void createButtons() {
        buttons = new UrmButton[BUTTONS_IN_MENU+BUTTONS_IN_DEATH_S];
        buttons[0] = new PlayButton(game, menuX + (int) (36f*Game.SCALE), menuY + (int) (75*Game.SCALE));
        buttons[1] = new OptionsButton(game, menuX + (int) (36f*Game.SCALE), menuY + Constants.ButtonsDetails.BUTTON_HEIGHT + (int) (85*Game.SCALE));
        buttons[2] = new QuitButton(game, menuX + (int) (36f*Game.SCALE), menuY + 2*Constants.ButtonsDetails.BUTTON_HEIGHT + (int) (95*Game.SCALE));

        buttons[3] = new RageQuitButton(game,  deathSX + (int) (26.66f * Game.SCALE), deathSY + (int) (151f*Game.SCALE), 0);
        buttons[4] = new RetryButton(game,  deathSX + (int) (107f * Game.SCALE), deathSY + (int) (151f*Game.SCALE), 0);
    }

    public void update() {
        if (game.gameState == GameStates.menu) {
            if (game.gameState == GameStates.menu) {
                for (UrmButton button : buttons) {
                    button.update();
                }
            }
        }
    }

    public void resetButtons() {
        for (UrmButton button : buttons) {
            button.isPressed = false;
            button.isMouseOver = false;
        }
    }

    public void draw(Graphics g) {
        g.setFont(font.deriveFont(17*Game.SCALE));
        if (game.gameState == GameStates.menu) {
            drawBg(g);
            drawMenu(g);
        } else if (game.gameState == GameStates.playing) {
            drawPlaying(g);
        } else if (game.gameState == GameStates.pause) {

        } else if (game.gameState == GameStates.deathScreen) {
            drawPlaying(g);
            drawDeathScr(g);
        }
    }

    private void drawBg(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, null);
    }

    private int menuX = (int) ((Game.WINDOW_WIDTH-188*Game.SCALE)/2);
    private int menuY = (int) (Game.WINDOW_HEIGHT-270.66f * Game.SCALE)/2;

    private void drawMenu(Graphics g) {
        drawBg(g);
        g.drawImage(menuImg, menuX, menuY, (int) (188*Game.SCALE), (int) (270.66f*Game.SCALE), null);

        for (int i = 0; i < BUTTONS_IN_MENU; i++) {
            buttons[i].draw(g);
        }
    }

    private void drawPlaying(Graphics g) {
        drawBg(g);
        game.player.draw(g);
        game.eSpawner.draw(g);
        drawHealthBar(g);
        drawScore(g, Game.WINDOW_WIDTH/2-(int) (40*Game.SCALE), (int) (80*Game.SCALE));
    }

    private int hBarX = (int) (3*Game.SCALE);
    private int hBarY = (int) (5*Game.SCALE);
    private int fullHealth;
    private int toFill;
    private void drawHealthBar(Graphics g) {
        g.drawImage(healthBar, hBarX, hBarY, (int) (144*Game.SCALE), (int) (43.5f*Game.SCALE), null);

        fullHealth = (int) (112.5f*Game.SCALE);
        toFill = (int) (fullHealth * (game.player.getHealth()/ (float) (game.player.getMaxHealth())));
        g.setColor(new Color(255, 0, 0, 200));
        g.fillRect(hBarX + (int) (25.5f*Game.SCALE), hBarY + (int) (10.5f*Game.SCALE), toFill, (int) (3*Game.SCALE));

        fullHealth = (int) (78f*Game.SCALE);
        toFill = (int) (fullHealth * (game.player.getSpeed()/ game.player.getMaxSpeed()));
        g.setColor(new Color(156, 255, 234, 200));
        g.fillRect(hBarX + (int) (33*Game.SCALE), hBarY + (int) (25.5f*Game.SCALE), toFill, (int) (1.5f*Game.SCALE));
    }

    private void drawScore(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.setFont(font.deriveFont(25*Game.SCALE));
        g.drawString("SCORE: " + game.player.score, x, y);
        g.setColor(Color.WHITE);
        g.setFont(font.deriveFont(25*Game.SCALE));
        g.drawString("SCORE: " + game.player.score, x, y+4);
    }

    private int deathSX = (Game.WINDOW_WIDTH-Constants.DeathScreenDetails.DEATH_S_WIDTH) / 2;
    private int deathSY = (Game.WINDOW_HEIGHT-Constants.DeathScreenDetails.DEATH_S_HEIGHT) / 2;
    private void drawDeathScr(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        g.drawImage(deathScreenImg, deathSX, deathSY, Constants.DeathScreenDetails.DEATH_S_WIDTH, Constants.DeathScreenDetails.DEATH_S_HEIGHT, null);
        drawScore(g, deathSX+(int) (56*Game.SCALE), deathSY+(int) (118*Game.SCALE));
    }

    // to buttons
    public void mousePressed(MouseEvent e) {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < BUTTONS_IN_MENU; i++) {
                buttons[i].mousePressed(e);
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < BUTTONS_IN_DEATH_S; i++) {
                buttons[BUTTONS_IN_MENU+i].mousePressed(e);
            }
        }
    }

    public void mouseRelased(MouseEvent e) {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < BUTTONS_IN_MENU; i++) {
                buttons[i].mouseRelased(e);
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < BUTTONS_IN_DEATH_S; i++) {
                buttons[BUTTONS_IN_MENU+i].mouseRelased(e);
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < BUTTONS_IN_MENU; i++) {
                buttons[i].mouseMoved(e);
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < BUTTONS_IN_DEATH_S; i++) {
                buttons[BUTTONS_IN_MENU+i].mouseMoved(e);
            }
        }
    }
}
