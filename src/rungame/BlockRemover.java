package rungame;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

import java.awt.Color;


/**
 *  in charge of removing blocks from the game.
 *  as well as keeping count
 * of the number of blocks that remain.
 */

public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;


    /**
     *
     * @param game removing blocks from the game.
     * @param removedBlocks the number of removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;

    }

    /**
     *
     * @param beingHit lets object know that its being hit.
     * @param hitter  The the sprites.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        if (beingHit.getRect().getnumHits() <= 1) {
            if (!(hitter.getColor() == Color.RED && beingHit.getImage() != null)) {
                beingHit.removeFromGame(game);
                beingHit.setWasRemoved();
                if (beingHit.getImage() != null) {
                    remainingBlocks.decrease(1);
                }
            }
            beingHit.removeHitListener(beingHit);


        }
    }
}
