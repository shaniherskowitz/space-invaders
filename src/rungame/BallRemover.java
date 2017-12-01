package rungame;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;


    /**
     *
     * @param game removing blocks from the game.
     * @param removedBalls the number of removed blocks
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;

    }


    /**
     *
     * @param beingHit lets object know that its being hit.
     * @param hitter  The the sprites.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);

    }

    /**
     *
     * @param num updates num of balls.
     */
    public void updateBalls(int num) {
        this.remainingBalls.increase(num);
    }
}
