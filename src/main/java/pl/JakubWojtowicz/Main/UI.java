package pl.JakubWojtowicz.Main;

import pl.JakubWojtowicz.Utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    private Game game;
    private Font font;
    private Image health_bar;

    public UI(Game game) {
        this.game = game;

        setFonts();
        setImgs();
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
        health_bar = LoadSave.GetSpriteAtlas(LoadSave.HEALTH_BAR_IMG);
    }

    private int hBarX = (int) (3*Game.SCALE);
    private int hBarY = (int) (5*Game.SCALE);

    public void draw(Graphics g) {
        g.setFont(font.deriveFont(17*Game.SCALE));
        if (game.gameState == GameStates.menu) {

        } else if (game.gameState == GameStates.playing) {
            drawHealthBar(g);
            drawScore(g);
        } else if (game.gameState == GameStates.pause) {

        } else if (game.gameState == GameStates.deathScreen) {

        }
    }

    private int fullHealth;
    private int toFill;
    private void drawHealthBar(Graphics g) {
        g.drawImage(health_bar, hBarX, hBarY, (int) (144*Game.SCALE), (int) (43.5f*Game.SCALE), null);

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
