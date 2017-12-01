package interfaces;

/**
 * runs the task.
 *  @param <T> generic type
 */
public interface Task<T> {
    /**
     *
     * @return runs the task.
     */
    T run();
}
