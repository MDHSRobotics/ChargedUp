
package frc.robot.oi.movements;

import frc.robot.oi.controllers.XboxPositionAccessible;
import frc.robot.oi.positions.ThumbstickPosition;

// The values needed to drive using tank mode.
public class TankMovement {

    public double leftSpeed = 0; // x left Forward & Backward
    public double rightSpeed = 0; // x right Forward & Backward

    public TankMovement() {
    }

    public TankMovement(double xLeftSpeed, double xRightSpeed) {
        leftSpeed = xLeftSpeed;
        rightSpeed = xRightSpeed;
    }

    // Determines the tank movement (left-side straight speed, right-side straight speed)
    // from the given xbox thumbstick positions
    public static TankMovement getMovement(XboxPositionAccessible controller, boolean isYflipped) {
        ThumbstickPosition pos = ThumbstickPosition.getPositions(controller, isYflipped);
        TankMovement move = new TankMovement(pos.leftForwardBackPosition, pos.rightForwardBackPosition);
        return move;
    }

}
