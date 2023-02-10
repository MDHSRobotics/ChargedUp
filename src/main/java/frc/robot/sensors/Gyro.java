
package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.consoles.Logger;

// This class contains methods for obtaining useful gyro readings.
public class Gyro {

    // Checks connections
    public static void initializeGyro(AHRS gyro) {
        boolean gyroIsConnected = gyro.isConnected();
        if (!gyroIsConnected) {
            Logger.problem("Gyro not connected!");
        }
    }

}
