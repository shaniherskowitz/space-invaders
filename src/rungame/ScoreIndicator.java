package rungame;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * displays score.
 */
public class ScoreIndicator implements Sprite {
    private Counter scores;

    /**
     *
     * @param count score of game.
     */
    public ScoreIndicator(Counter count) {
        this.scores = count;
    }

    /**
     * draw the sprite to the screen.
     * @param d surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(200, 13, "Score:" + this.scores.getValue(), 15);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt frameRate
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
