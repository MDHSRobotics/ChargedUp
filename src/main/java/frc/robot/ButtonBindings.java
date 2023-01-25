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

        // Pneumatics
        BotControllers.xbox.btnY.onTrue(BotCommands.toggleClamp);

    }

}
