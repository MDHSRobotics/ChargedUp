
package frc.robot.oi.controllers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.oi.positions.ThumbstickPosition;
import frc.robot.oi.positions.TriggerAxisPosition;

// This class contains an xbox controller and properties for all its buttons.
public class XboxControllerContainer extends ControllerContainer implements XboxPositionAccessible {

    public XboxController xbox;
    public JoystickButton btnA;
    public JoystickButton btnB;
    public JoystickButton btnX;
    public JoystickButton btnY;
    public JoystickButton btnBumperLeft;
    public JoystickButton btnBumperRight;
    public JoystickButton btnBack;
    public JoystickButton btnStart;
    public JoystickButton btnStickLeft;
    public JoystickButton btnStickRight;
    public Trigger btnDpadUp;
    public Trigger btnDpadDown;
    public Trigger btnDpadLeft;
    public Trigger btnDpadRight;
    public Trigger btnDpadUpLeft;
    public Trigger btnDpadUpRight;
    public Trigger btnDpadDownLeft;
    public Trigger btnDpadDownRight;
    public Trigger btnBumperBoth;

    public XboxControllerContainer(int port) {
        super(port);
        CommandXboxController commandXboxController = new CommandXboxController(port);
    
        xbox = new XboxController(port);
        btnA = new JoystickButton(xbox, 1);
        btnB = new JoystickButton(xbox, 2);
        btnX = new JoystickButton(xbox, 3);
        btnY = new JoystickButton(xbox, 4);
        btnBumperLeft = new JoystickButton(xbox, 5);
        btnBumperRight = new JoystickButton(xbox, 6);
        btnBack = new JoystickButton(xbox, 7);
        btnStart = new JoystickButton(xbox, 8);
        btnStickLeft = new JoystickButton(xbox, 9);
        btnStickRight = new JoystickButton(xbox, 10);
        btnDpadUp = commandXboxController.povUp();
        btnDpadDown = commandXboxController.povDown();
        btnDpadLeft = commandXboxController.povLeft();
        btnDpadRight = commandXboxController.povRight();
        btnDpadUpLeft = commandXboxController.povUpLeft();
        btnDpadUpRight = commandXboxController.povUpRight();
        btnDpadDownLeft = commandXboxController.povDownLeft();
        btnDpadDownRight = commandXboxController.povDownRight();
        btnBumperBoth = btnBumperRight.and(btnBumperLeft);
    }

    // Gets the raw xbox thumbstick positions
    public ThumbstickPosition getThumbstickPositions(boolean isYleftFlipped) {
        if (!isConnected()) return new ThumbstickPosition();

        double yLeft = xbox.getLeftY(); // Forward & backward, flipped
        double xLeft = xbox.getLeftX(); // Strafe
        double yRight = xbox.getRightY(); // Forward & backward, flipped
        double xRight = xbox.getRightX(); // Rotate

        ThumbstickPosition pos = new ThumbstickPosition(yLeft, xLeft, yRight, xRight);
        return pos;
    }

    // Gets the raw xbox trigger positions
    public TriggerAxisPosition getTriggerAxisPositions() {
        if (!isConnected()) return new TriggerAxisPosition();

        double leftTriggerAxis = xbox.getLeftTriggerAxis();
        double rightTriggerAxis = xbox.getRightTriggerAxis();

        TriggerAxisPosition pos = new TriggerAxisPosition(leftTriggerAxis, rightTriggerAxis);
        return pos;
    }

}
