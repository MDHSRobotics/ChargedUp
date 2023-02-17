
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Xbox Controller,
// their default values, and methods for retrieving their current values.
public class XboxBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double yLeftDeadZoneDefault = .1;
    public static double xLeftDeadZoneDefault = .1;
    public static double yRightDeadZoneDefault = .1;
    public static double xRightDeadZoneDefault = .1;

    public static double yLeftSensitivityDefault = .5;
    public static double xLeftSensitivityDefault = .5;
    public static double yRightSensitivityDefault = .5;
    public static double xRightSensitivityDefault = .5;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry entryYLeftDeadZone;
    public static GenericEntry entryXLeftDeadZone;
    public static GenericEntry entryYRightDeadZone;
    public static GenericEntry entryXRightDeadZone;

    public static GenericEntry entryYLeftSensitivity;
    public static GenericEntry entryXLeftSensitivity;
    public static GenericEntry entryYRightSensitivity;
    public static GenericEntry entryXRightSensitivity;

    public static GenericEntry entryIsControllerOneConnected;
    public static GenericEntry entryIsControllerTwoConnected;

    //---------//
    // Getters //
    //---------//

    public static double getYleftDeadZone() {
        return entryYLeftDeadZone.getDouble(yLeftDeadZoneDefault);
    }

    public static double getXleftDeadZone() {
        return entryXLeftDeadZone.getDouble(xLeftDeadZoneDefault);
    }

    public static double getYrightDeadZone() {
        return entryYRightDeadZone.getDouble(yRightDeadZoneDefault);
    }

    public static double getXrightDeadZone() {
        return entryXRightDeadZone.getDouble(xRightDeadZoneDefault);
    }

    public static double getYleftSensitivity() {
        return entryYLeftSensitivity.getDouble(yLeftSensitivityDefault);
    }

    public static double getXleftSensitivity() {
        return entryXLeftSensitivity.getDouble(xLeftSensitivityDefault);
    }

    public static double getYrightSensitivity() {
        return entryYRightSensitivity.getDouble(yRightSensitivityDefault);
    }

    public static double getXrightSensitivity() {
        return entryYRightSensitivity.getDouble(yRightSensitivityDefault);
    }

}
