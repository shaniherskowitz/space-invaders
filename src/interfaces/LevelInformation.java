package interfaces;

import java.util.List;
import sprites.Block;
/**
 * info on what you need to bulid a level.
 */
public interface LevelInformation {


    /**
     *
     * @return speed of paddle.
     */
    int paddleSpeed();

    /**
     *
     * @return width of paddle.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return name of level
     */
    String levelName();

    /**
     *
     * @return  a sprite with the background of the level
     */
    Sprite getBackground();


    /**
     *
     * @return a list of Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     *
     * @return moving block to remove from game.
     */
    List<Block> aliens();

    /**
     *
     * @return speed of aliens.
     */
    double aliensSpeed();

    /**
     *
     * @param num increase speed by num
     */
    void setSpeed(double num);

    /**
     *
     * @return the new speed.
     */
    double speedToChange();


}
