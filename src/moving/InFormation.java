package moving;

import sprites.Block;

import java.util.List;

/**
 * moves group in formation.
 */
public class InFormation {
    private List<Block> blocks;
    private double speed;
    private int rightBound;
    private int leftBound;
    private int lowerBound;

    /**
     *
     * @param blockList to move in formation.
     * @param speed1 speed of move
     * @param leftBound1 of movement
     * @param rightBound1 of movement
     * @param lowerBound1 of movement
     */
    public InFormation(List<Block> blockList, double speed1, int leftBound1, int rightBound1, int lowerBound1) {
        this.blocks = blockList;
        this.speed = speed1;
        this.leftBound = leftBound1;
        this.rightBound = rightBound1;
        this.lowerBound = lowerBound1;
    }

    /**
     * moves all blocks in list left.
     * @return if hit bound
     */
    public boolean moveLeft() {
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).getRect().getUpperL().getX() + speed < leftBound) {
                return false;
            }
        }
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).getRect().moveLeft(speed);
        }
        return true;
    }

    /**
     * moves all blocks in list right.
     * @return if hit right bound
     */
    public boolean moveRight() {
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).getRect().getUpperR().getX() + speed > rightBound) {
                return false;
            }
        }
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).getRect().moveRight(speed);
        }
        return true;
    }

    /**
     * moves all blocks in list right.
     * @return if hit right bound
     */
    public boolean moveDown() {
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).getRect().getLowerL().getY() + 30 > lowerBound) {
                return false;
            }
        }
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).getRect().moveDown(20);

        }
        return true;
    }

    /**
     *
     * @return if hit the side.
     */
    public boolean hitSide() {
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).getRect().getUpperL().getX() + speed <= leftBound
                    || blocks.get(i).getRect().getUpperR().getX() + speed >= rightBound) {
                return true;
            }
        }
        return false;
    }

    /**
     * reset aliens to original place.
     */
    public void reset() {
        double temp = speed;
        boolean isLeft = false;
        boolean isRight = false;
        int leftMostBlock = 0;
        int change = 0;
        if (blocks.size() > 0) {
            for (int j = 0; j < blocks.size(); j++) {
                if (blocks.get(j).getX1() < blocks.get(leftMostBlock).getX1()) {
                    leftMostBlock = j;
                }
            }
                if (blocks.get(leftMostBlock).getX1() <= leftBound) {
                    isLeft = true;
                    change = leftBound - blocks.get(leftMostBlock).getX1();
                }
                if (blocks.get(leftMostBlock).getX1() > leftBound) {
                    isRight = true;
                    change = blocks.get(leftMostBlock).getX1() - leftBound;
                }


            for (int i = 0; i < blocks.size(); i++) {
                if (isLeft) {
                    blocks.get(i).getRect().moveRight(change);
                }
                if (isRight) {
                    blocks.get(i).getRect().moveLeft(change);
                }

                //speed = blocks.get(i).getRect().getCountRight();

                blocks.get(i).getRect().moveUp(blocks.get(i).getRect().getCountDown());
            }


            speed = temp;
        }
    }



}
