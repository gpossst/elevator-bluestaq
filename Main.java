/*
 * Main class for the elevator simulation
 * Runs a loop that polls the button panel for a request and runs the elevator
 * Currently hardcoded to 10 floors, but could be passed in as an argument
 */
public class Main {
    public static void main(String[] args) {   
        int floorCount = 10;
        Elevator elevator = new Elevator(floorCount);
        ButtonPanel buttonPanel = new ButtonPanel(floorCount);
        
        while (true) {
            // Get request (returns -1 if no input)
            int request = buttonPanel.getRequest();
            
            // Run elevator step (handles both new requests and existing queue)
            elevator.run(request);
            
            // Poll every second
            Clock.wait(1);
        }
    }
}