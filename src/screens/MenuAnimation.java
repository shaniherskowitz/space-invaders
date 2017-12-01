package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Menu;
import java.awt.Color;
import java.util.ArrayList;

/**
 * starting menu of game.
 * @param <T> generic type
 */
public class MenuAnimation<T> implements Menu<T> {
    private Boolean stop;
    private KeyboardSensor keyboard;
    private String title;
    private ArrayList<String> keys;
    private ArrayList<String> menuKeys;
    private ArrayList<String> message;
    private ArrayList<String> messageKeys;
    private ArrayList<Menu<T>> menus;
    private ArrayList<T> option;
    private int current;
    private int menuCurrent;
    private boolean isMenu;

    /**
     *
     * constroctor.
     * @param name for menu.
     * @param key to press out
     */
    public MenuAnimation(String name, KeyboardSensor key) {
        this.stop = false;
        this.keyboard = key;
        this.option = new ArrayList<>();
        this.title = name;
        this.keys = new ArrayList<>();
        this.message = new ArrayList<>();
        this.messageKeys = new ArrayList<>();
        this.menus = new ArrayList<>();
        this.menuKeys = new ArrayList<>();
        current = 0;
        menuCurrent = 0;
        isMenu = false;

    }

    /**
     *
     * @param d draws one frame of the game on this surface.
     * @param dt frame rate
     */
    public void doOneFrame(DrawSurface d, double dt) {

        if (isMenu && !menus.get(menuCurrent).shouldStop()) {
                menus.get(menuCurrent).doOneFrame(d, dt);
                stop = menus.get(menuCurrent).shouldStop();

        } else {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, 800, 600);

            d.setColor(new Color(255, 175, 228));
            d.drawText(100, 100, title, 50);


            int count = 150;
            for (int i = 0; i < menuKeys.size(); i++) {
                d.setColor(new Color(137, 4, 93));
                d.drawText(100, count, messageKeys.get(i), 30);
                d.setColor(new Color(226, 90, 147));
                d.drawText(390, count, "Press " + "'" + menuKeys.get(i) + "'", 25);
                count += 50;
            }

            for (int i = 0; i < keys.size(); i++) {
                d.setColor(new Color(137, 4, 93));
                d.drawText(100, count, message.get(i), 30);
                d.setColor(new Color(226, 90, 147));
                d.drawText(390, count, "Press " + "'" + keys.get(i) + "'", 25);
                count += 50;
            }

            for (int j = 0; j < menuKeys.size(); j++) {
                if (keyboard.isPressed(menuKeys.get(j))) {
                    menuCurrent = j;
                    isMenu = true;
                    return;
                }
            }

            for (int j = 0; j < keys.size(); j++) {
                if (keyboard.isPressed(keys.get(j))) {
                    current = j;
                    stop = true;

                }
            }
        }




    }

    /**
     *
     * @return when to stop the animation.
     */
    public boolean shouldStop() {
        return stop;
    }

    /**
     *
     * @param key to wait for.
     * @param message1 what to print
     * @param returnVal what to return
     */

    public void addSelection(String key, String message1, T returnVal) {
        this.keys.add(key);
        this.message.add(message1);
        this.option.add(returnVal);

    }

    /**
     *
     * @return string
     */
    public T getStatus() {
        stop = false;
        if (isMenu) {
            isMenu = false;
            return menus.get(menuCurrent).getStatus();

        }
        return this.option.get(current);
    }

    /**
     *
     * @param key to get to menu.
     * @param message1 to show
     * @param subMenu to add
     */
    @Override
    public void addSubMenu(String key, String message1, Menu<T> subMenu) {
        //this.keys.add(key);
        this.menuKeys.add(key);
        this.messageKeys.add(message1);
        this.menus.add(subMenu);


    }


}
