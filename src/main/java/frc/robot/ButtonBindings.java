package frc.robot;

import frc.robot.consoles.Logger;

// Configures all the button->command bindings for the robot.
public class ButtonBindings {

    // Configure jstick buttons
    public static void configureJoystick() {
        Logger.setup("Configure Buttons -> Jstick Controller...");

    }

    // Configure xbox 1 buttons
    public static void configureXbox1ButtonBindings() {
        Logger.setup("Configure Buttons -> Xbox Controller 1...");

        //Change Speed
        BotControllers.xbox1.btnY.onTrue(BotCommands.speedUpDrive);
        BotControllers.xbox1.btnA.onTrue(BotCommands.slowDownDrive);

        //Toggle Feild Orientated Driving
        BotControllers.xbox1.btnB.onTrue(BotCommands.toggleOrientation);

    }

    // Configure xbox 2 buttons
    public static void configureXbox2ButtonBindings() {
        Logger.setup("Configure Buttons -> Xbox Controller 2...");

        //Reset to default command
        BotControllers.xbox2.btnB.onTrue(BotCommands.moveForklift);

        //Reset the encoders
        BotControllers.xbox2.btnDpadDown.onTrue(BotCommands.resetEncoders);

        //Autonomous Movements
        BotControllers.xbox2.btnA.onTrue(BotCommands.forkliftPickUpPosition);
        BotControllers.xbox2.btnY.onTrue(BotCommands.forkliftHighLevelPosition);
        BotControllers.xbox2.btnX.onTrue(BotCommands.forkliftMediumLevelPosition);

        // Pneumatics
        //BotControllers.xbox2.btnBumperLeft.onTrue(BotCommands.openClamp);
        //BotControllers.xbox2.btnBumperRight.onTrue(BotCommands.closeClamp);

    }

}
