
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

    public static double defaultExtenderSoftStop = 120;
    public static double defaultElevatorSoftStop = 66;

    public static boolean defaultSoftStopEnabled = true;

    public static double defaultElevatorPickupPosition = 0;
    public static double defaultElevatorMediumPosition = 68;
    public static double defaultElevatorHighPosition = 80;

    public static double defaultExtenderPickupPosition = 0;
    public static double defaultExtenderMediumPosition = 90;
    public static double defaultExtenderHighPosition = 100;


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

    public static GenericEntry elevatorPickupPosition;
    public static GenericEntry elevatorMediumPosition;
    public static GenericEntry elevatorHighPosition;

    public static GenericEntry extenderPickupPosition;
    public static GenericEntry extenderMediumPosition;
    public static GenericEntry extenderHighPosition;

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

    public static double getElevatorPickupPosition(){
        return elevatorPickupPosition.getDouble(defaultElevatorPickupPosition);
    }

    public static double getElevatorMediumPosition(){
        return elevatorMediumPosition.getDouble(defaultElevatorMediumPosition);
    }

    public static double getElevatorHighPosition(){
        return elevatorHighPosition.getDouble(defaultElevatorHighPosition);
    }

    public static double getExtenderPickupPosition(){
        return extenderPickupPosition.getDouble(defaultExtenderPickupPosition);
    }

    public static double getExtenderMediumPosition(){
        return extenderMediumPosition.getDouble(defaultExtenderMediumPosition);
    }

    public static double getExtenderHighPosition(){
        return extenderHighPosition.getDouble(defaultExtenderHighPosition);
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
