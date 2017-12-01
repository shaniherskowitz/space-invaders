package shapes;

/**.
 * calculates velocity of ball
 */
public class Velocity {

    private double x;
    private double y;
    // constructor

    /**
     * @param dx x of velocity
     * @param dy y of velocity
     */
    public Velocity(double dx, double dy) {

        this.x = round(dx, 6);
        this.y = round(dy, 6);
    }

    /**
     *
     * @param v sets new velocity from old one.
     */
    public Velocity(Velocity v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    /**
     * @param angle of velocity
     * @param speed of velocity
     * @return velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        if (angle == 0 || angle == 180) {
            angle = 1;
        }

        double dx = Math.cos((angle - 90.0D) / 180.0D * 3.141592653589793D) * speed;
        double dy = Math.sin((angle - 90.0D) / 180.0D * 3.141592653589793D) * speed;

        return new Velocity(dx, dy);
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)

    /**.
     * adds one point to another
     * @param p point for change
     * @return  new point anded with old point
     */
    public Point applyToPoint(Point p) {


        return new Point(p.getX() + this.x, p.getY() + this.y);

    }

    /**
     * @return x val of velocity
     */
    public double getX() {
        return this.x;

    }

    /**.
     * @return y val of velocity.
     */
    public double getY() {
        return this.y;

    }

    /**
     * @param value to round
     * @param places to round to
     * @return list of collidables in environment
     */
    public static double round(double value, int places) {

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
     * @param angle new angle to change.
     * @return new velocity with new angle
     */
    public Velocity changeAngle(double angle) {
        double newSpeed = Math.sqrt(x * x + y * y);

        return Velocity.fromAngleAndSpeed(angle, newSpeed);
    }
    /**
     *
     * @param speed new speed to change.
     */
    public void changeSpeed(double speed) {

        this.x = this.getX() * speed;
        this.y = this.getY() * speed;

    }

}
