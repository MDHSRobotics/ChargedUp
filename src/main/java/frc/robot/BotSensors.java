
package frc.robot;

import static frc.robot.RobotManager.isReal;

import frc.robot.consoles.Logger;
import frc.robot.sensors.DevGyro;
import frc.robot.sensors.Limelight;

// This class contains singleton instances of id mapped sensors.
public class BotSensors {

    // Attitude and Heading Reference Systems
    public static final DevGyro gyro = new DevGyro();
    public static final Limelight limelight = new Limelight();

    // This initialization is called in RobotManager at startup.
    public static void initializeSensors() {
        Logger.setup("Initializing BotSensors...");
        gyro.initialize();
        if (isReal) {
            Limelight.setPipeline(1);
            Limelight.setCamMode(0);
        } else {
            Logger.setup("Skipping initializion of sensors in Simulation mode...");
        }
    }

}
