
package frc.robot;

import frc.robot.consoles.Logger;
import frc.robot.oi.controllers.JoystickContainer;
import frc.robot.oi.controllers.XboxControllerContainer;

// This class contains the robot controllers and defined ports.
public class BotControllers {

    // Controllers
    public static final JoystickContainer jstick = new JoystickContainer(0);
    public static final XboxControllerContainer xbox = new XboxControllerContainer(1);
    public static final XboxControllerContainer xbox2 = new XboxControllerContainer(2);


    // Configure all the controllers
    public static void configure() {
        configureJoystick();
        configureXbox();
    }

    // Configure the jstick controller
    public static void configureJoystick() {
        // Detect whether the jstick controller has been plugged in after start-up
        if (!BotControllers.jstick.configured) {
            boolean isConnected = BotControllers.jstick.isConnected();
            if (!isConnected) return;

            // Configure button bindings
            ButtonBindings.configureJoystick();
            BotControllers.jstick.configured = true;
            Logger.setup("Joystick controller detected and configured");
        }
    }

    // Configure the xbox controller
    public static void configureXbox() {
        // Detect whether the xbox controller has been plugged in after start-up
        if (!BotControllers.xbox.configured) {
            boolean isConnected = BotControllers.xbox.isConnected();
            if (!isConnected) return;

            // Configure button bindings
            ButtonBindings.configureXbox();
            BotControllers.xbox.configured = true;
            Logger.setup("Xbox controller detected and configured");
        }
    }

}
