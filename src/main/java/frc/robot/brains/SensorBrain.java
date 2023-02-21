
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the sensors,
// their default values, and methods for retrieving their current values.
public class SensorBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double defaultGyroRoll = 0.;
    public static double defaultGyroPitch = 0.;
    public static double defaultGyroYaw = 0.;

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
        return entryGyroRoll.getDouble(defaultGyroRoll);
    }

    public static double getGyroPitch() {
        return entryGyroRoll.getDouble(defaultGyroPitch);
    }

    public static double getGyroYaw() {
        return entryGyroRoll.getDouble(defaultGyroYaw);
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
