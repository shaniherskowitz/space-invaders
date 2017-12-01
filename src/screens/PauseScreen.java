package screens;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * pauses the screen of the game.
 */
public class PauseScreen implements Animation {


    /**
     *
     * constoctor for the screen.
     */
    public PauseScreen() {

    }

    /**
     *
     * @param d draws one frame of the game on this surface.
     * @param dt does nothing
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color((int) (0.33 * 0x1000000)));
        d.drawText(260, d.getHeight() / 4, "paused", 62);
        d.setColor(new Color((int) (0.54 * 0x1000000)));
        d.drawText(340, d.getHeight() / 4 + 50, " -- ", 32);
        d.setColor(new Color((int) (0.275 * 0x1000000)));
        d.drawText(90, d.getHeight() / 4 + 100, "press space to continue", 50);

    }

    /**
     *
     * @return when it should pause the game
     */
    public boolean shouldStop() { return true; }
}
