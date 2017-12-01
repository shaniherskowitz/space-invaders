package screens;

import interfaces.Task;
import rungame.GameFlow;

/**
 * runs game.
 */
public class ShowGameTask implements Task<Void> {
    private GameFlow game;

    /**
     *
     * @param game1 to run.
     */
    public ShowGameTask(GameFlow game1) {
        this.game = game1;
    }

    /**
     *
     * @return runs game.
     */
    @Override
    public Void run() {
        game.runLevels();
        return null;
    }
}
