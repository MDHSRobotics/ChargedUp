
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Joystick,
// their default values, and methods for retrieving their current values
public class JoystickBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double yDeadZoneDefault = .1;
    public static double xDeadZoneDefault = .1;
    public static double zDeadZoneDefault = .1;
    public static double ySensitivityDefault = .5;
    public static double xSensitivityDefault = .5;
    public static double zSensitivityDefault = .5;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry yDeadZoneEntry;
    public static GenericEntry xDeadZoneEntry;
    public static GenericEntry zDeadZoneEntry;
    public static GenericEntry ySensitivityEntry;
    public static GenericEntry xSensitivityEntry;
    public static GenericEntry zSensitivityEntry;

    //---------//
    // Getters //
    //---------//

    public static double getYdeadZone() {
        return yDeadZoneEntry.getDouble(yDeadZoneDefault);
    }

    public static double getXdeadZone() {
        return xDeadZoneEntry.getDouble(xDeadZoneDefault);
    }

    public static double getZdeadZone() {
        return zDeadZoneEntry.getDouble(zDeadZoneDefault);
    }

    public static double getYsensitivity() {
        return ySensitivityEntry.getDouble(ySensitivityDefault);
    }

    public static double getXsensitivity() {
        return xSensitivityEntry.getDouble(xSensitivityDefault);
    }

    public static double getZsensitivity() {
        return zSensitivityEntry.getDouble(zSensitivityDefault);
    }

}
