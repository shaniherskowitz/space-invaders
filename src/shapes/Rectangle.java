package shapes;

import java.util.ArrayList;
import java.util.List;
/**
 * creates rectangle.
 */
public class Rectangle {

    private double width;
    private double height;
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    private int numHits;
    private double countDown;
    private double countRight;

    /**
     * @param upperLeft location of rectangle
     * @param width1 of rectangle
     * @param height1 of rectangle
     * Create a new rectangle with location and width/height.
     */
    public Rectangle(Point upperLeft, double width1, double height1) {
        this.height = height1;
        this.width = width1;

        Point uL = new Point(upperLeft.getX(), upperLeft.getY());
        Point uR = new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY());
        Point lL = new Point(upperLeft.getX(), upperLeft.getY() + this.getHeight());
        Point lR = new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY() + this.getHeight());


        this.top = new Line(uR, uL);
        this.bottom = new Line(lR, lL);
        this.left = new Line(uL, lL);
        this.right = new Line(uR, lR);

        this.countDown = 0;
        this.countRight = 0;

        this.numHits = 0;
    }

    /**
     * @param line checks intersection of rectangle with line
     * @return listOfPoints a (possibly empty) List of intersection points
     * with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {

        List<Point> listOfPoints = new ArrayList<>();

        if (this.top.intersectionWith(line) != null) {
            listOfPoints.add(this.top.intersectionWith(line));
        }
        if (this.bottom.intersectionWith(line) != null) {
            listOfPoints.add(this.bottom.intersectionWith(line));
        }
        if (this.right.intersectionWith(line) != null) {
            listOfPoints.add(this.right.intersectionWith(line));
        }
        if (this.left.intersectionWith(line) != null) {
            listOfPoints.add(this.left.intersectionWith(line));
        }
        return listOfPoints;

    }
    /**
     * @return size of rect
     */
    public double getSize() {
        return this.getHeight() * this.getWidth();
    }
    /**
     * @return size of rect
     */
    public Line top() {
        return this.top;
    }
    /**
     * @return size of rect
     */
    public Point getUpperL() {
        return this.top.start();
    }
    /**
     * @return size of rect
     */
    public Point getLowerL() {
        return this.left.end();
    }
    /**
     * @return size of rect
     */
    public Point getUpperR() {
        return this.top.end();
    }

    /**
     * @return size of rect
     */
    public Line bottom() {
        return this.bottom;
    }
    /**
     * @return size of rect
     */
    public Line left() {
        return this.left;
    }
    /**
     * @return size of rect
     */
    public Line right() {
        return this.right;
    }
    /**
     * @return width of rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * @return height of rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * @return  the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.left.start();
    }
    /**
     * @return  the number of hits on the rect.
     */
    public int getnumHits() {
        return this.numHits;
    }
    /**
     * counts number of hits on each block.
     */
    public void numHits() {
        if (this.numHits > 0) {
            this.numHits -= 1;
        }
    }


    /**
     * calculates number of hits on block.
     * @param num number of hits
     */
    public void updateNumHits(double num) {
        this.numHits = (int) num;

    }

    /**
     *
     * @return how many times moved down.
     */
    public double getCountDown() {
        return countDown;
    }

    /**
     *
     * @return how many times went right.
     */
    public double getCountRight() {
        return countRight;
    }


    /**
     *
     * @param num moves rectangle down by num.
     */
    public void moveDown(double num) {
        this.top.start().moveDown(num);
        this.top.end().moveDown(num);
        this.bottom.start().moveDown(num);
        this.bottom.end().moveDown(num);
        countDown += num;
    }

    /**
     *
     * @param num moves rect right by num.
     */
    public void moveRight(double num) {
        this.top.start().moveRight(num);
        this.top.end().moveRight(num);
        this.bottom.start().moveRight(num);
        this.bottom.end().moveRight(num);
        countRight += num;

    }

    /**
     *
     * @param num moves rect left by num.
     */
    public void moveLeft(double num) {
        this.top.start().moveLeft(num);
        this.top.end().moveLeft(num);
        this.bottom.start().moveLeft(num);
        this.bottom.end().moveLeft(num);
        countRight -= num;
    }

    /**
     *
     * @param num moves rect up by num.
     */
    public void moveUp(double num) {
        this.top.start().moveUp(num);
        this.top.end().moveUp(num);
        this.bottom.start().moveUp(num);
        this.bottom.end().moveUp(num);
        countDown -= num;
    }


}