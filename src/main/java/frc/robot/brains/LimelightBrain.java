package frc.robot.brains;

import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.networktables.GenericEntry;

public class LimelightBrain {

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static double xOffsetEntryDefault = 0;
    public static double yOffsetEntryDefault = 0;
    public static double distanceEntryDefault = 0;
    public static boolean validTargetEntryDefault = false;
    public static VideoSource limelightCamera = new HttpCamera("limelight-mdrobot", "http://10.41.41.11:5801/", HttpCameraKind.kMJPGStreamer);

    public static GenericEntry xOffsetEntry;
    public static GenericEntry yOffsetEntry;
    public static GenericEntry distanceEntry;
    public static GenericEntry validTargetEntry;

}
