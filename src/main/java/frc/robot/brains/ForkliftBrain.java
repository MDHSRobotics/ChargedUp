
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Joystick,
// their default values, and methods for retrieving their current values
public class ForkliftBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double extenderPowerDefault = 1;
    public static double verticalPowerDefault = 1;
    public static double clawPowerDefault = 1;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry extenderPowerEntry;
    public static GenericEntry verticalPowerEntry;
    public static GenericEntry clawPowerEntry;

    //---------//
    // Setters //
    //---------//

    public static void setExtenderPower(double value) {
       extenderPowerEntry.setDouble(value);
    }
    
    public static void setVerticalPower(double value) {
        verticalPowerEntry.setDouble(value);
    }

    public static void setClawPower(double value) {
        clawPowerEntry.setDouble(value);
    }

    //---------//
    // Getters //
    //---------//

    // Main Tab
    public static double getExtenderPower() {
       return extenderPowerEntry.getDouble(extenderPowerDefault);
    }
    public static double getVerticalPower() {
        return verticalPowerEntry.getDouble(verticalPowerDefault);
     }
    public static double getClawPower() {
        return clawPowerEntry.getDouble(clawPowerDefault);
     }

}
