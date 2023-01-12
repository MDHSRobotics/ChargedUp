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
    public static final double kTurnTimeSeconds = 1;
    public static final double kShootTimeSeconds = 5;
    public static final double kConveyorDelayTimeSeconds = 3;
    public static final double kMoveBackwardTimeSeconds = 2;
    public static final double kDefaultDriveTimeSeconds = 2;

    public static final double kInitialTopShooterWheelPower = 0.515;
    public static final double kInitialBottomShooterWheelPower = 0.14;
    public static final double kDrivePower = 0.5;

    //---------------//
    // Path-Specific //
    //---------------//

    /* 
    Different constants result from the ball being positioned at different
    distances from the robot's initial starting position, so turning to get the 
    ball and turning back towards the goal would also require different angles.
    */

    // Path 1 (left)
    public static final double kAngleToBallRadiansPathOne = 2.71;
    public static final double kMoveToBallTimeSecondsPathOne = 3;
    public static final double kAngleToPortRadiansPathOne = -2.94;
    public static final double kSecondBallTopShooterWheelPowerPathOne = 0.1;
    public static final double kSecondBallBottomShooterWheelPowerPathOne = 0.5;

    // Path 2 (center)
    public static final double kAngleToBallRadiansPathTwo = 2.20;
    public static final double kMoveToBallTimeSecondsPathTwo = 3;
    public static final double kAngleToPortRadiansPathTwo = -2.82;
    public static final double kSecondBallTopShooterWheelPowerPathTwo = 0.1;
    public static final double kSecondBallBottomShooterWheelPowerPathTwo = 0.5;

    // Path 3 (right)
    public static final double kAngleToBallRadiansPathThree = -2.84;
    public static final double kMoveToBallTimeSecondsPathThree = 3;
    public static final double kAngleToPortRadiansPathThree = 3.02;
    public static final double kSecondBallTopShooterWheelPowerPathThree = 0.1;
    public static final double kSecondBallBottomShooterWheelPowerPathThree = 0.5;

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
