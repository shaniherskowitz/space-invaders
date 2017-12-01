package rungame;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * keeps track pf scores.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     *
     * @param scoreCounter counts score of game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * updates score based on hits.
     * @param beingHit lets object know that its being hit
     * @param hitter  The the sprites.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        if (beingHit.getRect().getnumHits() <= 1) {
            currentScore.increase(100);
        }


    }
}
