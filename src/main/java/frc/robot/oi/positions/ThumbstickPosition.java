
package frc.robot.oi.positions;

import frc.robot.brains.XboxBrain;
import frc.robot.oi.controllers.XboxPositionAccessible;

// The position values obtained from xbox thumbsticks.
public class ThumbstickPosition {

    public double leftForwardBackPosition = 0; // y left Forward & Backward
    public double leftSideToSidePosition = 0; // x left Side to Side
    public double rightForwardBackPosition = 0; // y right Forward & Backward
    public double rightSideToSidePosition = 0; // x right Side to Side

    public ThumbstickPosition() {
    }

    public ThumbstickPosition(double leftForwardBack, double leftSideToSide, double rightForwardBack, double rightSideToSide) {
        leftForwardBackPosition = leftForwardBack;
        leftSideToSidePosition = leftSideToSide;
        rightForwardBackPosition = rightForwardBack;
        rightSideToSidePosition = rightSideToSide;
    }

    // Gets the xbox thumbstick positions and applies user-determined orientation, deadzones, and sensitivity
    public static ThumbstickPosition getPositions(XboxPositionAccessible controller, boolean isYleftFlipped) {
        ThumbstickPosition pos = controller.getThumbstickPositions(isYleftFlipped);

        double yLeft = pos.leftForwardBackPosition; // Left forward & backward, flipped
        double xLeft = pos.leftSideToSidePosition; // Left side to side
        double yRight = pos.rightForwardBackPosition; // Right forward & backward, flipped
        double xRight = pos.rightSideToSidePosition; // Right side to side

        // Forward/backward direction is reversed from what is intuitive, so flip it
        yLeft = -yLeft;
        yRight = -yRight;

        // User-determined flipping of forward/backward orientation
        if (isYleftFlipped) {
            yLeft = -yLeft;
            yRight = -yRight;
        }

        // Deadzones
        double yLeftDeadZone = XboxBrain.getYleftDeadZone();
        double xLeftDeadZone = XboxBrain.getXleftDeadZone();
        double yRightDeadZone = XboxBrain.getYrightDeadZone();
        double xRightDeadZone = XboxBrain.getXrightDeadZone();

        if (Math.abs(yLeft) <= yLeftDeadZone) yLeft = 0;
        if (Math.abs(xLeft) <= xLeftDeadZone) xLeft = 0;
        if (Math.abs(yRight) <= yRightDeadZone) yRight = 0;
        if (Math.abs(xRight) <= xRightDeadZone) xRight = 0;

        if (yLeft > 0) yLeft = yLeft - yLeftDeadZone;
        if (yLeft < 0) yLeft = yLeft + yLeftDeadZone;
        if (xLeft > 0) xLeft = xLeft - xLeftDeadZone;
        if (xLeft < 0) xLeft = xLeft + xLeftDeadZone;
        if (yRight > 0) yRight = yRight - yRightDeadZone;
        if (yRight < 0) yRight = yRight + yRightDeadZone;
        if (xRight > 0) xRight = xRight - xRightDeadZone;
        if (xRight < 0) xRight = xRight + xRightDeadZone;

        // Sensitivity
        double yLeftSensitivity = XboxBrain.getYleftSensitivity();
        double xLeftSensitivity = XboxBrain.getXleftSensitivity();
        double yRightSensitivity = XboxBrain.getYrightSensitivity();
        double xRightSensitivity = XboxBrain.getXrightSensitivity();

        pos.leftForwardBackPosition = yLeft * yLeftSensitivity;
        pos.leftSideToSidePosition = xLeft * xLeftSensitivity;
        pos.rightForwardBackPosition = yRight * yRightSensitivity;
        pos.rightSideToSidePosition = xRight * xRightSensitivity;

        return pos;
    }

    // TODO: Also consider adding a "debouncer" for the buttons
    // https://frc-pdr.readthedocs.io/en/latest/user_input/joystick.html
}
