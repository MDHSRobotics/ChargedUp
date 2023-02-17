
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Forklift,
// their default values, and methods for retrieving their current values
public class ForkliftBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double clawPowerDefault = 0;
    public static double extenderPowerDefault = 0;
    public static double elevatorPowerDefault = 0;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry entryClawPower;
    public static GenericEntry entryExtenderPower;
    public static GenericEntry entryElevatorPower;

    //---------//
    // Getters //
    //---------//

    public static double getClawPower() {
        return entryClawPower.getDouble(clawPowerDefault);
    }

    public static double getExtenderPower() {
        return entryExtenderPower.getDouble(extenderPowerDefault);
    }

    public static double getElevatorPower() {
        return entryElevatorPower.getDouble(elevatorPowerDefault);
    }

    //---------//
    // Setters //
    //---------//

    public static void setClawPower(double value) {
        entryClawPower.setDouble(value);
    }

    public static void setExtenderPower(double value) {
       entryExtenderPower.setDouble(value);
    }
    
    public static void setElevatorPower(double value) {
        entryElevatorPower.setDouble(value);
    }

}
