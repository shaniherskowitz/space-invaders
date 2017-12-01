package rungame;

import interfaces.Collidable;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**.
 * sets environment where the game takes place
 */
public class GameEnvironment {

    private List<Collidable> environment;
    private int multi;

    /**.
     * sets environment where the game takes place.
     */
    public GameEnvironment() {
        this.environment = new ArrayList<>();
        this.multi = 0;
    }

    // add the given collidable to the environment.
    /**
     * @param c adds collidable c to environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.add(c);

    }
    /**
     * @return number of hits in corner.
     */
    public int getMulti() {
        return this.multi;
    }
    /**
     * rests hits to zero.
     */
    public void resetMulti() {
        this.multi = 0;
    }
    /**
     * @return list of collidables in enviroment
     */
    public List<Collidable> getEnvironment() {
        return this.environment;
    }


    /**
     * @param trajectory of the ball
     * @return info on clossest collison
     *   Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {

        int count = 0;
        int index = 0;
        List<Integer> indexs = new ArrayList<>();
        List<Collidable> copy = new ArrayList<>(this.environment);
        Point roundPoint = new Point(0, 0);

        for (int i = 0; i < copy.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(copy.get(i).getCollisionRectangle()) != null) {

                roundPoint = new Point(round(trajectory.
                        closestIntersectionToStartOfLine(copy.get(i).getCollisionRectangle()).getX(),
                        5),
                        round(trajectory.closestIntersectionToStartOfLine(copy.
                                get(i).getCollisionRectangle()).getY(), 5));
                count++;
                index = i;
                indexs.add(i);

            }

        }


        if (count == 0) {
            return null;
        } else if (count == 1) {
            return new CollisionInfo(roundPoint,
                    copy.get(index));
        }

        if (count == 2 && checkTwo(indexs.get(0), indexs.get(1))) {
            multi = 1;
                return new CollisionInfo(roundPoint,
                        copy.get(index));

        }


        multi = count;
        return new CollisionInfo(roundPoint,
                copy.get(index));
    }
    /**
     * @param one first block
     * @param two second block
     * @return if two blocks are next to each other
     */
    private boolean checkTwo(int one, int two) {
        Rectangle rect1 = this.environment.get(one).getCollisionRectangle();
        Rectangle rect2 = this.environment.get(two).getCollisionRectangle();

        if (rect1.right().equals(rect2.left())) {
            return true;
        } else if (rect1.left().equals(rect2.right())) {
            return true;
        }
        return false;
    }


    /**
     * @param value to round
     * @param places to round to
     * @return list of collidables in environment
     */
    private static double round(double value, int places) {

        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     *
     * @param item to remove.
     */
    public void remove(Collidable item) {
        this.environment.remove(item);
    }

}