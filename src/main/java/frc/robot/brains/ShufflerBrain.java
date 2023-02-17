
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

    public static GenericEntry entryMatchTime;
    public static GenericEntry entryDriveEncoderTicks;

    //---------//
    // Getters //
    //---------//

    public static double getMatchTime() {
        return entryMatchTime.getDouble(matchTimeDefault);
    }

    //---------//
    // Setters //
    //---------//

    public static void setMatchTime() {
       double matchTime = DriverStation.getMatchTime();
       entryMatchTime.setDouble(matchTime);
    }

}
