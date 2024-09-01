package Main;

import java.awt.event.*;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener {

    private Game game;
    public boolean leftPressed, rightPressed, debbugging;

    public Listeners(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = true;
        else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = true;
        else if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_SPACE)
            game.player.setAttacking(true);
        else if (code == KeyEvent.VK_ESCAPE) {
            if (game.getGameState() == GameStates.playing)
                game.changeGameState(GameStates.pause);
            else if (game.getGameState() == GameStates.pause)
                game.changeGameState(GameStates.playing);
            else if (game.getGameState() == GameStates.options)
                game.changeGameState(GameStates.menu);
        } else if (/*code == KeyEvent.VK_B || */code == KeyEvent.VK_MULTIPLY) {
            debbugging = !debbugging;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = false;
        else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = false;
        else if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_SPACE)
            game.player.setAttacking(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        game.ui.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        game.ui.mouseRelased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        game.ui.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        game.ui.mouseMoved(e);
    }
}
