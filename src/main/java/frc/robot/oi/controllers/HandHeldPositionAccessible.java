
package frc.robot.oi.controllers;

import frc.robot.oi.positions.ThumbstickPosition;
import frc.robot.oi.positions.TriggerAxisPosition;

// Classes that implement this interface must define methods
// for obtaining xbox thumbstick and trigger axis position data.
public interface HandHeldPositionAccessible {

    public ThumbstickPosition getThumbstickPositions(boolean isYleftFlipped);

    public TriggerAxisPosition getTriggerAxisPositions();

}
