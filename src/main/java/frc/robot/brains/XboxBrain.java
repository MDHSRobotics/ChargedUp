
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;
import frc.robot.BotControllers;
import frc.robot.consoles.tabs.InputsTab;

// This class contains all the shared NetworkTableEntries for the Xbox Controller,
// their default values, and methods for retrieving their current values.
public class XboxBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double yLeftDeadZoneDefault = .02;
    public static double xLeftDeadZoneDefault = .02;
    public static double yRightDeadZoneDefault = .02;
    public static double xRightDeadZoneDefault = .02;
    public static double yLeftSensitivityDefault = .3;
    public static double xLeftSensitivityDefault = .3;
    public static double yRightSensitivityDefault = .3;
    public static double xRightSensitivityDefault = .3;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry yLeftDeadZoneEntry;
    public static GenericEntry xLeftDeadZoneEntry;
    public static GenericEntry yRightDeadZoneEntry;
    public static GenericEntry xRightDeadZoneEntry;
    public static GenericEntry yLeftSensitivityEntry;
    public static GenericEntry xLeftSensitivityEntry;
    public static GenericEntry yRightSensitivityEntry;
    public static GenericEntry xRightSensitivityEntry;

    public static GenericEntry isControllerOneConnectedEntry;
    public static GenericEntry isControllerTwoConnectedEntry;

    //---------//
    // Getters //
    //---------//

    public static double getYleftDeadZone() {
        return yLeftDeadZoneEntry.getDouble(yLeftDeadZoneDefault);
    }

    public static double getXleftDeadZone() {
        return xLeftDeadZoneEntry.getDouble(xLeftDeadZoneDefault);
    }

    public static double getYrightDeadZone() {
        return yRightDeadZoneEntry.getDouble(yRightDeadZoneDefault);
    }

    public static double getXrightDeadZone() {
        return xRightDeadZoneEntry.getDouble(xRightDeadZoneDefault);
    }

    public static double getYleftSensitivity() {
        return yLeftSensitivityEntry.getDouble(yLeftSensitivityDefault);
    }

    public static double getXleftSensitivity() {
        return xLeftSensitivityEntry.getDouble(xLeftSensitivityDefault);
    }

    public static double getYrightSensitivity() {
        return yRightSensitivityEntry.getDouble(yRightSensitivityDefault);
    }

    public static double getXrightSensitivity() {
        return yRightSensitivityEntry.getDouble(yRightSensitivityDefault);
    }

    // public static boolean getControllerOneConectedEntry() {
    //     return isControllerOneConnectedEntry.getBoolean(BotControllers.xbox1.isConnected());
    // }

    // public static boolean getControllerTwoConectedEntry() {
    //     return isControllerTwoConnectedEntry.getBoolean(BotControllers.xbox2.isConnected());
    // }

}
