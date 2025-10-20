/*
 * Elevator class is responsible for moving, storing current location, and storing runtime
 */
public class Elevator {
    private int currentFloor;
    private Control control;
    private int runtime;
    
    /*
     * Initialize the elevator with the number of floors
     * @param floorCount the number of floors in the building
     */
    public Elevator(int floorCount) {
        this.currentFloor = 0;
        this.control = new Control(floorCount);
        this.runtime = 0;
    }

    /*
     * Moves the elevator to the destination floor
     * Simulates the elevator's movement time by sleeping for 1 second per floor (arbitrary time)
     * @param destination the floor to move to
     * @return true if the floor was visited, false if the floor was not in the queue
     */
    public boolean move(int destination) {
        if (destination > this.currentFloor) {
            this.currentFloor++;
            Clock.wait(1);
        } else if (destination < this.currentFloor) {
            this.currentFloor--;
            Clock.wait(1);
        }
        
        // this floor is visited and someone wants to be picked up, we'll do so
        return control.visitFloor(this.currentFloor);
    }

    /*
     * Main loop for the elevator, technically runs forever until the elevator is turned off
     * @param request the request from the button panel, or -1 if no request is available
     * @return true if the elevator has a new destination, false if the elevator is idle
     */
    public boolean run(int request) {
        this.runtime++;
        System.out.println("Running " + this.runtime);
        if (request != -1) { 
            control.addFloor(request);
        }
        System.out.println("Location: " + this.currentFloor);

        // get the next destination from the control class
        int destination = control.getDestination(currentFloor);
        if (destination == -1) {
            return false;
        }

        this.move(destination);
        return true;
    }
}
