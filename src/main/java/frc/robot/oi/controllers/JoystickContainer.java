
package frc.robot.oi.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.oi.positions.JoystickPosition;

// This class contains an joystick and properties for all its buttons.
public class JoystickContainer extends ControllerContainer implements JoystickPositionAccessible {

    public Joystick jstick;
    public JoystickButton jstickBtn1;
    public JoystickButton jstickBtn2;
    public JoystickButton jstickBtn3;
    public JoystickButton jstickBtn4;
    public JoystickButton jstickBtn5;
    public JoystickButton jstickBtn6;
    public JoystickButton jstickBtn7;
    public JoystickButton jstickBtn8;
    public JoystickButton jstickBtn9;
    public JoystickButton jstickBtn10;
    public JoystickButton jstickBtn11;
    public JoystickButton jstickBtn12;

    public JoystickContainer (int port) {
        super(port);
        jstick = new Joystick(port);
        jstickBtn1 = new JoystickButton(jstick, 1); 
        jstickBtn2 = new JoystickButton(jstick, 2);
        jstickBtn3 = new JoystickButton(jstick, 3);
        jstickBtn4 = new JoystickButton(jstick, 4);
        jstickBtn5 = new JoystickButton(jstick, 5);
        jstickBtn6 = new JoystickButton(jstick, 6);
        jstickBtn7 = new JoystickButton(jstick, 7);
        jstickBtn8 = new JoystickButton(jstick, 8);
        jstickBtn9 = new JoystickButton(jstick, 9);
        jstickBtn10 = new JoystickButton(jstick, 10);
        jstickBtn11 = new JoystickButton(jstick, 11);
        jstickBtn12 = new JoystickButton(jstick, 12);
    }

    // Gets the raw joystick positions
    public JoystickPosition getJoystickPosition(boolean isYflipped) {
        double y = jstick.getY(); // Forward & backward, flipped
        double x = jstick.getX(); // Side to side
        double z = jstick.getZ(); // Rotate, flipped

        JoystickPosition pos = new JoystickPosition(y, x, z);
        return pos;
    }

}
