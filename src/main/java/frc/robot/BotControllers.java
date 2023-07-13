
package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import frc.robot.consoles.Logger;
import frc.robot.oi.controllers.JoystickContainer;
import frc.robot.oi.controllers.PS4ControllerContainer;
import frc.robot.oi.controllers.XboxControllerContainer;

// This class contains the robot controllers and defined ports.
public class BotControllers {

    // Controllers
    public static final PS4ControllerContainer xbox1 = new PS4ControllerContainer(1);
    public static final XboxControllerContainer xbox2 = new XboxControllerContainer(2);
    public static final JoystickContainer jstick = new JoystickContainer(0);

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
        if (!BotControllers.xbox1.configured) {
            boolean isConnected = BotControllers.xbox1.isConnected();
            if (isConnected){

                // Configure button bindings
                ButtonBindings.configureXbox1ButtonBindings();
                BotControllers.xbox1.configured = true;
                Logger.setup("Xbox controller 1 detected and configured");
            }

        }

        if (!BotControllers.xbox2.configured) {
            boolean isConnected = BotControllers.xbox2.isConnected();
            if (isConnected){
                
                // Configure button bindings
                ButtonBindings.configureXbox2ButtonBindings();
                BotControllers.xbox2.configured = true;
                Logger.setup("Xbox controller 2 detected and configured");
            }

        }
    }

}
