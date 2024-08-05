package pl.JakubWojtowicz.ui;

import pl.JakubWojtowicz.Main.Game;
import pl.JakubWojtowicz.Main.GameStates;
import pl.JakubWojtowicz.Utilz.Constants;
import pl.JakubWojtowicz.Utilz.LoadSave;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    private Game game;
    private Font font;
    private UrmButton[] buttons;
    private static int PLAY_B = 0;
    private static int OPTIONS_B = 1;
    private static int QUIT_B = 2;
    private Image backgroundImg;
    private Image menuImg;
    private Image healthBar;

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
    }

    private void createButtons() {
        buttons = new UrmButton[3];
        buttons[0] = new PlayButton(game, menuX + (int) (36f*Game.SCALE), menuY + (int) (75*Game.SCALE));
        buttons[1] = new OptionsButton(game, menuX + (int) (36f*Game.SCALE), menuY + Constants.ButtonsDetails.BUTTON_HEIGHT + (int) (85*Game.SCALE));
        buttons[2] = new QuitButton(game, menuX + (int) (36f*Game.SCALE), menuY + 2*Constants.ButtonsDetails.BUTTON_HEIGHT + (int) (95*Game.SCALE));
    }

    public void draw(Graphics g) {
        g.setFont(font.deriveFont(17*Game.SCALE));
        if (game.gameState == GameStates.menu) {
            drawBg(g);
            drawMenu(g);
        } else if (game.gameState == GameStates.playing) {
            drawHealthBar(g);
            drawScore(g);
        } else if (game.gameState == GameStates.pause) {

        } else if (game.gameState == GameStates.deathScreen) {

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

        for (UrmButton button : buttons) {
            button.draw(g);
        }
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

    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(font.deriveFont(25*Game.SCALE));
        g.drawString("SCORE: " + game.player.score, Game.WINDOW_WIDTH/2-(int) (40*Game.SCALE), (int) (80*Game.SCALE));
        g.setColor(Color.WHITE);
        g.setFont(font.deriveFont(25*Game.SCALE));
        g.drawString("SCORE: " + game.player.score, Game.WINDOW_WIDTH/2-(int) (40*Game.SCALE), (int) (84*Game.SCALE));
    }
}
