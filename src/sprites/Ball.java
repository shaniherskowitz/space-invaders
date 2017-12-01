package sprites;

import biuoop.DrawSurface;
import interfaces.HitListener;
import interfaces.Sprite;
import shapes.Line;
import shapes.Point;
import rungame.GameEnvironment;
import shapes.Velocity;
import rungame.GameLevel;

import java.awt.Color;

/**.
 * ball has middle point, radius of circle, color, and velocity that controls
 * direction and speed
 */
public class Ball implements Sprite, HitListener {
    // constructor
    private int radius;
    private double x;
    private double y;
    private Point p;
    private Velocity v;
    private Color color1;
    private GameEnvironment game;

    /**.
     * @param  r radius
     * @param  color of ball
     * @param  x value of middle point
     * @param  y value of middle point
     */
    public Ball(int x, int y, int r, Color color) {
        this.x = x;
        this.y = y;
        this.radius = r;
        this.color1 = color;
        this.p = new Point(x, y);
        this.game = new GameEnvironment();

    }
    /**.
     * @param  x value of middle point
     * @param  y value of middle point
     * @param  r radius of ball
     * @param  color of ball
     * @param g game environment ball is in
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment g) {
        this.x = x;
        this.y = y;
        this.radius = r;
        this.color1 = color;
        this.v = new Velocity(0, 0);
        this.p = new Point(x, y);
        this.game = g;

    }

    // accessors
    /**.
     *
     * @return radius of ball
     *
     */
    public int getRadius() {
        return  this.radius;

    }
    /**.
     * @return x value of middle point
     */
    public int getX() {
        return  (int) this.x;

    }
    /**.
     *
     * @return y value of middle point
     *
     */

    public int getY() {
        return (int) this.y;

    }
    /**
     *
     * @return size of the ball
     */
    public int getSize() {
        return (int) 3.14 * this.radius * this.radius;

    }
    /**
     * @return color of ball
     */
    public Color getColor() {
        return this.color1;

    }

    /**.
     * draws ball on this surface
     * @param  surface to draw ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color1);
        surface.fillCircle((int) this.p.getX(), (int) this.p.getY(), this.radius);
        surface.setColor(this.color1);
        surface.drawCircle((int) this.p.getX(), (int) this.p.getY(), this.radius);

    }
    /**
     * @param  v1 velocity of ball
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;

    }
    /**.
     * calculates velocity
     * @param  dx dx value to calculate velocity
     * @param  dy dy value to calculate velocity
     */
    public void setVelocity(double dx, double dy) {

        this.v = new Velocity(dx, dy);
    }
    /**
     * @return velocity of ball
     */
    public Velocity getVelocity() {

        return this.v;

    }
    /**.
     * checks the boundary of surface the ball is in to make sure
     * it bounces in bounds
     *  @param  minX min x boundary of given surface
     *  @param  minY min y boundary of given surface
     *  @param  maxX max x boundary of given surface
     *  @param  maxY max y boundary of given surface
     */
    public void checkBound(int minX, int minY, int maxX, int maxY) {

        if (this.p.getX() > maxX && this.v.getX() > 0) {
            this.setVelocity(-this.v.getX(), this.v.getY());
        }
        if (this.p.getY() > maxY && this.v.getY() > 0) {
            this.setVelocity(this.v.getX(), -this.v.getY());
        }
        if (this.p.getX() < minX) {
            this.setVelocity(-this.v.getX(), this.v.getY());
        }
        if (this.p.getY() < minY) {
            this.setVelocity(this.v.getX(), -this.v.getY());
        }

    }
    /**.
     * @return center point of ball
     */
    public Point getPoint() {
        return this.p;
    }
    /**.
     * gets game environment
     * @return the game environment that the ball is in
     */
    public GameEnvironment getGame() {
        return this.game;
    }
    /**.
     * moves the ball one step based on its velocity
     */
    public void moveOneStep() {


        Line trajectory = new Line(this.p.getX(), this.p.getY(),
                this.p.getX() + this.getVelocity().getX(),
                this.p.getY() + this.getVelocity().getY());

        double c = 0;
        double d = 0;

        if (this.game.getClosestCollision(trajectory) == null) {
            this.p = this.getVelocity().applyToPoint(this.p);
            return;
        }

        Point colPoint =  this.game.getClosestCollision(trajectory).collisionPoint();

       if (this.game.getMulti() == 1) {
            this.game.getClosestCollision(trajectory).collisionObject().hit(this,
                    colPoint, this.getVelocity());
            this.game.resetMulti();
            this.v = new Velocity(this.v.getX(), -this.v.getY());

        } else if (this.game.getMulti() != 0) {
            this.game.getClosestCollision(trajectory).collisionObject().hit(this, colPoint,
                    this.getVelocity());
            this.game.resetMulti();
            this.v = new Velocity(-this.v.getX(), -this.v.getY());

        } else {
           this.v = this.game.getClosestCollision(trajectory).collisionObject().hit(this, colPoint,
                   this.getVelocity());

           if (this.v.getX() > 0) {
               c = 0.0001;

           } else if (this.v.getX() < 0) {
               c = -0.0001;
           }
           if (this.v.getY() > 0) {
               d = 0.0001;

           } else if (this.v.getY() < 0) {
               d = -0.0001;
           }

           this.p = new Point(colPoint.getX() + c, colPoint.getY() + d);
       }



    }
    /**.
     * moves the ball one step based on time passed in game
     * @param dt frame rate
     */
    public void timePassed(double dt) { //not sure
        this.moveOneStep();

    }

    /**
     * @param g adds ball to the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }

    /**
     *
     * @param beingHit lets object know that its being hit
     * @param hitter  The the sprites.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
         beingHit.addHitListener(hitter);
    }

    /**
     *
     * @param g removes ball from game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }


}
