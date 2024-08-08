package Utilz;

import Main.Game;
import Entity.Entity;

public class Constants {

    public static int GetHowMSprInRow(String name, int state) {
        switch (name) {
            case "Crabby" -> {
                switch (state) {
                    case Entity.IDLE -> {
                        return CrabbyDetails.SPRITES_IN_IDLE;
                    }
                    case Entity.RUNNING -> {
                        return CrabbyDetails.SPRITES_IN_RUNNING;
                    }
                    case Entity.ATTACK -> {
                        return CrabbyDetails.SPRITES_IN_ATTACK;
                    }
                    case Entity.HIT -> {
                        return CrabbyDetails.SPRITES_IN_HIT;
                    }
                    case Entity.DEAD -> {
                        return CrabbyDetails.SPRITES_IN_DEAD;
                    }
                }
            }
            case "Ball" -> {return 1;}
            case "Potion" -> {return PotionDetails.SPRITES_IN_COL;}
        }
        return 0;
    }

    public static class CrabbyDetails {
        public static final int CRABBY_DEFAULT_WIDTH = 72;
        public static final int CRABBY_DEFAULT_HEIGHT = 32;

        public static final int CRABBY_WIDTH = (int) (CRABBY_DEFAULT_WIDTH * Game.SCALE);
        public static final int CRABBY_HEIGHT = (int) (CRABBY_DEFAULT_HEIGHT * Game.SCALE);

        public static final int SPRITES_IN_ROW = 5;
        public static final int SPRITES_IN_COL = 9;

        public static final int SPRITES_IN_IDLE = 9;
        public static final int SPRITES_IN_RUNNING = 6;
        public static final int SPRITES_IN_ATTACK = 7;
        public static final int SPRITES_IN_HIT = 4;
        public static final int SPRITES_IN_DEAD = 5;
    }

    public static class BallDetails {
        public static final int BALL_DEFAULT_WIDTH = 32;
        public static final int BALL_DEFAULT_HEIGHT = 32;

        public static final int BALL_WIDTH = (int) (BALL_DEFAULT_WIDTH * Game.SCALE);
        public static final int BALL_HEIGHT = (int) (BALL_DEFAULT_HEIGHT * Game.SCALE);

        public static final int SPRITES_IN_ROW = 1;
        public static final int SPRITES_IN_COL = 1;


    }

    public static class PotionDetails {
        public static final int POTION_DEFAULT_WIDTH = 12;
        public static final int POTION_DEFAULT_HEIGHT = 16;

        public static final int POTION_WIDTH = (int) (POTION_DEFAULT_WIDTH * 2 * Game.SCALE);
        public static final int POTION_HEIGHT = (int) (POTION_DEFAULT_HEIGHT * 2 * Game.SCALE);

        public static final int SPRITES_IN_COL = 7;
    }

    public static class ButtonsDetails {
        public static final int BUTTON_DEFAULT_WIDTH = 140;
        public static final int BUTTON_DEFAULT_HEIGHT = 56;

        public static final int BUTTON_WIDTH = (int) (BUTTON_DEFAULT_WIDTH/1.2f*Game.SCALE);//116.66
        public static final int BUTTON_HEIGHT = (int) (BUTTON_DEFAULT_HEIGHT/1.2f*Game.SCALE);//46.66
    }

    public static class VolumeSliderDetails {
        public static final int VOLUME_SLIDER_DEFAULT_WIDTH = 28;
        public static final int VOLUME_SLIDER_DEFAULT_HEIGHT = 44;
        public static final int VOLUME_SLIDER_BG_DEFAULT_WIDTH = 215;
        public static final int VOLUME_SLIDER_BG_DEFAULT_HEIGHT = 44;

        public static final int VOLUME_SLIDER_WIDTH = (int) (VOLUME_SLIDER_DEFAULT_WIDTH/1.5f*Game.SCALE);
        public static final int VOLUME_SLIDER_HEIGHT = (int) (VOLUME_SLIDER_DEFAULT_HEIGHT/1.5f*Game.SCALE);
        public static final int VOLUME_SLIDER_BG_WIDTH = (int) (VOLUME_SLIDER_BG_DEFAULT_WIDTH/1.5f*Game.SCALE);
        public static final int VOLUME_SLIDER_BG_HEIGHT = (int) (VOLUME_SLIDER_BG_DEFAULT_HEIGHT/1.5f*Game.SCALE);
    }

    public static class DeathScreenDetails {
        public static final int DEATH_S_DEFAULT_WIDTH = 235;
        public static final int DEATH_S_DEFAULT_HEIGHT = 255;

        public static final int DEATH_S_WIDTH = (int) (DEATH_S_DEFAULT_WIDTH/1.2f*Game.SCALE);
        public static final int DEATH_S_HEIGHT = (int) (DEATH_S_DEFAULT_HEIGHT/1.2f*Game.SCALE);

        public static final int DEATH_S_RETRY_B_WIDTH = (int) (75/1.2f*Game.SCALE);
        public static final int DEATH_S_RETRY_B_HEIGHT = (int) (40f/1.2f*Game.SCALE);
        public static final int DEATH_S_RAGE_Q_B_WIDTH = (int) (59f/1.2f*Game.SCALE);
        public static final int DEATH_S_RAGE_Q_B_HEIGHT = (int) (40f/1.2f*Game.SCALE);
    }
}
