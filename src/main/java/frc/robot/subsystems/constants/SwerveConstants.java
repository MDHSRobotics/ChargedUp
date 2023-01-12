package frc.robot.subsystems.constants;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

public final class SwerveConstants {
    public static final double kGearRatioTurning = 12.8;
    public static final double kGearRatioDriving = 8.16;
    public static final double kPTurning = 0.5;
    public static final double kWheelDiameterMeters = Units.inchesToMeters(4.0);
    public static final double kPhysicalMaxSpeedMetersPerSecond = 8.0;
    public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2.0 * 2.0 * Math.PI;

    public static final double kTrackWidth = Units.inchesToMeters(25.0);
    // Distance between right and left wheels
    public static final double kWheelBase = Units.inchesToMeters(25.0);
    // Distance between front and back wheels
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
            new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, kTrackWidth / 2));

    // The turning motors should probably all have the same "reversed" state
    // since they are all mounted in the same way
    public static final boolean kFrontLeftTurningMotorReversed = true;
    public static final boolean kRearLeftTurningMotorReversed = true;
    public static final boolean kFrontRightTurningMotorReversed = true;
    public static final boolean kRearRightTurningMotorReversed = true;

    /* The drive motor's "reversed" state needs to take into account how the module
       is expected to be oriented when driving forward. The driving wheel on each
       module can be aligned with the robot in two different ways: with the gears
       pointing to the inside or outside. By convention, we assume that the gears
       are pointing to the inside, which means that the motors on the left and
       right sides will have opposite "reversed" states.
    */ 
    public static final boolean kFrontLeftDriveMotorReversed = true;
    public static final boolean kRearLeftDriveMotorReversed = true;
    public static final boolean kFrontRightDriveMotorReversed = false;
    public static final boolean kRearRightDriveMotorReversed = false;

    // TODO Double check these values and document how to interpret them
    public static final boolean kFrontLeftDriveAbsoluteEncoderReversed = true;
    public static final boolean kRearLeftDriveAbsoluteEncoderReversed = true;
    public static final boolean kFrontRightDriveAbsoluteEncoderReversed = true;
    public static final boolean kRearRightDriveAbsoluteEncoderReversed = true;

    /* The absolute encoder retains its value even after the robot has been
       powered off. Note that this behavior must be configured using the Phoenix Tuner
       by setting the "Sensor Boot-Initialization Strategy" so that it does not
       reset to 0 on re-boot. See the CTRE documentation for more information
       about how to bring up the CANcoder absolute encoder.
       https://docs.ctre-phoenix.com/en/stable/ch12a_BringUpCANCoder.html 

       The absolute encoder will normally report a non-zero value for its
       position when the module's wheel is aligned with the robot.  This value
       is referred to as the "absolute encoder offset", which is used by the
       swerve module to detect the true position of the module's wheel. The 
       procedure for determining the offset is as follows:
       1) Align the module's wheel with the robot and orient it as described
          above for the drive motor's reversed state - that is, ensure that 
          the gears are facing the inside of the robot.
       2) Using the Phoenix Tuner, run the Self Test Snapshot to find the
          current absolute position in degrees. That number is the offset
          for this module.  Convert that value from degrees to radians below.
    */
    public static final double kFrontLeftDriveAbsoluteEncoderOffsetRad = Units.degreesToRadians(140.713);
    public static final double kRearLeftDriveAbsoluteEncoderOffsetRad = Units.degreesToRadians(-140.713);
    public static final double kFrontRightDriveAbsoluteEncoderOffsetRad = Units.degreesToRadians(-166.377);
    public static final double kRearRightDriveAbsoluteEncoderOffsetRad = Units.degreesToRadians(-67.412);

    public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 4;
    public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = 3;//kPhysicalMaxAngularSpeedRadiansPerSecond / 9;
    public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 1;
    public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 1.5;

    public static final double kDriveRampTime = 1.0; // units are seconds

    public static final class OIConstants {
        public static final double kDeadband = 0.05;
    }
}
