package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.BotSensors;
import frc.robot.consoles.Logger;
import frc.robot.devices.DevSwerveModule;
import static frc.robot.subsystems.Devices.*;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveDriver extends SubsystemBase {

    // Constants
    private final double PHYSICAL_MAX_SPEED_MPS = 10;

    // Distance between right and left wheels
    public final double TRACK_WIDTH = Units.inchesToMeters(18.0);
    // Distance between front and back wheels
    public final double WHEEL_BASE = Units.inchesToMeters(23.0);
    // Drive Kinematics
    public final SwerveDriveKinematics DRIVE_KINEMATICS = new SwerveDriveKinematics(
            new Translation2d(WHEEL_BASE / 2, -TRACK_WIDTH / 2),
            new Translation2d(WHEEL_BASE / 2, TRACK_WIDTH / 2),
            new Translation2d(-WHEEL_BASE / 2, -TRACK_WIDTH / 2),
            new Translation2d(-WHEEL_BASE / 2, TRACK_WIDTH / 2));

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
    public final double FL_ABSOLUTE_ENCODER_OFFSET = Units.degreesToRadians(-81.562500); 
    public final double FR_ABSOLUTE_ENCODER_OFFSET = Units.degreesToRadians(-41.748047);
    public final double RL_ABSOLUTE_ENCODER_OFFSET = Units.degreesToRadians(-150.732422);
    public final double RR_ABSOLUTE_ENCODER_OFFSET = Units.degreesToRadians(-52.646484);

    //Drive Ramp Time
    private final double RAMP_TIME = 0.4;

    private final DevSwerveModule frontLeft = new DevSwerveModule(
        "Front Left",
        Devices.talonFxSwerveDriveFL,
        Devices.talonFxSwerveTurnFL,
        Devices.canCoderFL,
        true,
        true,
        true,
        FL_ABSOLUTE_ENCODER_OFFSET);

    private final DevSwerveModule frontRight = new DevSwerveModule(
        "Front Right",
        Devices.talonFxSwerveDriveFR,
        Devices.talonFxSwerveTurnFR,
        Devices.canCoderFR,
        false,
        true,
        true,
        FR_ABSOLUTE_ENCODER_OFFSET);

    private final DevSwerveModule rearLeft = new DevSwerveModule(
        "Rear Left",
        Devices.talonFxSwerveDriveRL,
        Devices.talonFxSwerveTurnRL,
        Devices.canCoderRL,
        true,
        true,
        true,
        RL_ABSOLUTE_ENCODER_OFFSET);

    private final DevSwerveModule rearRight = new DevSwerveModule(
        "Rear Right",
        Devices.talonFxSwerveDriveRR,
        Devices.talonFxSwerveTurnRR,
        Devices.canCoderRR,
        false,
        true,
        true,
        RR_ABSOLUTE_ENCODER_OFFSET);

    // Switch between robot and field relative control
    public boolean fieldRelative = false;

        SwerveModulePosition modulePositionFL;
        SwerveModulePosition modulePositionFR;
        SwerveModulePosition modulePositionRL;
        SwerveModulePosition modulePositionRR;

    private final SwerveDriveOdometry odometer;

    SwerveModulePosition[] modulePositions = new SwerveModulePosition[4];

    

    // Constructs new SwerveDriver
    public SwerveDriver() {
        talonFxSwerveDriveFL.configOpenloopRamp(RAMP_TIME);
        talonFxSwerveDriveFR.configOpenloopRamp(RAMP_TIME);
        talonFxSwerveDriveRL.configOpenloopRamp(RAMP_TIME);
        talonFxSwerveDriveRR.configOpenloopRamp(RAMP_TIME);

        talonFxSwerveDriveFL.setNeutralMode(NeutralMode.Brake);
        talonFxSwerveDriveFR.setNeutralMode(NeutralMode.Brake);
        talonFxSwerveDriveRL.setNeutralMode(NeutralMode.Brake);
        talonFxSwerveDriveRR.setNeutralMode(NeutralMode.Brake);

        frontLeft.resetEncoders();
        frontRight.resetEncoders();
        rearLeft.resetEncoders();
        rearRight.resetEncoders();

        // Initialize swerve module positions with initial values
        modulePositionFL =  new SwerveModulePosition(frontLeft.getDrivePositionMeters(), getWheelPosition(frontLeft));
        modulePositionFR =  new SwerveModulePosition(frontRight.getDrivePositionMeters(), getWheelPosition(frontRight));
        modulePositionRL =  new SwerveModulePosition(rearLeft.getDrivePositionMeters(), getWheelPosition(rearLeft));
        modulePositionRR =  new SwerveModulePosition(rearRight.getDrivePositionMeters(), getWheelPosition(rearRight));

        // Initialize swerve odometer with initial values
        odometer = new SwerveDriveOdometry(DRIVE_KINEMATICS, BotSensors.gyro.getRotation2d(), new SwerveModulePosition[] {
            modulePositionFL,
            modulePositionFR,
            modulePositionRL,
            modulePositionRR
          });

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                zeroHeading();
            } catch (Exception e) {
            }
        }).start();
    }

    public void ToggleOrientation() {
        if (fieldRelative) {
            fieldRelative = false;
        } else {
            fieldRelative = true;
        }
    }

    public void zeroHeading() {
        BotSensors.gyro.reset();
    }

    public double getHeading() {
        return Math.IEEEremainder(BotSensors.gyro.getAngle(), 360);
    }

    // Returns the current rotation2D
    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getHeading());
    }

    public Rotation2d getWheelPosition(DevSwerveModule wheelSwerveModule) {
        double angleInDegrees = wheelSwerveModule.getAbsoluteEncoderRadians() * (180 / Math.PI);
        angleInDegrees = Math.IEEEremainder(wheelSwerveModule.getAbsoluteEncoderRadians(), 360);
        return Rotation2d.fromDegrees(angleInDegrees);
    }

    public Pose2d getPose() {
        return odometer.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose) {
        odometer.resetPosition(getRotation2d(), new SwerveModulePosition[0], pose); 
    }

    @Override
    public void periodic() {

        // Updates module positions
        modulePositionFL = new SwerveModulePosition(frontLeft.getDrivePositionMeters(), getWheelPosition(frontLeft));
        modulePositionFR = new SwerveModulePosition(frontRight.getDrivePositionMeters(), getWheelPosition(frontRight));
        modulePositionRL = new SwerveModulePosition(rearLeft.getDrivePositionMeters(), getWheelPosition(rearLeft));
        modulePositionRR = new SwerveModulePosition(rearRight.getDrivePositionMeters(), getWheelPosition(rearRight));

        modulePositions[0] = modulePositionFL;
        modulePositions[1] = modulePositionFR;
        modulePositions[2] = modulePositionRL;
        modulePositions[3] = modulePositionRR;

        odometer.update(getRotation2d(), modulePositions);

        // Update SmartDashboard
        //SmartDashboard.putNumber("Robot Heading", getHeading());
        //SmartDashboard.putString("Robot Location", getPose().getTranslation().toString());


        // Update Shuffleboard
        /*frontLeft.setShuffleboardBrain();
        frontRight.setShuffleboardBrain();
        rearLeft.setShuffleboardBrain();
        rearRight.setShuffleboardBrain();*/
    }

    public void stopModules() {
        frontLeft.stop();
        frontRight.stop();
        rearLeft.stop();
        rearRight.stop();
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        //SmartDashboard.putString("05: Front Left Desired State", desiredStates[0].toString());
        //SmartDashboard.putString("05: Front Right Desired State", desiredStates[1].toString());
        //SmartDashboard.putString("05: Rear Left Desired State", desiredStates[2].toString());
        //SmartDashboard.putString("05: Rear Right Desired State", desiredStates[3].toString());

        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, PHYSICAL_MAX_SPEED_MPS);

        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        rearLeft.setDesiredState(desiredStates[2]);
        rearRight.setDesiredState(desiredStates[3]);
    }

    //Set chassis speed based on current toggle of field orientation
    public void setChassisSpeed(double xSpeed, double ySpeed, double turningSpeed) {
        setChassisSpeed(xSpeed, ySpeed, turningSpeed, fieldRelative);
    }

    //Set chassis speeds
    public void setChassisSpeed(double xSpeed, double ySpeed, double turningSpeed, boolean fieldOriented) {
        //SmartDashboard.putString("07: xSpeed", "" + xSpeed);
        //SmartDashboard.putString("07: ySpeed", "" + ySpeed);
        //SmartDashboard.putString("07: turningSpeed", "" + turningSpeed);
        //SmartDashboard.putString("07: fieldOriented", "" + fieldOriented);
        // Construct desired chassis speeds
        ChassisSpeeds chassisSpeeds;

        if (fieldOriented) {
            // Relative to field
            chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                    -xSpeed, ySpeed, -turningSpeed, getRotation2d());
        } else {
            // Relative to robot
            chassisSpeeds = new ChassisSpeeds(-xSpeed, ySpeed, -turningSpeed);
        }

        //SmartDashboard.putString("06: Chassis Speeeds", chassisSpeeds.toString());
        // Convert chassis speeds to individual module states
        SwerveModuleState[] moduleStates = DRIVE_KINEMATICS.toSwerveModuleStates(chassisSpeeds);

        // Output each module states to wheels
        setModuleStates(moduleStates);
    }

    private void lockWheels(){
        frontLeft.setTurningWheelPosition(-45);
        frontRight.setTurningWheelPosition(45);
        rearLeft.setTurningWheelPosition(45);
        rearRight.setTurningWheelPosition(-45);
    }

    private void updateAbsoluteEncoderOffsets(){
        /* 
        SwerveDriverBrain.setAbsoulteEncoderOffsets(
            frontLeft.getCanCoderAbsolutePosition(),
            frontRight.getCanCoderAbsolutePosition(),
            rearLeft.getCanCoderAbsolutePosition(),
            rearRight.getCanCoderAbsolutePosition());
        frontLeft.setAbsoluteEncoderOffset();
        frontRight.setAbsoluteEncoderOffset();
        rearLeft.setAbsoluteEncoderOffset();
        rearRight.setAbsoluteEncoderOffset();
        
        frontLeft.resetCanCoder();
        frontRight.resetCanCoder();
        rearLeft.resetCanCoder();
        rearRight.resetCanCoder();*/
    }
    
    public String getPosition(){
        return getPose().getTranslation().toString();
    }

    public double getRotation(){
        return getHeading();
    }

    public CommandBase toggleOrientationCommand() {
        Logger.info("Toggling Orientation");
        return this.runOnce(() -> ToggleOrientation());
    }

    public CommandBase lockWheelsCommand(){
        Logger.info("Locking Wheels");
        return this.runOnce(() -> lockWheels());
    }

    public CommandBase resetAbsoluteEncoderOffsets(){
        Logger.info("Reseting Encoder Offsets");
        return this.runOnce(() -> updateAbsoluteEncoderOffsets());
    }

}   
