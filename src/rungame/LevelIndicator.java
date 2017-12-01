package rungame;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * name of levels.
 */
public class LevelIndicator implements Sprite {
    private String levelname;

    /**
     *
     * @param name of game.
     */
    public LevelIndicator(String name) {
        this.levelname = name;
    }

    /**
     * draw the sprite to the screen.
     * @param d surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(450, 13, "Level: " + this.levelname, 15);

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

