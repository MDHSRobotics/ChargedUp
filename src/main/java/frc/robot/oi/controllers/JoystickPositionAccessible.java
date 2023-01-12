
package frc.robot.oi.controllers;

import frc.robot.oi.positions.JoystickPosition;

// Classes that implement this interface must define methods
// for obtaining joystick position data.
public interface JoystickPositionAccessible {

    public JoystickPosition getJoystickPosition(boolean isYflipped);

}