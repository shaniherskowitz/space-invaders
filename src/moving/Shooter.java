package moving;

import biuoop.KeyboardSensor;
import rungame.GameEnvironment;
import rungame.GameLevel;
import shapes.Velocity;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Shoots balls when needed.
 */
public class Shooter {
    private int timeShip;
    private int timeAliens;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private ArrayList<Ball> balls;
    private GameLevel gameLevel;

    /**
     *
     * @param environment1 to add balls too.
     * @param keyboard1 to check press
     * @param gameLevel1 to make changes
     */
    public Shooter(GameEnvironment environment1, KeyboardSensor keyboard1, GameLevel gameLevel1) {
        this.environment = environment1;
        this.keyboard = keyboard1;
        this.balls = new ArrayList<>();
        this.gameLevel = gameLevel1;
    }

    /**
     *
     * @param aliens1 to shoot.
     * @param dt for balls speed
     * @param aliens to change.
     * @param paddle to shoot
     */
    public void shoot(Aliens aliens1, double dt, ArrayList<Block> aliens, Paddle paddle) {
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (timeShip <= 0) {
                createBall(dt, new shapes.Point((paddle.upperLeft().getX()
                        + (paddle.getWidth() / 2)),
                        (paddle.upperLeft().getY() - 5)), 0, 500, Color.WHITE);
                timeShip = 21;
            }
        }
        timeShip--;

        if (aliens.size() > 0) {

            if (timeAliens == 0) {
                createBall(dt, aliens1.shoot(), -179, 300, Color.RED);
                timeAliens = 30;
            }
            timeAliens--;

        }
    }

    /**
     *
     * @param dt of game.
     * @param p point of ball
     * @param angle for velocity
     * @param speed for velocity
     * @param color of ball
     */
    public void createBall(double dt, shapes.Point p, double angle, int speed, Color color) {
        Ball ball = new Ball((int) p.getX(), (int) p.getY(), 2, color, environment);
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        v.changeSpeed(dt);
        ball.setVelocity(v);
        gameLevel.increaseBallCount();
        ball.addToGame(this.gameLevel);
        balls.add(ball);
        gameLevel.updateBallRemover();

    }

    /**
     *
     * deletes balls from game.
     */
    public void removeBalls() {
        for (int i = 0; i < this.balls.size(); i++) {
            this.balls.get(i).removeFromGame(this.gameLevel);
        }
    }
}
