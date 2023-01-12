
package frc.robot;

import static frc.robot.RobotManager.isReal;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

import frc.robot.consoles.Logger;
import frc.robot.sensors.Gyro;

// This class contains singleton instances of id mapped sensors.
public class BotSensors {

    // Attitude and Heading Reference Systems
    public static final AHRS gyro = new AHRS(SPI.Port.kMXP);

    // This initialization is called in RobotManager at startup.
    public static void initializeSensors() {
        Logger.setup("Initializing BotSensors...");
        if (isReal) {
            Gyro.initializeGyro(gyro);
        } else {
            Logger.setup("Skipping initializion of sensors in Simulation mode...");
        }
    }

}
