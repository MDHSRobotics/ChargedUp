
package frc.robot;

import frc.robot.consoles.Logger;
import frc.robot.sensors.DevGyro;
import frc.robot.sensors.DevRevV3ColorSensor;

// This class contains singleton instances of id mapped sensors.
public class BotSensors {

    // Attitude and Heading Reference Systems
    public static final DevGyro gyro = new DevGyro();
    public static final DevRevV3ColorSensor colorSensor = new DevRevV3ColorSensor();

    // This initialization is called in RobotManager at startup.
    public static void initializeSensors() {
        Logger.setup("Initializing BotSensors...");
        gyro.initialize();
        colorSensor.initialize();
    }

}
