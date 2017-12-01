package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * Controls end of screens.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation ani;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     *
     * @param sensor1 for keyboard to stop.
     * @param key1 to press when you want to stop
     * @param animation to run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor1, String key1, Animation animation) {
        this.sensor = sensor1;
        this.key = key1;
        this.ani = animation;
        this.stop = false;
        this.isAlreadyPressed = true;

    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.

    /**
     *
     * @param d draws one frame of the game on this surface.
     * @param dt frame rate
     */
    public void doOneFrame(DrawSurface d, double dt) {

        ani.doOneFrame(d, dt);

        if (!isAlreadyPressed) {

            if (this.sensor.isPressed(key)) {
                this.stop = true;
            }
        }

        if (!this.sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }

    }

    /**
     *
     * @return when to stop the animation.
     */
    public boolean shouldStop() {
            return stop;

    }
}