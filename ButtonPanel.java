import java.util.Scanner;
import java.io.IOException;

/*
 * ButtonPanel class is responsible for getting user input and returning it to the elevator
 */
public class ButtonPanel {
    private int floorCount;
    private Scanner scanner;

    /*
     * Initialize the button panel with the number of floors
     * @param floorCount the number of floors in the building
     */
    public ButtonPanel(int floorCount) {
        this.floorCount = floorCount;
        this.scanner = new Scanner(System.in);
    }

    /*
     * Get the next request from the button panel
     * @return the next request, or -1 if no request is available
     */
    public Integer getRequest() {
        try {
            // Check if input is available without blocking
            if (System.in.available() > 0) {
                if (scanner.hasNextInt()) {
                    int floor = scanner.nextInt();
                    if (floor < 0 || floor >= this.floorCount) {
                        System.out.println("Invalid floor number");
                        return -1;
                    }
                    System.out.println("Floor " + floor + " requested");
                    return floor;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1; // No input available
    }
}
