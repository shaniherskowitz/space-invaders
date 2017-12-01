package rungame;

import interfaces.LevelInformation;
import shapes.Point;
import sprites.Block;
import java.awt.Color;
import java.util.ArrayList;

/**
 * creates all blocks and borders for level.
 */
public class InitializeLevel {
    private LevelInformation level;
    private ArrayList<Block> aliens;
    private Counter count;

    /**
     *
     * @param level to show.
     * @param aliens for aliens
     * @param count blocks
     */
    public InitializeLevel(LevelInformation level, ArrayList<Block> aliens, Counter count) {
        this.level = level;
        this.aliens = aliens;
        this.count = count;
    }

    /**
     *
     * @param ballRemover to remove the balls.
     * @param gameLevel to init
     * @param score of game
     * @param numberOfLives for game.
     */
    public void initialize(BallRemover ballRemover, GameLevel gameLevel,
                           Counter score, Counter numberOfLives) {

        BlockRemover br = new BlockRemover(gameLevel, count);
        ScoreTrackingListener keepScore = new ScoreTrackingListener(score);
        ScoreIndicator scoreI = new ScoreIndicator(score);
        LivesIndicator livesI = new LivesIndicator(numberOfLives);
        LevelIndicator levelsI = new LevelIndicator(level.levelName());

        Block block2 = new Block(new Point(0, 600), 800, 10);
        block2.addHitListener(ballRemover);
        Block block1 = new Block(new Point(790, 0), 10, 600);
        Block block3 = new Block(new Point(0, 0), 10, 600);
        Block block4 = new Block(new Point(0, 0), 800, 15);
        block4.addHitListener(ballRemover);

        block1.colorBorder(Color.WHITE);
        block2.colorBorder(Color.WHITE);
        block3.colorBorder(Color.WHITE);
        block4.colorBorder(Color.WHITE);



        block1.getCollisionRectangle().updateNumHits(-1);
        block2.getCollisionRectangle().updateNumHits(-1);
        block3.getCollisionRectangle().updateNumHits(-1);
        block4.getCollisionRectangle().updateNumHits(-1);

        gameLevel.addSprite(this.level.getBackground());

        block1.addToGame(gameLevel);
        block2.addToGame(gameLevel);
        block3.addToGame(gameLevel);
        block4.addToGame(gameLevel);
        gameLevel.addSprite(scoreI);
        gameLevel.addSprite(livesI);
        gameLevel.addSprite(levelsI);


        for (int i = 0; i < this.level.blocks().size(); i++) {
            Block block = this.level.blocks().get(i);
            block.addToGame(gameLevel);
            block.addHitListener(br);
            block.addHitListener(ballRemover);


        }
        for (int i = 0; i < this.aliens.size(); i++) {
            Block block = this.aliens.get(i);
            block.addToGame(gameLevel);
            this.count.increase(1);
            block.addHitListener(br);
            block.addHitListener(ballRemover);
            block.addHitListener(keepScore);

        }
    }
}
