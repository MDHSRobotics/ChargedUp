
package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.consoles.Logger;
import frc.robot.BotSensors;

// This class contains methods for obtaining useful gyro readings.
public class Gyro {

    public static final double YAW_TOLERANCE = 5;

    // Checks connections
    public static void initializeGyro(AHRS gyro) {
        boolean gyroIsConnected = gyro.isConnected();
        if (!gyroIsConnected) {
            Logger.problem("Gyro not connected!");
        }
    }

    // Returns true if the gyro yaw matches the target angle within the YAW_TOLERANCE
    public static boolean isYawAligned(double targetAngle) {
        double angle = BotSensors.gyro.getYaw();
        double difference = Math.abs(targetAngle - angle);
        if (difference > 180) difference = 360 - difference;
        boolean aligned = (difference <= YAW_TOLERANCE);
        Logger.info("Gyro -> Target Angle: " + targetAngle + "; Gyro Yaw: " + angle + "; Difference: " + difference);
        return aligned;
    }

}
