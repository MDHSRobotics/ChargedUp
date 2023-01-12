
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DriverStation;

// This class contains all the shared NetworkTableEntries for the Shuffler,
// their default values, and methods for retrieving their current values.
public class ShufflerBrain {

    //----------------//
    // Default Values //
    //----------------//

    public static double matchTimeDefault = 0;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry matchTimeEntry;
    public static GenericEntry entryDriveEncoderTicks;

    //---------//
    // Setters //
    //---------//

    public static void setMatchTime() {
       double matchTime = DriverStation.getMatchTime();
       matchTimeEntry.setDouble(matchTime);
    }

    //---------//
    // Getters //
    //---------//

    // Main Tab
    public static double getMatchTime() {
       return matchTimeEntry.getDouble(matchTimeDefault);
    }

}
