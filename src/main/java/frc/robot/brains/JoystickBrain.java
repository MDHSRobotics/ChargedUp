
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Joystick Controller,
// their default values, and methods for retrieving their current values
public class JoystickBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double defaultXDeadZone = .1;
    public static double defaulyYDeadZone = .1;
    public static double defaultZDeadZone = .1;

    public static double defaultXSensitivity = .5;
    public static double defaultYSensitivity = .5;
    public static double defaultZSensitivity = .5;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry entryXDeadZone;
    public static GenericEntry entryYDeadZone;
    public static GenericEntry entryZDeadZone;

    public static GenericEntry entryXSensitivity;
    public static GenericEntry entryYSensitivity;
    public static GenericEntry entryZSensitivity;

    //---------//
    // Getters //
    //---------//
    public static double getXdeadZone() {
        return entryXDeadZone.getDouble(defaultXDeadZone);
    }

    public static double getYdeadZone() {
        return entryYDeadZone.getDouble(defaulyYDeadZone);
    }

    public static double getZdeadZone() {
        return entryZDeadZone.getDouble(defaultZDeadZone);
    }

    public static double getXsensitivity() {
        return entryXSensitivity.getDouble(defaultXSensitivity);
    }

    public static double getYsensitivity() {
        return entryYSensitivity.getDouble(defaultYSensitivity);
    }

    public static double getZsensitivity() {
        return entryZSensitivity.getDouble(defaultZSensitivity);
    }

}
