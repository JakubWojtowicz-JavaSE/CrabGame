package Main;

import Entity.Entity;

public class CollisionChecker {

    private Game game;

    public CollisionChecker(Game game) {
        this.game = game;
    }

    public boolean checkEnemy(Entity entity) {
        boolean collision = false;

        for (int i = 0; i < game.eSpawner.enemies.size(); i++) {
            if (game.eSpawner.enemies.get(i) != null) {
                if (game.eSpawner.enemies.get(i).getCollider().intersects(entity.getCollider()))
                    collision = true;
            }
        }
        return collision;
    }

    public boolean checkPlayer(Entity entity) {
        return game.player.getCollider().intersects(entity.getCollider());
    }
}
