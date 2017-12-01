package screens;

import interfaces.Task;

/**
 * quits game.
 */
public class QuitTask implements Task<Void> {

    /**
     * quits game.
     */
    public QuitTask() { }

    /**
     * @return quit game.
     */
    @Override
    public Void run() {
        System.exit(0);
        return null;
    }
}
