
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

    // Returns true if the gyro yaw matches the target angle within the YAW_TOLERANCE
    public static double yawDifference(double targetAngle) {
        double angle = BotSensors.gyro.getRoll();
        double difference = Math.abs(targetAngle - angle);
        /*
        if (difference > 180) difference = 360 - difference;
        boolean aligned = (difference <= YAW_TOLERANCE);
        Logger.info("Gyro -> Target Angle: " + targetAngle + "; Gyro Yaw: " + angle + "; Difference: " + difference);
        */
        return difference;
    }

    public static void adjustYaw(){
        double difference = yawDifference(0);
        if(difference > YAW_TOLERANCE){
            m_swerveDriver.setChassisSpeed(DRIVE_SPEED*YAW_DIRECTION, 0, 0);
        }else if(difference < -YAW_TOLERANCE){
            m_swerveDriver.setChassisSpeed(-DRIVE_SPEED*YAW_DIRECTION, 0, 0);
        }else {
            m_swerveDriver.stopModules();
        }
    }
}
