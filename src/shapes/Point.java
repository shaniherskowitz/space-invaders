package shapes;

/**.
 * creates a point with x,y values + calculates distance from one point to another.
 */
public class Point {

    private double x;
    private double y;
    // constructor
    /**
     * @param  x1 x val of point
     * @param  y1 y val of point
     */
    public Point(double x1, double y1) {
        this.x = x1;
        this.y = y1;
    }

    // distance -- return the distance of this point to the other point
    /**
     * @param  other point for distance check
     * @return distanceIs distance between points
     */
    public double distance(Point other) {

        double distanceIs;

        distanceIs = Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));

        return distanceIs;

    }

    // equals -- return true is the points are equal, false otherwise
    /**
     * @param  other point to compare
     * @return boolean if points are equal
     *
     */
    public boolean equals(Point other) {

        if (other == null) {
            return false;
        }
        return  (this.x == other.x && this.y == other.y);

    }

    // Return the x and y values of this point
    /**
     *
     * @return this.x x val of point.
     *
     */
    public double getX() {
        return this.x;

    }
    /**
     *
     * @return this.y y value of point.
     *
     */
    public double getY() {
        return this.y;

    }
    /**
     * moves point to the left.
     * @param num number to move x
     */
    public void moveLeft(double num) {
            this.x = this.x - num;

    }
    /**
     * moves point to the right.
     * @param num number to move x
     */
    public void moveRight(double num) {
            this.x = this.x + num;
    }

    /**
     * moves point down.
     * @param num number to move y
     */
    public void moveDown(double num) {
        this.y = this.y + num;
    }

    /**
     * moves point up.
     * @param num number to move y
     */
    public void moveUp(double num) {
        this.y = this.y - num;
    }

}
