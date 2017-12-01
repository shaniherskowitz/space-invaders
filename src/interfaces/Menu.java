package interfaces;

/**
 * menu of game.
 * @param <T> generic type
 */
public interface Menu<T> extends Animation {

    /**
     *
     * @param key to press.
     * @param message to display
     * @param returnVal after selection
     */
    void addSelection(String key, String message, T returnVal);

    /**
     *
     * @return of screen
     */
    T getStatus();

    /**
     *
     * @param key to get to menu.
     * @param message to show
     * @param subMenu to add
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

}
