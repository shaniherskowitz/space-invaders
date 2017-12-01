package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;
import rungame.GameLevel;
import java.awt.Color;
import java.util.List;

/**
 * background of the game.
 */
public class Background implements Sprite {
    private Color mainColor;
    private List<Block> blocks;
    private List<Ball> balls;
    private String levelName;

    /**
     *
     * @param color of background.
     * @param block list of blocks
     * @param circles list of balls
     * @param name of level
     */
    public Background(Color color, List<Block> block, List<Ball> circles, String name) {
        this.mainColor = color;
        this.blocks = block;
        this.balls = circles;
        this.levelName = name;
    }

    /**
     * draw the sprite to the screen.
     * @param d surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.mainColor);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        for (int i = 0; i < this.blocks.size(); i++) {
            this.blocks.get(i).drawOn(d);
        }
        for (int i = 0; i < this.balls.size(); i++) {
            this.balls.get(i).drawOn(d);
        }
    }

    /**
     *
     * @param dt frame rate
     */
    public void timePassed(double dt) {

    }

    /**
     * adds item to game.
     * @param g game to add it to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     *
     * @return color of level.
     */
    public Color getColor() {
        return this.mainColor;
    }
}
