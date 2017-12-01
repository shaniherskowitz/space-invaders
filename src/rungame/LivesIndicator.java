package rungame;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * indicates number of lives.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     *
     * @param count score of game.
     */
    public LivesIndicator(Counter count) {
        this.lives = count;
    }

    /**
     * draw the sprite to the screen.
     * @param d surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(340, 13, "Lives:" + this.lives.getValue(), 15);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt frame rate
     */
    public void timePassed(double dt) {

    }

    /**
     * adds item to game.
     * @param g game to add it to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
