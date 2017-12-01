package sprites;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import interfaces.Sprite;

/**
 * holds list of all items that should be drawn onto screen.
 */
public class SpriteCollection {
    private List<Sprite> sCollection;

    /**
     * creates new list of sprites.
     */
    public SpriteCollection() {
        this.sCollection = new ArrayList<>();
    }

    /**
     * @param s adds sprite to list
     */
    public void addSprite(Sprite s) {
        this.sCollection.add(s);

    }

    /**
     *  call timePassed() on all sprites.
     *  @param dt changes based on frames
     */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> copy = new ArrayList<>(this.sCollection);
        for (int i = 0; i < copy.size(); i++) {
            copy.get(i).timePassed(dt);
        }

    }

    /**
     * @param d draws on this surface
     * call drawOn(d) on all sprites.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> copy = new ArrayList<>(this.sCollection);
        for (int i = 0; i < copy.size(); i++) {
            copy.get(i).drawOn(d);
        }

    }

    /**
     *
     * @return collection of sprites.
     */
    public List<Sprite> getsCollection() {
        return sCollection;
    }

    /**
     *
     * @param sprite to remove from list.
     */
    public void remove(Sprite sprite) {
        this.sCollection.remove(sprite);
    }
}
