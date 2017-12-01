package shapes;
import java.util.List;

/**.
 * line holds two points, can chack for intersection between lines as well
 * as give info on current line
 */
public class Line {
    private Point p1;
    private Point p2;
    // constructors
    /**
     * @param  start point of line
     * @param  end point of line
     */
    public Line(Point start, Point end) {

        if (start.getX() <= end.getX() && start.getY() <= end.getY()) {
            this.p1 = start;
            this.p2 = end;

        } else {
            this.p1 = end;
            this.p2 = start;
        }
    }
    /**
     * @param  x1 x val of point 1
     * @param  y1 y val of point 1
     * @param  x2 x val of point 2
     * @param  y2 y val of point 2
     */
    public Line(double x1, double y1, double x2, double y2) {

            this.p1 = new Point(x1, y1);
            this.p2 = new Point(x2, y2);

    }

    /**
     *
     * @return p1.distance(p2) length of line
     *
     */
    public double length() {

        return p1.distance(p2);
    }

    /**
     *
     * @return point - middle point of line
     *
     */
    public Point middle() {
        return new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
    }

    /**
     *
     * @return middle x value.
     */
    public double xmiddle() {
        return this.middle().getX();
    }

    /**
     *
     * @return middle y value.
     */
    public double ymiddle() {
        return this.middle().getY();
    }

    /**
     *
     * @return p1 start point of line
     *
     */
    public Point start() {
        return p1;
    }

    /**
     *
     * @return p2 end point of line
     *
     */
    public Point end() {
        return p2;
    }

    /**
     *
     * @return double slope of line
     *
     */
    public double slope() {
        if (Math.abs(this.p1.getX() - this.p2.getX()) < 0.00000001) {
            return 1;
        }

        return (this.p1.getY() - this.p2.getY()) / (this.p1.getX() - this.p2.getX());
    }
    /**
     *
     * @return double line function  -  y value
     *
     */
    public double func() {

        return (this.p1.getY()) - (this.slope() * this.p1.getX());

    }

    /**
     *  @param  other checkes intersction with line other
     *  @return boolean returns if intersect
     *
     */
    public boolean isIntersecting(Line other) {

        return (this.intersectionWith(other) != null);
    }

    /**
     * @param  other finds point(if exists) of intersection with line other
     * @return point - if found returns point else returns null
     *
     */
    public Point intersectionWith(Line other) {

        double c;
        double d;

        if (this.equals(other)) {
            return null;
        }
        // gets x val of intersection using slope
        if (Math.abs(this.p1.getX() - this.p2.getX()) < 0.00000001) {
            c = this.p1.getX();
        } else {
            c = (this.func() - other.func()) / (other.slope() - this.slope());
        }

        // checks that slope is not 0 so it wont calculate wrong
        if (Math.abs(this.p1.getY() - this.p2.getY()) < 0.00000001) {
            d = this.p1.getY();
        } else if (this.slope() == 1 && Math.abs(this.p1.getX() - this.p2.getX()) < 0.00000001) {
            d = (other.slope() * c) + other.func();
        } else if (other.slope() == 1) {
           d = (this.slope() * c) + this.func();
        } else {
           d = (this.slope() * c) + this.func();
       }
        //makes sure the point is in range
        if (inRange(c, d, other)) {
            return new Point(c, d);
        }

       return null;
    }
    /**.
     *  checks to see if intersection between lines is in range of both lines
     *  @param  c x val of intersection
     *  @param  d y val of intersection
     *  @param  other line
     *  @return boolean returns if point is in range
     *
     */
    public boolean inRange(double c, double d, Line other) {
        int count = 0;

        double x  =  Math.round(c * Math.pow(10, 10)) / Math.pow(10, 10);
        double y =  Math.round(d * Math.pow(10, 10)) / Math.pow(10, 10);


       if ((this.min(this.p1.getX(), this.p2.getX()) <= x
               || Math.abs(this.min(this.p1.getX(), this.p2.getX()) - x) < 0.000001)
               && ((x <= this.max(this.p1.getX(), this.p2.getX())
               || Math.abs(this.max(this.p1.getX(), this.p2.getX()) - x) < 0.000001))) {
            count++;
        }
        if ((this.min(this.p1.getY(), this.p2.getY()) <= y
                || Math.abs(this.min(this.p1.getY(), this.p2.getY()) - y) < 0.000001)
                && (y <= this.max(this.p1.getY(), this.p2.getY())
                || Math.abs(this.max(this.p1.getY(), this.p2.getY()) - y) < 0.000001)) {
            count++;
        }
        if ((other.min(other.p1.getX(), other.p2.getX()) <= x
                || Math.abs(other.min(other.p1.getX(), other.p2.getX())  - x) < 0.000001)
                && (x <= other.max(other.p1.getX(), other.p2.getX())
                || Math.abs(other.max(other.p1.getX(), other.p2.getX()) - x) < 0.000001)) {
            count++;
        }
        if ((this.min(other.p1.getY(), other.p2.getY()) <= y
                || Math.abs(this.min(other.p1.getY(), other.p2.getY()) - y) < 0.000001)
                && (y <= other.max(other.p1.getY(), other.p2.getY())
                || Math.abs(other.max(other.p1.getY(), other.p2.getY()) - y) < 0.000001)) {
            count++;
        }

        return  (count == 4);

    }
    /**
     * @param num1 number one
     * @param num2 number two
     * @return the smaller number
     */
    public double min(double num1, double num2) {
        if (num1 < num2) {
            return num1;
        }
        return num2;
    }
    /**
     * @param num1 number one
     * @param num2 number two
     * @return the bigger number
     */
    public double max(double num1, double num2) {
        if (num1 > num2) {
            return num1;
        }
        return num2;
    }
    /**
     * @param check if point on line
     * @return true or false
     */
    public boolean pointOnLine(Point check) { //changed this!
        int count = 0;

        Point checkRound = new Point(check.getX(), check.getY());

        if ((this.min(this.p1.getX(), this.p2.getX()) <= checkRound.getX()
                || Math.abs(this.min(this.p1.getX(), this.p2.getX()) - checkRound.getX()) < 0.000001)
                && ((checkRound.getX() <= this.max(this.p1.getX(), this.p2.getX())
                || Math.abs(this.max(this.p1.getX(), this.p2.getX()) - checkRound.getX()) < 0.000001))) {
            count++;
        }
        if ((this.min(this.p1.getY(), this.p2.getY()) <= checkRound.getY()
                || Math.abs(this.min(this.p1.getY(), this.p2.getY()) - checkRound.getY()) < 0.000001)
                && (checkRound.getY() <= this.max(this.p1.getY(), this.p2.getY())
                || Math.abs(this.max(this.p1.getY(), this.p2.getY()) - checkRound.getY()) < 0.000001)) {
            count++;
        }
       return count == 2;
    }

    // equals -- return true is the lines are equal, false otherwise
    /**
     * @param other line to see if are equal
     * @return boolean true if they are equal
     */
    public boolean equals(Line other) {
        return (this.slope() == other.slope() && this.p1.equals(other.p1) && this.p2.equals(other.p2));

    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * @param rect to check for intersection
     * @return current  the closest intersection point to the start of the line. or null
     * if no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        Line check = new Line(this.p1, this.p2);

        double count = 200;

        Point current = new Point(1000, 1000);

        List<Point> listOfPoints = rect.intersectionPoints(check);

        if (listOfPoints.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < listOfPoints.size(); i++) {

                if (this.start().distance(listOfPoints.get(i)) < count) {
                    count = this.start().distance(listOfPoints.get(i));
                    current = new Point(listOfPoints.get(i).getX(), listOfPoints.get(i).getY());
                }
            }
        }

        //if (listOfPoints.get(i).getX() )
        return current;



    }

}

