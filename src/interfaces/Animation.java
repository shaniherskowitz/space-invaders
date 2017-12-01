package interfaces;

import biuoop.DrawSurface;

/**
 * animates the game.
 */
public interface Animation {
    /**
     *
     * @param d draws one frame of the game on this surface.
     * @param dt frame rate
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     *
     * @return when to stop the animation.
     */
    boolean shouldStop();
}
