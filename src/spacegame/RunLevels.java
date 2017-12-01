package spacegame;

import rungame.GameFlow;
import score.HighScoresTable;
import java.io.File;


/**
 * runs all levels in a loop.
 */
public class RunLevels {
    /**
     * to run levels.
     */
    public RunLevels() {
    }

    /**
     * runs levles.
     */
    public void run() {

        File file = new File("highscores");
        HighScoresTable scoresTable = HighScoresTable.loadFromFile(file);


        GameFlow first = new GameFlow(3, scoresTable);
        first.showMenu();

    }
}
