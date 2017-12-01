package interfaces;

import biuoop.DrawSurface;
import rungame.GameLevel;
/**
 * keeps hold of all things that can be drawn on the screen.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     * @param dt frame rate
     */
    void timePassed(double dt);

    /**
     * adds item to game.
     * @param g game to add it to
     */
    void addToGame(GameLevel g);
}
