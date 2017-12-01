package score;

import java.io.Serializable;

/**
 * holds info on scores.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     *
     * @param name1 of player.
     * @param score1 of player
     */
    public ScoreInfo(String name1, int score1) {
        this.name = name1;
        this.score = score1;
    }

    /**
     *
     * @return name of score.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return score of player.
     */
    public int getScore() {
        return score;
    }
}
