package moving;

import interfaces.LevelInformation;
import rungame.AnimationRunner;
import sprites.Block;
import java.util.ArrayList;
import java.util.Random;

/**
 * controls aliens.
 */
public class Aliens {
    private LevelInformation level;
    private ArrayList<Block> aliens;
    private boolean running;
    private InFormation move;

    /**
     *
     * @param runner to run animation.
     * @param level to play
     * @param running when to stop
     * @param list of blocks
     */
    public Aliens(AnimationRunner runner, LevelInformation level, boolean running, ArrayList<Block> list) {
        this.level = level;
        this.running = running;
        this.aliens = list;
        this.move = new InFormation(aliens, this.level.speedToChange(), 20,
                runner.getGui().getDrawSurface().getWidth() - 20, 500);
    }

    /**
     * moves aliens in formation.
     * @param change of movement
     * @return if moved
     */
    public boolean moveAliens(boolean change) {

        updateAliens();

        MovesFormation change1 = new MovesFormation(move, change);
        change1.movesBlocks();

        if (move.hitSide()) {
            if (!move.moveDown()) {
                this.level.setSpeed(this.level.aliensSpeed());
                move.reset();
                change = false;
                this.running = false;

            } else {
                change = !change;
                this.level.setSpeed(this.level.speedToChange() + this.level.speedToChange() / 10);
            }
        }
        return change;

    }

    /**
     *
     * @return when to stop.
     */
    public boolean shouldStop() {
        return this.running;
    }

    /**
     * changes speed.
     */
    public void changeSpeed() {
        this.level.setSpeed(this.level.aliensSpeed());

    }

    /**
     * updates list of aliens.
     */
    public void updateAliens() {
        for (int i = 0; i < this.aliens.size(); i++) {
            if (this.aliens.get(i).wasReamoved()) {
                this.aliens.remove(i);
            }
        }
    }

    /**
     *
     * @return picks alien to shoot bullet.
     */
    public shapes.Point shoot() {
        ArrayList<Block> bottemRow = new ArrayList<>();
        boolean contains = false;

        for (int i = 0; i < this.aliens.size(); i++) {
            if (bottemRow.size() < 10) {
                for (int s = 0; s < bottemRow.size(); s++) {
                    if (bottemRow.get(s).getX1() == this.aliens.get(i).getX1()) {
                        contains = true;
                    }
                }
                    if (!contains) {
                        bottemRow.add(this.aliens.get(i));
                    }
                    contains = false;

            }
        }
        for (int j = 0; j < this.aliens.size(); j++) {
            for (int k = 0; k < bottemRow.size(); k++) {
                if (this.aliens.get(j).getX1() == bottemRow.get(k).getX1()) {
                    bottemRow.remove(k);
                    bottemRow.add(this.aliens.get(j));
                }
            }

        }

        int rnd = new Random().nextInt(bottemRow.size());

        return new shapes.Point((int) bottemRow.get(rnd).getRect().bottom().xmiddle() + 2,
               (int) bottemRow.get(rnd).getRect().bottom().ymiddle() + 2);


    }

    /**
     *
     * resets aliens.
     */
    public void resetFormation() {

        this.move.reset();
    }

}
