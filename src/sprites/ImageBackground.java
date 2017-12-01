package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;
import rungame.GameLevel;
import java.awt.Image;


/**
 * draws background from image.
 */
public class ImageBackground implements Sprite {
    private Image img;

    /**
     *
     * @param img1 to draw on background.
     */
    public ImageBackground(Image img1) {

        this.img = img1;
    }

    /**
     * @param d surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, img);
    }

    /**
     * @param dt frame rate.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     *
     * @param g game to add it to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
