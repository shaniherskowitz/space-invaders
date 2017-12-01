package interfaces;

import sprites.Block;

/**
 * interface of a factory-object that is used for creating blocks.
 */
public interface BlockCreator {

    /**
     *
     * @param xpos x place.
     * @param ypos y place
     * @return Create a block at the specified location.
     */
    Block create(int xpos, int ypos);
}
