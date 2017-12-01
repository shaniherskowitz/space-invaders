package rungame;

import interfaces.Collidable;
import shapes.Point;

/**
 * gives info on collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable object;

    /**.
     * @param c point of collision
     * @param o object of collision
     *
     */
    public CollisionInfo(Point c, Collidable o) {
        this.collisionPoint = c;
        this.object = o;
    }

    /**
     *@return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}