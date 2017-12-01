package rungame;

/**
 * counting things.
 */
public class Counter {
    private int value;

    /**
     *
     * @param value of count.
     */
    public Counter(int value) {
        this.value = value;
    }


    /**
     *
     * @param number add number to current count.
     */
    public void increase(int number)  {
        this.value += number;
    }

    /**
     *
     * @param number subtract number from current count.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     *
     * @return // get current count.
     */
    public int getValue() {
        return this.value;
    }

}
