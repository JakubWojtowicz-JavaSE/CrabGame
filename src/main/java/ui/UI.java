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
    private Graphics g;
    private Font font;
    private UrmButton[] buttons;
    private int BUTTONS_IN_MENU = 3;
    private int BUTTONS_IN_OPTIONS = 2;
    private int BUTTONS_IN_DEATH_S = 2;
    private static int PLAY_B = 0;
    private static int OPTIONS_B = 1;
    private static int QUIT_B = 2;
    private Image backgroundImg;
    private Image optionsImg;
    private Image menuImg;
    private Image healthBar;
    private Image pauseMenuImg;
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
    }

    private void setImgs() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.BASKGROUND_IMG);
        optionsImg = LoadSave.GetSpriteAtlas(LoadSave.OPTIONS_IMG);
        menuImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_IMG);
        healthBar = LoadSave.GetSpriteAtlas(LoadSave.HEALTH_BAR_IMG);
        pauseMenuImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_IMG);
        deathScreenImg = LoadSave.GetSpriteAtlas(LoadSave.DEATH_SCREEN);
    }

    private void createButtons() {
        buttons = new UrmButton[BUTTONS_IN_MENU+BUTTONS_IN_OPTIONS+BUTTONS_IN_DEATH_S];
        buttons[0] = new PlayButton(game, menuX + (int) (36f*Game.SCALE), menuY + (int) (75*Game.SCALE));
        buttons[1] = new OptionsButton(game, menuX + (int) (36f*Game.SCALE), menuY + Constants.MenuButtonsDetails.BUTTON_HEIGHT + (int) (85*Game.SCALE));
        buttons[2] = new QuitButton(game, menuX + (int) (36f*Game.SCALE), menuY + 2* Constants.MenuButtonsDetails.BUTTON_HEIGHT + (int) (95*Game.SCALE));

        buttons[3] = new RageQuitButton(game, menuX + (int) (75f*Game.SCALE), menuY + (int) (200f*Game.SCALE));
        buttons[4] = new VolumeSlider(game, menuX + (int) (23f*Game.SCALE), (int) (228f*Game.SCALE));

        buttons[5] = new RageQuitButton(game,  deathSX + (int) (31f * Game.SCALE), deathSY + (int) (105f*Game.SCALE));
        buttons[6] = new RetryButton(game,  deathSX + (int) (120f * Game.SCALE), deathSY + (int) (105f*Game.SCALE));
    }

    public void update() {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < BUTTONS_IN_MENU; i++) {
                buttons[i].update();
            }
        } else if (game.gameState == GameStates.options) {
            for (int i = 0; i < BUTTONS_IN_OPTIONS; i++) {
                buttons[BUTTONS_IN_MENU+i].update();
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < BUTTONS_IN_DEATH_S; i++) {
                buttons[BUTTONS_IN_MENU+BUTTONS_IN_OPTIONS+i].update();
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
        this.g = g;

        g.setFont(font.deriveFont(17*Game.SCALE));
        if (game.gameState == GameStates.menu) {
            drawMenu();
        } else if (game.gameState == GameStates.options) {
            drawOptions();
        } else if (game.gameState == GameStates.playing) {
            drawPlaying();
        } else if (game.gameState == GameStates.pause) {

        } else if (game.gameState == GameStates.deathScreen) {
            drawPlaying();
            drawDeathScr();
        }
    }

    private void drawBg() {
        g.drawImage(backgroundImg, 0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, null);
    }

    private int menuX = (int) ((Game.WINDOW_WIDTH-188*Game.SCALE)/2);
    private int menuY = (int) (Game.WINDOW_HEIGHT-270.66f * Game.SCALE)/2;

    private void drawMenu() {
        drawBg();
        g.drawImage(menuImg, menuX, menuY, (int) (188*Game.SCALE), (int) (270.66f*Game.SCALE), null);

        for (int i = 0; i < BUTTONS_IN_MENU; i++) {
            buttons[i].draw(g);
        }
    }

    private void drawOptions() {
        drawBg();
        g.drawImage(optionsImg, menuX, menuY, (int) (188*Game.SCALE), (int) (270.66f*Game.SCALE), null);

        for (int i = 0; i < BUTTONS_IN_OPTIONS; i++) {
            buttons[BUTTONS_IN_MENU+i].draw(g);
        }
    }

    private void drawPlaying() {
        drawBg();
        game.player.draw(g);
        game.eSpawner.draw(g);
        drawHealthBar();
        drawStr("SCORE: " + game.player.score, Game.WINDOW_WIDTH/2-(int) (40*Game.SCALE), (int) (80*Game.SCALE));
    }

    private int hBarX = (int) (3*Game.SCALE);
    private int hBarY = (int) (5*Game.SCALE);
    private int fullHealth;
    private int toFill;
    private void drawHealthBar() {
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

    private void drawStr(String str, int x, int y) {
        g.setFont(font.deriveFont(25*Game.SCALE));
        g.setColor(Color.BLACK);
        g.drawString(str, x, y);
        g.setColor(Color.WHITE);
        g.drawString(str, x, y+4);
    }

    private int deathSX = (Game.WINDOW_WIDTH-Constants.DeathScreenDetails.DEATH_S_WIDTH) / 2;
    private int deathSY = (Game.WINDOW_HEIGHT-Constants.DeathScreenDetails.DEATH_S_HEIGHT) / 2;
    private void drawDeathScr() {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        g.drawImage(deathScreenImg, deathSX, deathSY, Constants.DeathScreenDetails.DEATH_S_WIDTH, Constants.DeathScreenDetails.DEATH_S_HEIGHT, null);
        drawStr("SCORE: " + game.player.score, deathSX+(int) (55*Game.SCALE), deathSY/2-(int) (5*Game.SCALE));
        drawStr("BEST SCORE: " + game.player.bestScore, deathSX+(int) (24*Game.SCALE), deathSY/2+(int) (25*Game.SCALE));

        for (int i = 0; i < BUTTONS_IN_DEATH_S; i++) {
            buttons[BUTTONS_IN_MENU+BUTTONS_IN_OPTIONS+i].draw(g);
        }
    }

    // to buttons
    public void mousePressed(MouseEvent e) {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < BUTTONS_IN_MENU; i++) {
                buttons[i].mousePressed(e);
            }
        } else if (game.gameState == GameStates.options) {
            for (int i = 0; i < BUTTONS_IN_OPTIONS; i++) {
                buttons[BUTTONS_IN_MENU+i].mousePressed(e);
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < BUTTONS_IN_DEATH_S; i++) {
                buttons[BUTTONS_IN_MENU+BUTTONS_IN_OPTIONS+i].mousePressed(e);
            }
        }
    }

    public void mouseRelased(MouseEvent e) {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < BUTTONS_IN_MENU; i++) {
                buttons[i].mouseRelased(e);
            }
        } else if (game.gameState == GameStates.options) {
            for (int i = 0; i < BUTTONS_IN_OPTIONS; i++) {
                buttons[BUTTONS_IN_MENU+i].mouseRelased(e);
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < BUTTONS_IN_DEATH_S; i++) {
                buttons[BUTTONS_IN_MENU+BUTTONS_IN_OPTIONS+i].mouseRelased(e);
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        for (int i = 0; i < BUTTONS_IN_OPTIONS; i++) {
            buttons[BUTTONS_IN_MENU+i].mouseDragged(e);
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < BUTTONS_IN_MENU; i++) {
                buttons[i].mouseMoved(e);
            }
        } else if (game.gameState == GameStates.options) {
            for (int i = 0; i < BUTTONS_IN_OPTIONS; i++) {
                buttons[BUTTONS_IN_MENU+i].mouseMoved(e);
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < BUTTONS_IN_DEATH_S; i++) {
                buttons[BUTTONS_IN_MENU+BUTTONS_IN_OPTIONS+i].mouseMoved(e);
            }
        }
    }
}
