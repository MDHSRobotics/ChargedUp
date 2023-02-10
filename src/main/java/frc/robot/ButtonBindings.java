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

    }

    // Configure xbox 2 buttons
    public static void configureXbox2ButtonBindings() {
        Logger.setup("Configure Buttons -> Xbox Controller 2...");

        // Pneumatics
        BotControllers.xbox2.btnY.onTrue(BotCommands.toggleClamp);

    }

}
