
package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.consoles.Logger;
import frc.robot.BotSensors;

import frc.robot.subsystems.SwerveDriver;

// This class contains methods for obtaining useful gyro readings.
public class Gyro {

    public static final double YAW_TOLERANCE = 5;
    public static final int YAW_DIRECTION = 1;
    public static final double DRIVE_SPEED = 0.5;

    private static SwerveDriver m_swerveDriver;

    // Checks connections
    public static void initializeGyro(AHRS gyro, SwerveDriver swerveDriver) {
        boolean gyroIsConnected = gyro.isConnected();
        if (!gyroIsConnected) {
            Logger.problem("Gyro not connected!");
        }
        m_swerveDriver = swerveDriver;
    }

}
