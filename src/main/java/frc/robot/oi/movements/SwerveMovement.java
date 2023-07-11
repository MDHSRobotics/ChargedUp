
package frc.robot.oi.movements;

import frc.robot.oi.controllers.JoystickPositionAccessible;
import frc.robot.oi.controllers.HandHeldPositionAccessible;
import frc.robot.oi.positions.JoystickPosition;
import frc.robot.oi.positions.ThumbstickPosition;

// The values needed to drive with SwerveDriver.
public class SwerveMovement {

    public double forwardBackwardSpeed = 0; // Forward & Backward
    public double sideToSideSpeed = 0; // Side to Side Speed
    public double rotationSideToSideSpeed = 0; // Rotation x value
    public double rotationForwardBackwardSpeed = 0; // Rotation y value

    public SwerveMovement(double xLeftSpeed, double yLeftSpeed, double thetaSpeed) {
        forwardBackwardSpeed = xLeftSpeed;
        sideToSideSpeed = yLeftSpeed;
        rotationForwardBackwardSpeed = thetaSpeed;
        rotationSideToSideSpeed = thetaSpeed;
    }

    public SwerveMovement(double xLeftSpeed, double yLeftSpeed, double xRightSpeed, double yRightSpeed) {
        forwardBackwardSpeed = xLeftSpeed;
        sideToSideSpeed = yLeftSpeed;
        rotationForwardBackwardSpeed = xRightSpeed;
        rotationSideToSideSpeed = yRightSpeed;
    }

    // Determines the Swerve movement (straight speed, side speed, rotation speed) from the given jstick/thumbstick positions
    public static SwerveMovement getMovement(JoystickPositionAccessible controller, boolean isYFlipped) {
        JoystickPosition pos = JoystickPosition.getJoystickPosition(controller, isYFlipped);
        SwerveMovement move = new SwerveMovement(pos.forwardBackPosition, pos.sideToSidePosition, pos.rotationPosition);
        return move;
    }

    public static SwerveMovement getMovement(HandHeldPositionAccessible controller, boolean isYFlipped) {
        ThumbstickPosition pos = ThumbstickPosition.getPositions(controller, isYFlipped);
        SwerveMovement move = new SwerveMovement(pos.leftForwardBackPosition, pos.leftSideToSidePosition, pos.rightForwardBackPosition, pos.rightSideToSidePosition);
        return move;
    }

}
