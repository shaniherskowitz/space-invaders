package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Collidable;
import interfaces.Sprite;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import rungame.GameLevel;

import java.awt.Color;

/**
 * paddle that moves left and right, is collidable and can be drawn on the board.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int leftBound;
    private int rightBound;
    private double speed;
    private Ball hit;

    /**
     * @param p makes block into paddle
     * @param k keyboard for movement of paddle
     * @param leftBound checks that the paddle stays in bounds from left
     * @param rightBound checks that the paddle stays in bounds from right
     * @param speed of paddle
     */
    public Paddle(Rectangle p, biuoop.KeyboardSensor k, int leftBound, int rightBound, double speed) {
        this.keyboard = k;
        this.paddle = p;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.speed = speed;
        this.hit = null;
    }

    /**
     * moves paddle to the left.
     * @param dt changes based on frames
     */
    public void moveLeft(double dt) {
        if (this.paddle.getUpperL().getX() - (this.speed * dt) >= this.leftBound) {

            this.paddle.getUpperL().moveLeft(this.speed * dt);

        } else if (this.paddle.getUpperL().getX() >= this.leftBound) {

            this.paddle.getUpperL().moveLeft(((this.paddle.getUpperL().getX()) - this.leftBound));
        }
            this.paddle = new Rectangle(this.paddle.getUpperL(), this.paddle.getWidth(), this.paddle.getHeight());



    }
    /**
     * moves paddle to the right.
     * @param dt changes based on frames
     */
    public void moveRight(double dt) {
        if (this.paddle.getUpperL().getX() + (this.speed * dt) <= this.rightBound) {

            this.paddle.getUpperL().moveRight(this.speed * dt);

        } else  if (this.paddle.getUpperL().getX()  <= this.rightBound) {

            this.paddle.getUpperL().moveRight((this.rightBound - this.paddle.getUpperL().getX()));
        }
            this.paddle = new Rectangle(this.paddle.getUpperL(), this.paddle.getWidth(), this.paddle.getHeight());

    }

    /**
     * based on user pressing keyboard moves paddle.
     * @param dt frame rate
     */
    public void timePassed(double dt) {

        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);

        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }
    /**
     * @param d draws paddle on surface d.
     */
    public void drawOn(DrawSurface d) {

        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperL().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.WHITE);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperL().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());

    }


    /**
     * @return rectangle of collision.
     */
    public Rectangle getCollisionRectangle() {
       return this.paddle;
    }
    /**
     * @param collisionPoint checks where collision accord
     * @param currentVelocity changes velocity accordingly
     * @param hitter ball hitting the paddle
     * @return changed velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {


        if (this.paddle.top().pointOnLine(collisionPoint)) {
            this.hit = hitter;
            return new Velocity(0, 0);
        }
        return currentVelocity;


    }

    /**
     * sets back to not hit.
     * @return if was hit
     */
    public Ball getWasHit() {
        return hit;
    }
    /**
     * adds paddle to the game.
     * @param g game to add paddle to
     */
    public void addToGame(GameLevel g) {

        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     *
     * @return the width of paddle
     */
    public double getWidth() {
        return this.paddle.getWidth();
    }

    /**
     *
     * @return paddle.
     */
    public Rectangle getPaddle() {
        return paddle;
    }

    /**
     *
     * @return upper left point of paddle.
     */
    public Point upperLeft() {
        return paddle.top().start();
    }
}
