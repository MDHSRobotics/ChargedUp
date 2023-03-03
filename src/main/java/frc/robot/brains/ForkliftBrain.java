
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Forklift,
// their default values, and methods for retrieving their current values
public class ForkliftBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double defaultExtenderSpeed = 1;
    public static double defaultElevatorSpeed = 1;

    public static double defaultExtenderEncoder = 0;
    public static double defaultElevatorEncoder = 0;

    public static double defaultExtenderSoftStop = 135;
    public static double defaultElevatorSoftStop = 23;

    public static boolean defaultSoftStopEnabled = true;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry entryExtenderSpeed;
    public static GenericEntry entryElevatorSpeed;

    public static GenericEntry entryExtenderEncoder;
    public static GenericEntry entryElevatorEncoder;

    public static GenericEntry entryExtenderSoftStop;
    public static GenericEntry entryElevatorSoftStop;

    public static GenericEntry entrySoftStopEnabled;

    //---------//
    // Getters //
    //---------//

    public static double getExtenderSpeed(){
        return entryExtenderSpeed.getDouble(defaultExtenderSpeed);
    }

    public static double getElevatorSpeed(){
        return entryElevatorSpeed.getDouble(defaultElevatorSpeed);
    }

    public static double getExtenderEncoder(){
        return entryExtenderEncoder.getDouble(defaultExtenderEncoder);
    }

    public static double getElevatorEncoder(){
        return entryElevatorEncoder.getDouble(defaultElevatorEncoder);
    }

    public static boolean getSoftStopEnabled(){
        return entrySoftStopEnabled.getBoolean(defaultSoftStopEnabled);
    }

    public static double getExtenderSoftStop(){
        return entryExtenderSoftStop.getDouble(defaultExtenderSoftStop);
    }

    public static double getElevatorSoftStop(){
        return entryElevatorSoftStop.getDouble(defaultElevatorSoftStop);
    }

    //---------//
    // Setters //
    //---------//

    public static void setExtenderEncoder(double value) {
        entryExtenderEncoder.setDouble(value);
    }

    public static void setElevatorEncoder(double value) {
        entryElevatorEncoder.setDouble(value);
    }

    public static void setSoftStopEnabled(boolean value) {
        entrySoftStopEnabled.setBoolean(value);
    }

}
