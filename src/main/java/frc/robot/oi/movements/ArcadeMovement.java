
package frc.robot.oi.movements;

import frc.robot.oi.controllers.XboxPositionAccessible;
import frc.robot.oi.positions.ThumbstickPosition;

// The values needed to drive using arcade mode.
public class ArcadeMovement {

    public double straightSpeed = 0; // x Forward & Backward
    public double rotationSpeed = 0; // z Rotate

    public ArcadeMovement() {
    }

    public ArcadeMovement(double xStraightSpeed, double zRotationSpeed) {
        straightSpeed = xStraightSpeed;
        rotationSpeed = zRotationSpeed;
    }

    // Determines the arcade movement from the given xbox thumbstick position(s)
    public static ArcadeMovement getMovement(XboxPositionAccessible controller, boolean isYleftFlipped) {
        ThumbstickPosition pos = ThumbstickPosition.getPositions(controller, isYleftFlipped);
        ArcadeMovement move = new ArcadeMovement(pos.leftForwardBackPosition, pos.leftSideToSidePosition);
        return move;
    }
    
}
