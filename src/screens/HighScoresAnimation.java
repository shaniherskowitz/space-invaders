package screens;

import biuoop.DrawSurface;
import interfaces.Animation;
import score.HighScoresTable;

import java.awt.Color;

/**
 * animation for high scores.
 */
public class HighScoresAnimation implements Animation {


    private HighScoresTable scoresTable;

    /**
     *
     * @param scores displays high scores.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scoresTable = scores;
    }
    /**
     *
     * @param d draws one frame of the game on this surface.
     * @param dt frame rate
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(new Color(239, 158, 222));
        d.drawText(100, 100, "High Scores", 50);


        d.setColor(new Color(178, 82, 117));
        int count = 150;
        for (int i = 0; i < scoresTable.size(); i++) {

            d.drawText(100, count, scoresTable.getHighScores().get(i).getName(), 32);
            count += 40;
        }

        d.setColor(new Color(160, 65, 140));
        count = 150;
        for (int i = 0; i < scoresTable.size(); i++) {

            d.drawText(300, count, "" + scoresTable.getHighScores().get(i).getScore(), 32);
            count += 40;
        }


    }

    /**
     *
     * @return when to stop the animation.
     */
    public boolean shouldStop() {
        return true;
    }


}