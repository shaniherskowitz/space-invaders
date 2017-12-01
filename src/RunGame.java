import spacegame.RunLevels;

/**
 * runs the game.
 */
public class RunGame {
    /**
     * runs game.
     * @param args not in use
     */
    public static void main(String[] args) {


        try {
            RunLevels run = new RunLevels();
            run.run();


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("problem!");

        }
    }
}
