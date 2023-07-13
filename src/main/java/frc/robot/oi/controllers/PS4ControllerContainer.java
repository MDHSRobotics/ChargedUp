package frc.robot.oi.controllers;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.oi.positions.ThumbstickPosition;
import frc.robot.oi.positions.TriggerAxisPosition;

// This class contains a ps4 controller and properties for all its buttons.
public class PS4ControllerContainer extends ControllerContainer implements HandHeldPositionAccessible{

    public CommandPS4Controller ps4 = new CommandPS4Controller(port);
    public PS4Controller regps4 = new PS4Controller(port);

    public Trigger btnCircle;
    public Trigger btnCross;
    public Trigger btnSquare;
    public Trigger btnTriangle;
    public Trigger btnOptions;
    public Trigger btnShare;
    public Trigger btnPs;
    public Trigger btnTouchpad;
    public Trigger btnL1;
    public Trigger btnL2;
    public Trigger btnL3;
    public Trigger btnR1;
    public Trigger btnR2;
    public Trigger btnR3;
    public Trigger btnDpadUp;
    public Trigger btnDpadDown;
    public Trigger btnDpadLeft;
    public Trigger btnDpadRight;
    public Trigger btnDpadUpLeft;
    public Trigger btnDpadUpRight;
    public Trigger btnDpadDownLeft;
    public Trigger btnDpadDownRight;

    public PS4ControllerContainer(int port) {
        super(port);
        
        btnCircle = ps4.circle();
        btnCross = ps4.cross();
        btnSquare = ps4.square();
        btnTriangle = ps4.triangle();
        btnOptions = ps4.options();
        btnShare = ps4.share();
        btnPs = ps4.PS();
        btnTouchpad = ps4.touchpad();
        btnL1 = ps4.L1();
        btnL2 = ps4.L2();
        btnL3 = ps4.L3();
        btnR1 = ps4.R1();
        btnR2 = ps4.R2();
        btnR3 = ps4.R3();
        btnDpadUp = ps4.povUp();
        btnDpadDown = ps4.povDown();
        btnDpadLeft = ps4.povLeft();
        btnDpadRight = ps4.povRight();
        btnDpadUpLeft = ps4.povUpLeft();
        btnDpadUpRight = ps4.povUpRight();
        btnDpadDownLeft = ps4.povDownLeft();
        btnDpadDownRight = ps4.povDownRight();
    }

    public ThumbstickPosition getThumbstickPositions(boolean isYleftFlipped) {
        if (!isConnected()) return new ThumbstickPosition();

        double yLeft = ps4.getLeftY(); // Forward & backward, flipped
        double xLeft = ps4.getLeftX(); // Strafe
        double yRight = ps4.getRightY(); // Forward & backward, flipped
        double xRight = ps4.getRightX(); // Rotate

        ThumbstickPosition pos = new ThumbstickPosition(yLeft, xLeft, yRight, xRight);
        return pos;
    }

    public TriggerAxisPosition getTriggerAxisPositions() {
        if (!isConnected()) return new TriggerAxisPosition();

        double leftTriggerAxis = ps4.getL2Axis();
        double rightTriggerAxis = ps4.getR2Axis();

        TriggerAxisPosition pos = new TriggerAxisPosition(leftTriggerAxis, rightTriggerAxis);
        return pos;
    }


}
