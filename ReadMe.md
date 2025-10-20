# Elevator Simulation

## Bluestaq Take Home Challenge

### Assumptions

- There is a continuous input from users
- There are 10 floors (can be directly modified for more)
- The optimal "home" location for the elevator is the floor at 1/3 of the total number of floors - close to the base but also doesn't have to traverse the entire building when a high floor is selected

### Unimplemented Features

- Any type of UI, currently takes inputs directly from console
- Multiple input values, like how elevators have panels on different floors
  - In this situation, we would create a request value that would hold whether a user on a floor wanted to go up or down, but because there's no direct association with inputs and floors right now, it's infeasible
- Different threads for elevator and input could be implemented, but felt it unnecessary for the project
- Proper runtime, at this point it just counts cycles in the loop but that's not actual runtime because we wait at different points and it takes time to run the code

### Notes

- Elevator class is responsible for moving, storing current location
- Control class is responsible for holding location queue, and sending the elevator to the next location
- Clock class is responsible solely for timing, used to simulate real world elevator movement time
- ButtonPanel class is responsible for getting user input and returning it to the elevator
- POSSIBLY: Request class to be passed to elevator.run to hold whether a user on a floor wanted to go up or down
