
package frc.robot.oi.controllers;

import edu.wpi.first.wpilibj.DriverStation;

// This is a base class for controller containers.
public class ControllerContainer {

    public int port;
    public boolean configured = false;

    public ControllerContainer(int port) {
        this.port = port;
    }

    // Determine if the controller is connected
    public boolean isConnected() {
        int numberOfButtons = DriverStation.getStickButtonCount(port);
        return numberOfButtons > 0;
    }

}
