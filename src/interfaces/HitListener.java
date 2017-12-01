package interfaces;

import sprites.Ball;
import sprites.Block;

/**
 * This method is called whenever the beingHit object is hit.
 * The hitter parameter is the sprites.Ball that's doing the hitting
 */
public interface HitListener {
    /**
     *
     * @param beingHit lets object know that its being hit
     * @param hitter  The the sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
