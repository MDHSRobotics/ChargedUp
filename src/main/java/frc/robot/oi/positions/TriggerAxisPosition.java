
package frc.robot.oi.positions;

import frc.robot.oi.controllers.HandHeldPositionAccessible;

// The position values obtained from xbox trigger axis.
public class TriggerAxisPosition {

    public double leftTriggerAxisPosition = 0;
    public double rightTriggerAxisPosition = 0;

    public TriggerAxisPosition() {
    }

    public TriggerAxisPosition(double leftTriggerAxis, double rightTriggerAxis) {
        leftTriggerAxisPosition = leftTriggerAxis;
        rightTriggerAxisPosition = rightTriggerAxis;
    }

    // Gets the xbox trigger axis positions
    public static TriggerAxisPosition getTriggerAxisPositions(HandHeldPositionAccessible controller) {
        TriggerAxisPosition pos = controller.getTriggerAxisPositions();
        return pos;
    }

}
