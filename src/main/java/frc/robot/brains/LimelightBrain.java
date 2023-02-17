package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

public class LimelightBrain {

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static double xOffsetEntryDefault = 0;
    public static double yOffsetEntryDefault = 0;
    public static double distanceEntryDefault = 0;
    public static boolean validTargetEntryDefault = false;

    public static GenericEntry xOffsetEntry;
    public static GenericEntry yOffsetEntry;
    public static GenericEntry distanceEntry;
    public static GenericEntry validTargetEntry;

}
