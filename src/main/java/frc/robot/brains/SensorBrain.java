
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the sensors,
// their default values, and methods for retrieving their current values.
public class SensorBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double gyroRollDefault = 0.;
    public static double gyroPitchDefault = 0.;
    public static double gyroYawDefault = 0.;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry gyroRollEntry;
    public static GenericEntry gyroPitchEntry;
    public static GenericEntry gyroYawEntry;

    //---------//
    // Getters //
    //---------//

    public static double getGyroRoll() {
        return gyroRollEntry.getDouble(gyroRollDefault);
    }

    public static double getGyroPitch() {
        return gyroRollEntry.getDouble(gyroPitchDefault);
    }

    public static double getGyroYaw() {
        return gyroRollEntry.getDouble(gyroYawDefault);
    }

    //---------//
    // Setters //
    //---------//

    public static void setGyroRoll(double value) {
        gyroRollEntry.setDouble(value);
     }

     public static void setGyroPitch(double value) {
        gyroPitchEntry.setDouble(value);
     }

     public static void setGyroYaw(double value) {
        gyroYawEntry.setDouble(value);
     }
}
