
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
    
    public static double defaultTargetDistance = 0.;
    public static int defaultRedValue = 0;
    public static int defaultGreenValue = 0;
    public static int defaultBlueValue = 0;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry entryGyroRoll;
    public static GenericEntry entryGyroPitch;
    public static GenericEntry entryGyroYaw;

    public static GenericEntry entryTargetDistance;
    public static GenericEntry entryRedValue;
    public static GenericEntry entryGreenValue;
    public static GenericEntry entryBlueValue;

    public static GenericEntry entryObjectInRange;
    public static GenericEntry entryColor;

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

    public static double getTargetDistance() {
        return entryTargetDistance.getDouble(defaultTargetDistance);
    }

    public static double getRedValue() {
        return entryRedValue.getInteger(defaultRedValue);
    }

    public static double getGreenValue() {
        return entryRedValue.getInteger(defaultRedValue);
    }

    public static double getBlueValue() {
        return entryRedValue.getInteger(defaultRedValue);
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

    public static void setTargetDistance(double value) {
        entryTargetDistance.setDouble(value);
    }

    public static void setRedValue(int value) {
        entryRedValue.setInteger(value);
    }

    public static void setGreenValue(int value) {
        entryGreenValue.setInteger(value);    
    }

    public static void setBlueValue(int value) {
        entryBlueValue.setInteger(value);   
    }

    public static void setObjectInRange(boolean value){
        entryObjectInRange.setBoolean(value);
    }

    public static void setColor(boolean value){
        entryColor.setBoolean(value);
    }

}