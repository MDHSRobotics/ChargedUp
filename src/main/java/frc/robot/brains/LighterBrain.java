
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the sensors,
// their default values, and methods for retrieving their current values.
public class LighterBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static int numLightsDefault = 60;
    public static int redValueDefault = 0xFF;
    public static int greenValueDefault = 0xFF;
    public static int blueValueDefault = 0xFF;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry numLightsEntry;
    public static GenericEntry redValueEntry;
    public static GenericEntry greenValueEntry;
    public static GenericEntry blueValueEntry;

    //---------//
    // Getters //
    //---------//

    public static double getNumLights() {
        return numLightsEntry.getDouble(numLightsDefault);
    }

    public static double getRedValue() {
        return redValueEntry.getDouble(redValueDefault);
    }

    public static double getGreenValue() {
        return greenValueEntry.getDouble(greenValueDefault);
    }

    public static double getBlueValue() {
        return blueValueEntry.getDouble(blueValueDefault);
    }

}
