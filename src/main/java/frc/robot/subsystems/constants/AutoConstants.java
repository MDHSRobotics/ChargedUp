package frc.robot.subsystems.constants;

import edu.wpi.first.math.trajectory.TrapezoidProfile;

public final class AutoConstants {

    //------------------//
    // Nonpath-Specific //
    //------------------//

    /*
    Since the robot starts in the same place right next to the goal every time 
    regardless of which path is chosen, the initial shootng sequence will 
    always be the same. The robot will also need to back up every time to create
    space to move towards the target ball. 
    */
    
    // General pathing constants
    public static final double kDefaultDriveTimeSeconds = 2;
    public static final double kDrivePower = 0.5;
    public static final double kChargeStationRampAngle = 11;

    //------------//
    // Pathweaver //
    //------------//

    // PID constants for trajectory following
    public static final double kPXController = 1.5;
    public static final double kPYController = 1.5;
    public static final double kPThetaController = 0;

    // Profile for trajectory following
    public static final double kMaxAngularSpeedRadiansPerSecond = 0.25;
    public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;

    public static final TrapezoidProfile.Constraints kThetaControllerConstraints = 
                new TrapezoidProfile.Constraints(
                        kMaxAngularSpeedRadiansPerSecond,
                        kMaxAngularAccelerationRadiansPerSecondSquared);

}
