package frc.robot;

import frc.robot.consoles.Logger;

// Configures all the button->command bindings for the robot.
public class ButtonBindings {

    // Configure jstick buttons
    public static void configureJoystick() {
        Logger.setup("Configure Buttons -> Jstick Controller...");

    }

    // Configure xbox buttons
    public static void configureXbox() {
        Logger.setup("Configure Buttons -> Xbox Controller...");

        //Clamp
        BotControllers.xbox.btnX.onTrue(BotCommands.openClampForklift);
        BotControllers.xbox.btnB.onTrue(BotCommands.closeClampForklift);
        // SwerveDrive
        //BotControllers.xbox.btnStart.whenPressed(BotCommands.toggleDriverOrientation);

    }

}
