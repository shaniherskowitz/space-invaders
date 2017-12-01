package spacegame;

import interfaces.LevelInformation;
import interfaces.Sprite;
import shapes.Point;
import sprites.Background;
import sprites.Block;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * space levels.
 */
public class Level implements LevelInformation {
    private int numLevel;
    private double speed;
    private double speedToChange;

    /**
     *
     * @param numl of level.
     * @param aSpeed of level
     */
    public Level(int numl, double aSpeed) {
        numLevel = numl;
        speed = aSpeed;
        speedToChange = speed;
    }

    /**
     *
     * @return paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return 500;
    }

    /**
     *
     * @return paddle width.
     */
    @Override
    public int paddleWidth() {
        return 50;
    }

    /**
     *
     * @return level name.
     */
    @Override
    public String levelName() {
        return "" + numLevel;
    }

    /**
     *
     * @return background.
     */
    @Override
    public Sprite getBackground() {
        return new Background(Color.BLACK, new ArrayList<>(), new ArrayList<>(), levelName());
    }

    /**
     *
     * @return list of blocks for shield.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int j = 500; j < 515; j += 5) {
            for (int i = 80; i < 230; i += 5) {
                Block b = new Block(new Point(i, j), 5, 5);
                b.setColor1(new Color(255, 181, 230));
                b.colorBorder(new Color(255, 181, 230));
                blocks.add(b);
            }
        }
        for (int j = 500; j < 515; j += 5) {
            for (int i = 330; i < 480; i += 5) {
                Block b = new Block(new Point(i, j), 5, 5);
                b.setColor1(new Color(255, 181, 230));
                b.colorBorder(new Color(255, 181, 230));
                blocks.add(b);
            }
        }
        for (int j = 500; j < 515; j += 5) {
            for (int i = 580; i < 730; i += 5) {
                Block b = new Block(new Point(i, j), 5, 5);
                b.setColor1(new Color(255, 181, 230));
                b.colorBorder(new Color(255, 181, 230));
                blocks.add(b);
            }

        }
        return blocks;
    }

    /**
     *
     * @return list of aliens.
     */
    public List<Block> aliens() {
        List<Block> list1 = new ArrayList<>();
        Image img = null;
        try {
            img = ImageIO.read(new File("alien.jpg"));
        } catch (IOException e) {
            System.out.println("problem with alien");
        }
        for (int j = 30; j < 210; j += 40) {
            for (int i = 30; i < 500; i += 50) {
                Block b = new Block(new shapes.Point(i, j), 40, 30, img);
                b.colorBorder(Color.BLACK);
                list1.add(b);

            }
        }
        return list1;
    }

    /**
     *
     * @return aliens speed.
     */
    public double aliensSpeed() {
        return speed;
    }

    /**
     *
     * @param num increase speed by num
     */
    public void setSpeed(double num) {
        speedToChange = num;

    }

    /**
     *
     * @return speed after change.
     */
    public double speedToChange() {
        return speedToChange;
    }


}
