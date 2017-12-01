package rungame;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * runs the game animation.
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;


    /**
     *
     * creates animation of game.
     */
    public AnimationRunner() {
        this.gui = new GUI("Game", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     *
     * @param animation runs the animation.
     * @param dt frame rate
     */
    public void run(Animation animation, double dt) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d, dt);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     *
     * @return get draw surface.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     *
     * @return frames per second.
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }

}