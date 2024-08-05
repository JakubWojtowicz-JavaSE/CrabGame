package pl.JakubWojtowicz.Utilz;

import pl.JakubWojtowicz.Entity.Entity;
import pl.JakubWojtowicz.Main.Game;

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
}
