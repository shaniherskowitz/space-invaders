package score;
import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * keeps track of high scores.
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> scoresTable;
    private int size;


    /**
     *  Creates an empty high-scores table with the specified size.
     * @param size the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        scoresTable = new ArrayList<>(size);
        this.size = size;
    }

    /**
     *
     * @param score Add a high-score.
     */
    public void add(ScoreInfo score) {


        if (getRank(score.getScore()) <= size) {
            if (scoresTable.size() >= size) {
                scoresTable.remove(scoresTable.size() - 1);
            }
            scoresTable.add(getRank(score.getScore()) - 1, score);
        }

    }

    /**
     *
     * @return Return table size.
     */
    public int size() {
        return scoresTable.size();
    }

    /**
     *
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return scoresTable;
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.

    /**
     *
     * @param score the rank of the current score
     * @return the rank of the current score
     */
    public int getRank(int score) {
        int count = 1;
        if (scoresTable.size() == 0) {
            return count;
        }
        for (int i = 0; i < scoresTable.size(); i++) {
            count++;
            if (scoresTable.get(i).getScore() < score) {
                return count - 1;
            }

        }
        return count;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        for (int i = 0; i < scoresTable.size(); i++) {
           scoresTable.remove(i);
        }
    }


    /**
     *
     * @param filename Load table data from file.
     *                  Current table data is cleared.
     * @throws IOException if cant read file
     */
    public void load(File filename) throws IOException {
       // clear();

        ObjectInputStream objectInputStream = null;
        try {

            objectInputStream  = new ObjectInputStream(
                    new FileInputStream(filename));

            this.scoresTable = (ArrayList<ScoreInfo>) objectInputStream.readObject();

        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);

        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }


    }


    /**
     *
     * @param filename Save table data to the specified file.
     * @throws IOException not good
     */
    public void save(File filename) throws IOException {

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(scoresTable);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     *
     * @return size of list.
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param filename Read a table from file.
     * @return table If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable first = new HighScoresTable(5);
        try {
            first.load(filename);
        } catch (IOException e) {
            HighScoresTable second =  new HighScoresTable(5);
            try {
                second.save(filename);
                return second;
            } catch (Exception s) {
                System.out.println("no file");
            }
        }

        return first;
    }
}
