
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Forklift,
// their default values, and methods for retrieving their current values
public class ForkliftBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double defaultExtenderPower = 0;
    public static double defaultElevatorPower = 0;

    public static double defaultExtenderSpeed = 1;
    public static double defaultElevatorSpeed = 1;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry entryExtenderPower;
    public static GenericEntry entryElevatorPower;

    public static GenericEntry entryExtenderSpeed;
    public static GenericEntry entryElevatorSpeed;

    //---------//
    // Getters //
    //---------//

    public static double getExtenderPower() {
        return entryExtenderPower.getDouble(defaultExtenderPower);
    }

    public static double getElevatorPower() {
        return entryElevatorPower.getDouble(defaultElevatorPower);
    }

    public static double getExtenderSpeed(){
        return entryExtenderSpeed.getDouble(defaultExtenderSpeed);
    }

    public static double getElevatorSpeed(){
        return entryElevatorSpeed.getDouble(defaultElevatorSpeed);
    }

    //---------//
    // Setters //
    //---------//

}
