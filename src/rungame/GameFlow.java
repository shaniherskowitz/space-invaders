package rungame;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import interfaces.Menu;
import interfaces.Task;
import screens.QuitTask;
import screens.HighScoresAnimation;
import screens.ShowHiScoresTask;
import screens.EndScreen;
import screens.MenuAnimation;
import screens.KeyPressStoppableAnimation;
import screens.ShowGameTask;
import score.HighScoresTable;
import score.ScoreInfo;
import spacegame.Level;
import java.io.File;

/**
 * controls the flow of the game.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter numLives;
    private Counter blocks;
    private double frameRate;
    private HighScoresTable scoresTable;
    private int numberLives;

    /**
     * @param numLivesRemain lives left,
     * @param table of scores
     */
    public GameFlow(int numLivesRemain, HighScoresTable table) {

        this.animationRunner = new AnimationRunner();
        this.keyboardSensor = animationRunner.getGui().getKeyboardSensor();
        this.score = new Counter(0);
        this.numberLives = numLivesRemain;
        this.numLives = new Counter(numLivesRemain);
        this.blocks = new Counter(0);
        this.frameRate = 1 / 60;
        this.scoresTable = table;

    }


    /**
     *
     *  runs all levels of game.
     */

    public void runLevels() {
        int levelnum = 1;
        double speed = 1;
        while (true) {

            GameLevel level = new GameLevel(new Level(levelnum, speed),
                    this.keyboardSensor, this.animationRunner, this.blocks,
                    this.score, this.numLives);
            this.frameRate = level.getFrameRate();

            level.initialize();

            while (this.blocks.getValue() > 0 && this.numLives.getValue() > 0) {
                level.playOneTurn(frameRate);
                if (this.blocks.getValue() == 0) {

                    break;
                 }

                this.numLives.decrease(1);
            }
            levelnum++;
            speed += 0.5;

            if (this.numLives.getValue() <= 0) {
                break;
            }

        }

        this.animationRunner.run(new KeyPressStoppableAnimation(animationRunner.getGui().getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, new EndScreen(score.getValue(), false)), this.frameRate);

        showScore();

        score = new Counter(0);
        numLives = new Counter(numberLives);
        blocks = new Counter(0);

    }


    /**
     * shows high scores.
     */
    public void showScore() {
        if (scoresTable.getHighScores().size() < scoresTable.getSize() || score.getValue()
                > scoresTable.getHighScores().get(scoresTable.getHighScores().size() - 1).getScore()) {

            DialogManager dialog = animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");

            scoresTable.add(new ScoreInfo(name, score.getValue()));

            File file = new File("highscores");
            try {
                scoresTable.save(file);
            } catch (Exception e) {
                System.out.println("cant save scores");
            }

        }

        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(scoresTable)), 1);


    }

    /**
     * shows menu.
     */
    public void showMenu() {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Space Invaders ", keyboardSensor);


        menu.addSelection("g", "Game", new ShowGameTask(this));

        menu.addSelection("h", "High Scores",
                new ShowHiScoresTask(animationRunner, new HighScoresAnimation(scoresTable), keyboardSensor));

        menu.addSelection("q", "Quit", new QuitTask());


        while (true) {
            animationRunner.run(menu, 2);

            Task<Void> task = menu.getStatus();

            task.run();
        }

    }

}
