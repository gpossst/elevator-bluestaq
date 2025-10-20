/*
 * Direction enum is responsible for holding the direction the elevator is moving
 */
enum Direction {
    UP,
    DOWN,
    IDLE
}

/*
 * Control class is responsible for holding location queue, and sending the elevator to the next location
 */
public class Control {
    boolean[] floors;
    Direction direction;
    int queueSize;

    /*
     * Initialize the control class with the number of floors
     * @param floorCount the number of floors in the building
     */
    public Control(int floorCount) {
        floors = new boolean[floorCount];
        direction = Direction.IDLE;
        queueSize = 0;
    }

    /*
     * Add a floor to the queue. Really just sets that floor to true in the floors array and does some input validation
     * @param floor the floor to add
     * @return true if the floor was added, false if the floor was already in the queue or if the floor is invalid
     */
    public boolean addFloor(int floor) {
        if (floor < 0 || floor >= floors.length) {
            return false;
        }

        if (floors[floor]) {
            return false;
        }

        floors[floor] = true;
        queueSize++;
        return true;
    }

    /*
     * Visit a floor. Simulates time to visit floor and removes the floor from the queue if it was in the queue
     * @param currentFloor the current floor the elevator is on
     * @return true if the floor was visited, false if the floor was not in the queue
     */
    public boolean visitFloor(int currentFloor) {
        if (floors[currentFloor]) {
            System.out.println("Visiting floor: " + currentFloor);
            Clock.wait(3); // simulate time to visit floor
            floors[currentFloor] = false;
            queueSize--;
            return true;
        }
        return false;
    }

    /*
     * Get the next destination for the elevator. Checks the queue for the next floor to visit
     * @param currentFloor the current floor the elevator is on
     * @return the next floor to visit, or -1 if no destination is found
     */
    public int getDestination(int currentFloor) {
        // if we're moving up (or idle), we check the up floors first, then switch to down and check the down floors
        if (direction == Direction.UP || direction == Direction.IDLE) {
            for (int i = currentFloor; i < this.floors.length; i++) {
                if (this.floors[i]) {
                    return i;
                }
            }
            direction = Direction.DOWN; // nothing above, so we start moving down
            for (int i = currentFloor; i >= 0; i--) {
                if (this.floors[i]) {
                    return i;
                }
            }
        // if we're moving down, we check the down floors first, then switch to up and check the up floors
        } else {
            for (int i = currentFloor; i >= 0; i--) {
                if (this.floors[i]) {
                    return i;
                }
            }
            this.direction = Direction.UP; // nothing below, so we start moving up
            for (int i = currentFloor; i < this.floors.length; i++) {
                if (this.floors[i]) {
                    return i;
                }
            }
        }
        // No destination found, return -1 or home floor
        this.direction = Direction.IDLE; // this means every value in the queue has been visited and we're idle

        // arbitrary floor close to ground floor, where traffic will be highest
        if (currentFloor != Math.floorDiv(this.floors.length, 3)) {
            return Math.floorDiv(this.floors.length, 3);
        }

        return -1; // no destination found and we're already at the "home" floor
    }
}
