package Entity;

import Main.Game;

import static Utilz.Constants.MoneyDetails.*;

public class Money extends Entity {

    public Money(Game game, int xPos, int yPos, float speed) {
        super(game, Type.money, xPos, yPos, MONEY_WIDTH, MONEY_HEIGHT, speed, 1, RUNNING);
    }
}
