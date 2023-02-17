
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

    public static GenericEntry entryGyroRoll;
    public static GenericEntry entryGyroPitch;
    public static GenericEntry entryGyroYaw;

    //---------//
    // Getters //
    //---------//

    public static double getGyroRoll() {
        return entryGyroRoll.getDouble(gyroRollDefault);
    }

    public static double getGyroPitch() {
        return entryGyroRoll.getDouble(gyroPitchDefault);
    }

    public static double getGyroYaw() {
        return entryGyroRoll.getDouble(gyroYawDefault);
    }

    //---------//
    // Setters //
    //---------//

    public static void setGyroRoll(double value) {
        entryGyroRoll.setDouble(value);
    }

    public static void setGyroPitch(double value) {
        entryGyroPitch.setDouble(value);
    }

    public static void setGyroYaw(double value) {
       entryGyroYaw.setDouble(value);
    }

}
