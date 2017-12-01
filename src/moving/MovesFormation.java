package moving;

/**
 * controls movement of blocks in formation.
 */
public class MovesFormation {
    private InFormation move;
    private boolean controls;

    /**
     *
     * @param move for blocks in formation.
     * @param controls movement
     */
    public MovesFormation(InFormation move, boolean controls) {
        this.move = move;
        this.controls = controls;

    }

    /**
     * moves blocks.
     */
    public void movesBlocks() {

      if (controls) {
          move.moveLeft();
      } else {
          move.moveRight();
      }
    }
}
