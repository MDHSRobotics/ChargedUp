
package frc.robot.oi.movements;

import frc.robot.oi.controllers.XboxPositionAccessible;
import frc.robot.oi.positions.ThumbstickPosition;
import frc.robot.oi.positions.TriggerAxisPosition;

// This class contains the methods needed to determine conveyor speed.
public class ConveyMovement {

    // Determines speed of conveyor belt from the right thumbstick and trigger
    public static double getConveySpeed(XboxPositionAccessible controller) {
        ThumbstickPosition thumbPos = ThumbstickPosition.getPositions(controller, false);
        double thumbstick = thumbPos.rightSideToSidePosition;

        TriggerAxisPosition triggerPos = TriggerAxisPosition.getTriggerAxisPositions(controller);
        double trigger = triggerPos.rightTriggerAxisPosition;

        return thumbstick - trigger;
    }

}
