package frc.robot.oi.controllers;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.oi.positions.ThumbstickPosition;
import frc.robot.oi.positions.TriggerAxisPosition;

// This class contains a xbox controller and properties for all its buttons.
public class XboxControllerContainer extends ControllerContainer implements HandHeldPositionAccessible {

    public Trigger btnA;
    public Trigger btnB;
    public Trigger btnX;
    public Trigger btnY;
    public Trigger btnBack;
    public Trigger btnStart;
    public Trigger btnBumperLeft;
    public Trigger btnBumperRight;
    public Trigger btnStickLeft;
    public Trigger btnStickRight;
    public Trigger btnDpadUp;
    public Trigger btnDpadDown;
    public Trigger btnDpadLeft;
    public Trigger btnDpadRight;
    public Trigger btnDpadUpLeft;
    public Trigger btnDpadUpRight;
    public Trigger btnDpadDownLeft;
    public Trigger btnDpadDownRight;
    public Trigger btnBumperBoth;
    //Command Controller
    public CommandXboxController xbox = new CommandXboxController(port);

    public XboxControllerContainer(int port) {
        super(port);
        
        btnA = xbox.a();
        btnB = xbox.b();
        btnX = xbox.x();
        btnY = xbox.y();
        btnBack = xbox.back();
        btnStart = xbox.start();
        btnBumperLeft = xbox.leftBumper();
        btnBumperRight = xbox.rightBumper();
        btnStickLeft = xbox.leftStick();
        btnStickRight = xbox.rightStick();
        btnDpadUp = xbox.povUp();
        btnDpadDown = xbox.povDown();
        btnDpadLeft = xbox.povLeft();
        btnDpadRight = xbox.povRight();
        btnDpadUpLeft = xbox.povUpLeft();
        btnDpadUpRight = xbox.povUpRight();
        btnDpadDownLeft = xbox.povDownLeft();
        btnDpadDownRight = xbox.povDownRight();
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
