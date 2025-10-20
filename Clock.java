/*
 * Clock class is responsible for timing, used to simulate real world elevator movement time
 */
public class Clock {
    /*
     * Waits for the specified number of seconds
     * Used to simulate the elevator's movement time and to prevent repeated polling
     */
    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
