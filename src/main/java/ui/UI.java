package ui;

import Entity.Type;
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
    private UrmButton[][] buttons;
    private int MENU_STATE = 0;
    private int OPTIONS_STATE = 1;
    private int PAUSE_STATE = 2;
    private int DEATH_S_STATE = 3;
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
            InputStream is = getClass().getResourceAsStream("/ui/Font.ttf");
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
        pauseMenuImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_MENU);
        deathScreenImg = LoadSave.GetSpriteAtlas(LoadSave.DEATH_SCREEN);
    }

    private void createButtons() {
        buttons = new UrmButton[4][6];

        buttons[MENU_STATE][0] = new PlayButton(game, menuX + (int) (36f*Game.SCALE), menuY + (int) (75*Game.SCALE));
        buttons[MENU_STATE][1] = new OptionsButton(game, menuX + (int) (36f*Game.SCALE), menuY + Constants.MenuButtonsDetails.BUTTON_HEIGHT + (int) (85*Game.SCALE));
        buttons[MENU_STATE][2] = new QuitButton(game, menuX + (int) (36f*Game.SCALE), menuY + 2* Constants.MenuButtonsDetails.BUTTON_HEIGHT + (int) (95*Game.SCALE));

        buttons[OPTIONS_STATE][0] = new SoundButton(game, menuX + (int) (119f*Game.SCALE), menuY + (int) (72f*Game.SCALE), Type.music);
        buttons[OPTIONS_STATE][1] = new SoundButton(game, menuX + (int) (119f*Game.SCALE), menuY + (int) (105f*Game.SCALE), Type.sfx);
        buttons[OPTIONS_STATE][2] = new VolumeSlider(game, menuX + (int) (23f*Game.SCALE), (int) (228f*Game.SCALE));
        buttons[OPTIONS_STATE][3] = new HomeButton(game, menuX + (int) (75f*Game.SCALE), menuY + (int) (200f*Game.SCALE));

        buttons[PAUSE_STATE][0] = new SoundButton(game, menuX + (int) (119f*Game.SCALE), menuY + (int) (79.5f*Game.SCALE), Type.music);
        buttons[PAUSE_STATE][1] = new SoundButton(game, menuX + (int) (119f*Game.SCALE), menuY + (int) (112.5f*Game.SCALE), Type.sfx);
        buttons[PAUSE_STATE][2] = new VolumeSlider(game, menuX + (int) (23f*Game.SCALE), (int) (235.5f*Game.SCALE));
        buttons[PAUSE_STATE][3] = new ResumeButton(game, menuX + (int) (25f*Game.SCALE), menuY + (int) (212f*Game.SCALE));
        buttons[PAUSE_STATE][4] = new RestartButton(game, menuX + (int) (75f*Game.SCALE), menuY + (int) (212f*Game.SCALE));
        buttons[PAUSE_STATE][5] = new HomeButton(game, menuX + (int) (125f*Game.SCALE), menuY + (int) (212f*Game.SCALE));

        buttons[DEATH_S_STATE][0] = new HomeButton(game,  deathSX + (int) (31f * Game.SCALE), deathSY + (int) (105f*Game.SCALE));
        buttons[DEATH_S_STATE][1] = new RestartButton(game,  deathSX + (int) (120f * Game.SCALE), deathSY + (int) (105f*Game.SCALE));
    }

    public void update() {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < buttons[MENU_STATE].length; i++) {
                if (buttons[MENU_STATE][i] != null)
                    buttons[MENU_STATE][i].update();
            }
        } else if (game.gameState == GameStates.options) {
            for (int i = 0; i < buttons[OPTIONS_STATE].length; i++) {
                if (buttons[OPTIONS_STATE][i] != null)
                    buttons[OPTIONS_STATE][i].update();
            }
        } else if (game.gameState == GameStates.pause) {
            for (int i = 0; i < buttons[PAUSE_STATE].length; i++) {
                if (buttons[PAUSE_STATE][i] != null)
                    buttons[PAUSE_STATE][i].update();
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < buttons[DEATH_S_STATE].length; i++) {
                if (buttons[DEATH_S_STATE][i] != null)
                    buttons[DEATH_S_STATE][i].update();
            }
        }
    }

    public void resetButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                if (buttons[i][j] != null) {
                    buttons[i][j].index = 0;
                    buttons[i][j].isPressed = false;
                    buttons[i][j].isMouseOver = false;
                }
            }
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
            drawPlaying();
            drawPause();
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

        for (int i = 0; i < buttons[MENU_STATE].length; i++) {
            if (buttons[MENU_STATE][i] != null)
                buttons[MENU_STATE][i].draw(g);
        }
    }

    private void drawOptions() {
        drawBg();
        g.drawImage(optionsImg, menuX, menuY, (int) (188*Game.SCALE), (int) (270.66f*Game.SCALE), null);

        for (int i = 0; i < buttons[OPTIONS_STATE].length; i++) {
            if (buttons[OPTIONS_STATE][i] != null)
                buttons[OPTIONS_STATE][i].draw(g);
        }
    }

    private void drawPlaying() {
        drawBg();
        game.player.draw(g);
        game.eSpawner.draw(g);
        drawHealthBar();
        drawStr("SCORE: " + game.player.score, Game.WINDOW_WIDTH/2-(int) (40*Game.SCALE), (int) (70*Game.SCALE));
        drawStr("BUDGET: " + game.player.budget, Game.WINDOW_WIDTH/2-(int) (40*Game.SCALE), (int) (90*Game.SCALE));
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

    private void drawPause() {
        darkenScreen();
        g.drawImage(pauseMenuImg, menuX, menuY, (int) (188*Game.SCALE), (int) (270.66f*Game.SCALE), null);

        for (int i = 0; i < buttons[PAUSE_STATE].length; i++) {
            if (buttons[PAUSE_STATE][i] != null)
                buttons[PAUSE_STATE][i].draw(g);
        }
    }

    private int deathSX = (Game.WINDOW_WIDTH-Constants.DeathScreenDetails.DEATH_S_WIDTH) / 2;
    private int deathSY = (Game.WINDOW_HEIGHT-Constants.DeathScreenDetails.DEATH_S_HEIGHT) / 2;
    private void drawDeathScr() {
        darkenScreen();
        g.drawImage(deathScreenImg, deathSX, deathSY, Constants.DeathScreenDetails.DEATH_S_WIDTH, Constants.DeathScreenDetails.DEATH_S_HEIGHT, null);
        drawStr("SCORE: " + game.player.score, deathSX+(int) (55*Game.SCALE), deathSY/2-(int) (5*Game.SCALE));
        drawStr("BEST SCORE: " + game.player.bestScore, deathSX+(int) (24*Game.SCALE), deathSY/2+(int) (25*Game.SCALE));

        for (int i = 0; i < buttons[DEATH_S_STATE].length; i++) {
            if (buttons[DEATH_S_STATE][i] != null)
                buttons[DEATH_S_STATE][i].draw(g);
        }
    }

    private void darkenScreen() {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
    }

    // to buttons
    public void mousePressed(MouseEvent e) {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < buttons[MENU_STATE].length; i++) {
                if (buttons[MENU_STATE][i] != null)
                    buttons[MENU_STATE][i].mousePressed(e);
            }
        } else if (game.gameState == GameStates.options) {
            for (int i = 0; i < buttons[OPTIONS_STATE].length; i++) {
                if (buttons[OPTIONS_STATE][i] != null)
                    buttons[OPTIONS_STATE][i].mousePressed(e);
            }
        }  else if (game.gameState == GameStates.pause) {
            for (int i = 0; i < buttons[PAUSE_STATE].length; i++) {
                if (buttons[PAUSE_STATE][i] != null)
                    buttons[PAUSE_STATE][i].mousePressed(e);
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < buttons[DEATH_S_STATE].length; i++) {
                if (buttons[DEATH_S_STATE][i] != null)
                    buttons[DEATH_S_STATE][i].mousePressed(e);
            }
        }
    }

    public void mouseRelased(MouseEvent e) {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < buttons[MENU_STATE].length; i++) {
                if (buttons[MENU_STATE][i] != null)
                    buttons[MENU_STATE][i].mouseRelased(e);
            }
        } else if (game.gameState == GameStates.options) {
            for (int i = 0; i < buttons[OPTIONS_STATE].length; i++) {
                if (buttons[OPTIONS_STATE][i] != null)
                    buttons[OPTIONS_STATE][i].mouseRelased(e);
            }
        }  else if (game.gameState == GameStates.pause) {
            for (int i = 0; i < buttons[PAUSE_STATE].length; i++) {
                if (buttons[PAUSE_STATE][i] != null)
                    buttons[PAUSE_STATE][i].mouseRelased(e);
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < buttons[DEATH_S_STATE].length; i++) {
                if (buttons[DEATH_S_STATE][i] != null)
                    buttons[DEATH_S_STATE][i].mouseRelased(e);
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (game.gameState == GameStates.options) {
            for (int i = 0; i < buttons[OPTIONS_STATE].length; i++) {
                if (buttons[OPTIONS_STATE][i] != null)
                    buttons[OPTIONS_STATE][i].mouseDragged(e);
            }
        } else if (game.gameState == GameStates.pause) {
            for (int i = 0; i < buttons[PAUSE_STATE].length; i++) {
                if (buttons[PAUSE_STATE][i] != null)
                    buttons[PAUSE_STATE][i].mouseDragged(e);
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (game.gameState == GameStates.menu) {
            for (int i = 0; i < buttons[MENU_STATE].length; i++) {
                if (buttons[MENU_STATE][i] != null)
                    buttons[MENU_STATE][i].mouseMoved(e);
            }
        } else if (game.gameState == GameStates.options) {
            for (int i = 0; i < buttons[OPTIONS_STATE].length; i++) {
                if (buttons[OPTIONS_STATE][i] != null)
                    buttons[OPTIONS_STATE][i].mouseMoved(e);
            }
        }  else if (game.gameState == GameStates.pause) {
            for (int i = 0; i < buttons[PAUSE_STATE].length; i++) {
                if (buttons[PAUSE_STATE][i] != null)
                    buttons[PAUSE_STATE][i].mouseMoved(e);
            }
        } else if (game.gameState == GameStates.deathScreen) {
            for (int i = 0; i < buttons[DEATH_S_STATE].length; i++) {
                if (buttons[DEATH_S_STATE][i] != null)
                    buttons[DEATH_S_STATE][i].mouseMoved(e);
            }
        }
    }
}
