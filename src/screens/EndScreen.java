package screens;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * end screen of the game.
 */
public class EndScreen implements Animation {
    private int score;
    private boolean winner;

    /**
     * @param scoreIs score of game.
     * @param win if won
     */
    public EndScreen(int scoreIs, boolean win) {

        this.score = scoreIs;
        this.winner = win;
    }

    /**
     *
     * @param d draws one frame of the game on this surface.
     * @param dt frame rate
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.winner) {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(new Color((int) (0.235 * 0x1000000)));
            d.drawText(200, d.getHeight() / 4, "You Win!", 62);
            d.setColor(new Color((int) (0.541 * 0x1000000)));
            d.drawText(100, d.getHeight() / 4 + 150, "Your Score Is: ", 60);
            d.setColor(new Color((int) (0.246 * 0x1000000)));
            d.drawText(530, d.getHeight() / 4 + 150, score + "", 80);


        } else {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(new Color((int) (0.5 * 0x1000000)));
            d.drawText(200, d.getHeight() / 4, "Game Over", 62);
            d.setColor(new Color((int) (0.54 * 0x1000000)));
            d.drawText(100, d.getHeight() / 4 + 150, "Your Score Is: ", 60);
            d.setColor(new Color((int) (0.65 * 0x1000000)));
            d.drawText(530, d.getHeight() / 4 + 150, score + "", 80);

        }
    }

    /**
     *
     * @return when it should end the game
     */
    public boolean shouldStop() { return true; }
}
