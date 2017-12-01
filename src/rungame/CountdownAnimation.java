package rungame;

import biuoop.DrawSurface;
import interfaces.Animation;
import sprites.SpriteCollection;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * counts down before each level of the game.
 */
public class CountdownAnimation implements Animation {

    private double seconds;
    private int count;
    private SpriteCollection game;
    private int countdown;
    private double wait;
    /**
     *
     * @param numOfSeconds how many seconds to wait.
     * @param countFrom from what number to count from
     * @param gameScreen screen to print message on
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.count = countFrom;
        this.countdown = countFrom + 1;
        this.game = gameScreen;
        this.wait = (this.seconds * 1000) / this.countdown;
    }

    /**
     *
     * @param d draws one frame of the game on this surface.
     * @param dt frame rate
     */
    public void doOneFrame(DrawSurface d, double dt) {

        this.game.drawAllOn(d);

        Sleeper sleeper = new Sleeper();
        if (this.count >= -1) {
            if (count >= 0) {
                d.setColor(new Color(206, 219, 239));
                d.drawText(d.getWidth() / 2 - 50, d.getHeight() / 2, count + "", 100);
            }
            sleeper.sleepFor((long) (this.seconds * 1000) / this.countdown);
            count--;
        } else {
            seconds = -1;

        }

    }

    /**
     *
     * @return when should stop.
     */
    public boolean shouldStop() {
        return seconds < 0;
    }
}
