
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Joystick Controller,
// their default values, and methods for retrieving their current values
public class JoystickBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double xDeadZoneDefault = .1;
    public static double yDeadZoneDefault = .1;
    public static double zDeadZoneDefault = .1;

    public static double xSensitivityDefault = .5;
    public static double ySensitivityDefault = .5;
    public static double zSensitivityDefault = .5;

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
        return entryXDeadZone.getDouble(xDeadZoneDefault);
    }

    public static double getYdeadZone() {
        return entryYDeadZone.getDouble(yDeadZoneDefault);
    }

    public static double getZdeadZone() {
        return entryZDeadZone.getDouble(zDeadZoneDefault);
    }

    public static double getXsensitivity() {
        return entryXSensitivity.getDouble(xSensitivityDefault);
    }

    public static double getYsensitivity() {
        return entryYSensitivity.getDouble(ySensitivityDefault);
    }

    public static double getZsensitivity() {
        return entryZSensitivity.getDouble(zSensitivityDefault);
    }

}
