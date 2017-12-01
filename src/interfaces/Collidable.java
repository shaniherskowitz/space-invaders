package interfaces;

import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import sprites.Ball;

/**
 * colliadable interface for thigs that ball can coliide with.
 */
public interface Collidable {
    // Return the "collision shape" of the object.
    /**
     * gets rectangle.
     * @return rectangle of collison
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**
     * @param collisionPoint point of collision.
     * @param currentVelocity velocity when collides
     * @param hitter ball thats hitting
     * @return new velocity after hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
