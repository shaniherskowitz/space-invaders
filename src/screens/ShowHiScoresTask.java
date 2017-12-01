package screens;

import biuoop.KeyboardSensor;
import interfaces.Animation;
import interfaces.Task;
import rungame.AnimationRunner;

/**
 * shows high scores.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor key;

    /**
     * @param runner1 for animation.
     * @param highScoresAnimation1 to display
     * @param keyboardSensor to get out
     */
    public ShowHiScoresTask(AnimationRunner runner1, Animation highScoresAnimation1, KeyboardSensor keyboardSensor) {
        this.runner = runner1;
        this.highScoresAnimation = highScoresAnimation1;
        this.key = keyboardSensor;
    }

    /**
     *
     * @return the animation.
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(key, KeyboardSensor.SPACE_KEY,
                highScoresAnimation), 2);
        return null;
    }
}
