    package frc.robot.sensors; 
 
import edu.wpi.first.networktables.NetworkTable; 
import edu.wpi.first.networktables.NetworkTableEntry; 
import edu.wpi.first.networktables.NetworkTableInstance;
 
public class Limelight { 
 
    private static final double CAMERA_HEIGHT = 25.0; // height of lens (in) h1
    private static final double TARGET_HEIGHT = 18.0; //height to the center of target(in) h2
    private static final double CAMERA_ANGLE = 0.0;// angle of the camera(deg) a1 

    private static NetworkTable m_limelightNetworkTable = NetworkTableInstance.getDefault().getTable("limelight-mdrobot"); 
    
    private static NetworkTableEntry m_horizontalOffset = m_limelightNetworkTable.getEntry("tx"); // Checks horizontal offset from crosshair to target
    private static NetworkTableEntry m_verticalOffset = m_limelightNetworkTable.getEntry("ty"); // Checks vertical offset from crosshair to target
    private static NetworkTableEntry m_targetDetected = m_limelightNetworkTable.getEntry("tv"); // Checks whether limelight has valid targets
    private static NetworkTableEntry m_targetVisionCoverage = m_limelightNetworkTable.getEntry("ta"); // How many pixels of screen is target (target area)
    private static NetworkTableEntry m_ledMode = m_limelightNetworkTable.getEntry("ledMode"); // Set led state
    private static NetworkTableEntry m_pipeline = m_limelightNetworkTable.getEntry("pipeline"); // Set pipeline
    private static NetworkTableEntry m_camMode = m_limelightNetworkTable.getEntry("camMode");

    private static boolean m_isAligning = false;    

    public static double getXOffset() { 
        return m_horizontalOffset.getDouble(-1); 
    } 
    
    public static double getYOffset() { 
        return m_verticalOffset.getDouble(0.0); 
    }
    public static void setLedMode(int mode) { 
        m_ledMode.setNumber(mode); 
    } 
 
    public void toggleAlignmentState() {
        m_isAligning = !m_isAligning;
    }

    public static boolean getAlignmentState() {
        return m_isAligning;
    }
    
    public static boolean getDetectionState() {
        boolean targetDetected = false;
        if (m_targetDetected.getInteger(0) == 1) {
            //Turn on light
            targetDetected = true;
        } else {
            //Turn off light
            targetDetected = false;
        }
        return targetDetected;
    }

    public static void setPipeline(int pipeline) {
        m_pipeline.setNumber(pipeline);
    }

    public static void setCamMode(int cammode) {
        m_camMode.setNumber(cammode);
    }

    // Uses the limelight to find the distance in feet 
    public static double calculateDistanceToTarget() { 
        double yOffset = getYOffset(); 
        double angleInRadians = ((yOffset + CAMERA_ANGLE) / 180) * Math.PI; 
 
        double distance = (TARGET_HEIGHT - CAMERA_HEIGHT) / Math.tan(angleInRadians); 
        distance /= 12.0; // converts inches to feet 

        return distance;
    } 

}