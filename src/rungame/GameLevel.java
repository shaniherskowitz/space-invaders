package rungame;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import moving.Aliens;
import moving.Shooter;
import screens.KeyPressStoppableAnimation;
import screens.PauseScreen;
import shapes.Point;
import shapes.Rectangle;
import sprites.Block;
import sprites.Paddle;
import sprites.SpriteCollection;
import java.util.ArrayList;


/**
 * initiates the game based on parameter.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter count;
    private Counter ballCount;
    private Counter score;
    private Counter numberOfLives;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;
    private Paddle paddle;
    private BallRemover ballRemover;
    private boolean change;
    private ArrayList<Block> aliens;
    private Shooter shooter;


    /**
     *
     * @param levelInfo of current level.
     * @param key board to play on
     * @param run animation for game
     * @param blocks left to remove
     * @param scoreIs current score of game
     * @param livesLeft lives left for game
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor key, AnimationRunner run, Counter blocks,
                     Counter scoreIs, Counter livesLeft) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.count = blocks;
        this.ballCount = new Counter(0);
        this.score = scoreIs;
        this.numberOfLives = livesLeft;
        this.level = levelInfo;
        this.keyboard = key;
        this.runner = run;
        this.paddle = null;
        this.ballRemover = null;
        this.change = false;
        this.aliens = new ArrayList<>();
        aliens.addAll(this.level.aliens());
        this.shooter = new Shooter(environment, keyboard, this);



    }

    /**
     * @param c adds collidable c to game environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);

    }

    /**
     * @param s adds sprites to list od sprites.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);

    }

    /**
     *
     * @param c removes collidable from game.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getEnvironment().remove(c);
    }

    /**
     *
     * @param s removes sprite from game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getsCollection().remove(s);
    }

    /**
     * creates a new game.
     * Initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle)
     */
    public void initialize() {


        ballRemover = new BallRemover(this, ballCount);

        InitializeLevel init = new InitializeLevel(this.level, this.aliens, count);
        init.initialize(ballRemover, this, score, numberOfLives);


    }

    /**
     *
     * @return frame rate.
     */

    public double getFrameRate() {
        return (double) 1 / (double) runner.getFramesPerSecond();
    }


    /**
     * Run the game -- start the animation loop.
     * @param frameRate  of game
     */
    public void playOneTurn(double frameRate) {

        this.running = true;

        paddle = createPaddle(this.runner.getGui());


        this.runner.run(new CountdownAnimation(2, 3, this.sprites), frameRate);

        while (this.running) {

            this.runner.run(this, frameRate);
            doOneFrame(this.runner.getGui().getDrawSurface(), frameRate);


        }
        this.sprites.remove(paddle);
        this.environment.remove(paddle);

        shooter.removeBalls();

        if (count.getValue() == 0) {
            score.increase(1000);
        }

    }

    /**
     *
     * @param d draws one frame of the game on this surface.
     * @param dt frame rate
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);

        if (!(count.getValue() > 0)) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()), dt);

        }
        moveAliens(dt);


    }

    /**
     * moves aliens in formation.
     * @param dt frame rate
     */
    public void moveAliens(double dt) {
        Aliens aliens1 = new Aliens(runner, level, running, this.aliens);
        change = aliens1.moveAliens(change);
        running = aliens1.shouldStop();
        shooter.shoot(aliens1, dt, aliens, paddle);

        if (paddle.getWasHit() != null) {
            ballCount.decrease(1);
            this.removeSprite(paddle.getWasHit());
            change = false;
            aliens1.resetFormation();
            aliens1.changeSpeed();
            this.running = false;
        }

    }



    /**
     *
     * @return when to stop the animation.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     *
     * @param gui surface for the paddle.
     * @return paddle for the game
     */
    private Paddle createPaddle(GUI gui) {
        Rectangle pad = new Rectangle(new Point((gui.getDrawSurface().getWidth() / 2 - this.level.paddleWidth() / 2),
                550), this.level.paddleWidth(), 20);
        this.keyboard = gui.getKeyboardSensor();
        Paddle paddle1 = new Paddle(pad, keyboard,
                10, 790 - (int) pad.getWidth(),
                this.level.paddleSpeed());
        paddle1.addToGame(this);
        return paddle1;
    }

    /**
     *
     * updates ball count.
     *  num to increase
     */
    public void increaseBallCount() {
        ballCount.increase(1);
    }

    /**
     *
     *  num to add to ball remover.
     */
    public void updateBallRemover() {

        ballRemover.updateBalls(1);
    }

}

