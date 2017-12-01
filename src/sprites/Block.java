package sprites;

import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import rungame.GameLevel;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * block of the game, is a rectangle that you can collide with and draw on screen.
 */
public class Block implements Collidable, Sprite, HitNotifier, HitListener {
    private Rectangle rect;
    private Velocity hitVelocity;
    private Color color1;
    private Color colorBorder;
    private List<HitListener> hitListeners;
    private double hitPoints;
    private Image image;
    private ArrayList<String> imagelist;
    private ArrayList<Color> colorlist;
    private int counter;
    private boolean border;
    private boolean wasRemoved;


    /**
     * makes block from rectangle.
     * @param rectangle to create block from
     */
    public Block(Rectangle rectangle) {
        this.rect = rectangle;
        this.hitVelocity = new Velocity(0, 0);
        this.color1 = Color.BLACK;
        this.wasRemoved = false;
    }
    /**
     * makes block from point and two double.
     * @param upperLeft corner of block
     * @param width of block
     * @param hight of block
     */
    public Block(Point upperLeft, double width, double hight) {
        this.rect = new Rectangle(upperLeft, width, hight);
        this.hitVelocity = new Velocity(0, 0);
        this.color1 = Color.BLACK;
        this.colorBorder = Color.BLACK;
        this.hitListeners = new ArrayList<>();
        this.hitPoints = 0;
        this.colorlist = new ArrayList<>();
        this.imagelist = new ArrayList<>();
        this.counter = 1;
        this.wasRemoved = false;
    }

    /**
     * makes block from point and two double.
     * @param upperLeft corner of block
     * @param width of block
     * @param hight of block
     * @param img for block
     */
    public Block(Point upperLeft, double width, double hight, Image img) {
        this.rect = new Rectangle(upperLeft, width, hight);
        this.hitVelocity = new Velocity(0, 0);
        this.hitListeners = new ArrayList<>();
        this.hitPoints = 0;
        this.image = img;
        this.colorlist = new ArrayList<>();
        this.imagelist = new ArrayList<>();
        this.counter = 1;
        this.colorBorder = Color.BLACK;
        this.wasRemoved = false;
    }
    /**.
     * @return x value
     */
    public int getX1() {
        return  (int) this.rect.getUpperLeft().getX();

    }
    /**.
     *
     * @return y value
     *
     */

    public int getY1() {
        return (int) this.rect.getUpperLeft().getY();

    }
    /**.
     * @return x value
     */
    public int getX2() {
        return  (int) this.rect.getWidth();

    }
    /**.
     *
     * @return y value
     *
     */

    public int getY2() {
        return (int) this.rect.getHeight();

    }
    /**
     * draws block onto the screen.
     * @param surface to draw the block on
     */
    public void drawOn(DrawSurface surface) {
        if (colorBorder == null || border) {
            colorBorder = color1;
            border = true;

        }

        if (this.image == null) {

            surface.setColor(colorBorder);
            surface.drawRectangle(this.getX1(), this.getY1(), this.getX2(), this.getY2());

            surface.setColor(this.color1);
            surface.fillRectangle(this.getX1(), this.getY1(), this.getX2(), this.getY2());

        } else {
            surface.setColor(colorBorder);
            surface.drawRectangle(this.getX1(), this.getY1(), this.getX2(), this.getY2());
            surface.drawImage(this.getX1(), this.getY1(), image);
        }


    }
    /**
     * sets color to block.
     * @param c sets color
     */
    public void setColor1(Color c) {
        this.color1 = c;
    }

    /**
     * sets color to border.
     * @param c sets color
     */
    public void colorBorder(Color c) {
        this.colorBorder = c;
    }



    /**
     * @return the shapes.Rectangle in use.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    /**
     * @return num of hit points.
     */
    public double getHitPoints() {
        return hitPoints;
    }

    /**.
     * @param collisionPoint point of collision with block
     * @param currentVelocity velocity befor hit
     * @param hitter the ball thats hitting
     * @return Veclocity after hit
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double x = currentVelocity.getX();
        double y = currentVelocity.getY();


        if (this.rect.top().pointOnLine(collisionPoint)
                || this.rect.bottom().pointOnLine(collisionPoint)) {
            y = -y;

        }
        if (this.rect.left().pointOnLine(collisionPoint)
                || this.rect.right().pointOnLine(collisionPoint)) {
            x = -x;

        }
        this.hitVelocity = new Velocity(x, y);

        this.notifyHit(hitter);
        this.hitPoints++;

        this.rect.numHits();
        this.colorChange(colorlist, this.rect.getnumHits());
        this.imageChange(imagelist, this.rect.getnumHits());



        return this.hitVelocity;
    }

    /**
     * nothing yet.
     * @param dt frame rate
     */
    public void timePassed(double dt) {

    }

    /**
     * @param g ands block to the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);

    }

    /**
     *
     * @param game removes block from this game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);

    }

    /**
     *
     * @param hl Add hl as a listener to hit events.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     *
     * @param hl Remove hl from the list of listeners to hit events.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     *
     * @param hitter tells block its  being hit by ball.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     *
     * @return rectangle.
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     *
     * @param beingHit lets object know that its being hit.
     * @param hitter  The the sprites.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.addHitListener(hitter);

    }

    /**
     *
     *  changes colors based on hit points.
     *  @param list of blocks
     *  @param hits count
     */
    public void colorChange(ArrayList<Color> list, int hits) {
        ArrayList<Color> check = new ArrayList<>();
        check.addAll(list);
        if (!check.isEmpty() && counter < list.size()) {
            this.setColor1(check.get(this.counter));
        }
        if (counter < hits) {
            counter++;
        }


    }

    /**
     *
     * changes colors based on hit points.
     * @param list of blocks
     * @param hits count
     */
    public void imageChange(ArrayList<String> list, int hits) {
        if (!list.isEmpty()) {

            try {
                if (hits > 0) {
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(list.get(hits - 1));
                    //Reader r = new BufferedReader(new InputStreamReader(is));
                    this.image = ImageIO.read(is);

                }
            } catch (IOException e) {
                System.out.println("could not find file images!");
            }
        }

    }

    /**
     *
     * @return if block was removed from game.
     */
    public boolean wasReamoved() {
        return this.wasRemoved;
    }

    /**
     *
     * if block was removed from game.
     */
    public void setWasRemoved() {
        this.wasRemoved = true;
    }

    /**
     *
     * @return the image.
     */
    public Image getImage() {
        return image;
    }
}
