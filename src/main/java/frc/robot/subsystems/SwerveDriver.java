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
import frc.robot.subsystems.constants.SwerveConstants;

public class SwerveDriver extends SubsystemBase {
    private final DevSwerveModule frontLeft = new DevSwerveModule(
        "Front Left",
        Devices.talonFxSwerveDriveFL,
        Devices.talonFxSwerveTurnFL,
        Devices.canCoderFL,
        SwerveConstants.kFrontLeftDriveMotorReversed,
        SwerveConstants.kFrontLeftTurningMotorReversed,
        SwerveConstants.kFrontLeftDriveAbsoluteEncoderOffsetRad,
        SwerveConstants.kFrontLeftDriveAbsoluteEncoderReversed);

    private final DevSwerveModule frontRight = new DevSwerveModule(
        "Front Right",
        Devices.talonFxSwerveDriveFR,
        Devices.talonFxSwerveTurnFR,
        Devices.canCoderFR,
        SwerveConstants.kFrontRightDriveMotorReversed,
        SwerveConstants.kFrontRightTurningMotorReversed,
        SwerveConstants.kFrontRightDriveAbsoluteEncoderOffsetRad,
        SwerveConstants.kFrontRightDriveAbsoluteEncoderReversed);

    private final DevSwerveModule rearLeft = new DevSwerveModule(
        "Rear Left",
        Devices.talonFxSwerveDriveRL,
        Devices.talonFxSwerveTurnRL,
        Devices.canCoderRL,
        SwerveConstants.kRearLeftDriveMotorReversed,
        SwerveConstants.kRearLeftTurningMotorReversed,
        SwerveConstants.kRearLeftDriveAbsoluteEncoderOffsetRad,
        SwerveConstants.kRearLeftDriveAbsoluteEncoderReversed);

    private final DevSwerveModule rearRight = new DevSwerveModule(
        "Rear Right",
        Devices.talonFxSwerveDriveRR,
        Devices.talonFxSwerveTurnRR,
        Devices.canCoderRR,
        SwerveConstants.kRearRightDriveMotorReversed,
        SwerveConstants.kRearRightTurningMotorReversed,
        SwerveConstants.kRearRightDriveAbsoluteEncoderOffsetRad,
        SwerveConstants.kRearRightDriveAbsoluteEncoderReversed);

    // Switch between robot and field relative control
    public boolean fieldRelative = false;

    private final SwerveDriveOdometry odometer = new SwerveDriveOdometry(SwerveConstants.kDriveKinematics, new Rotation2d(0), new SwerveModulePosition[0]);

    public SwerveDriver() {
        frontLeft.resetEncoders();
        frontRight.resetEncoders();
        rearLeft.resetEncoders();
        rearRight.resetEncoders();

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
        return Math.IEEEremainder(-BotSensors.gyro.getAngle(), 360);
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

        SwerveModulePosition modulePositionFL =  new SwerveModulePosition(frontLeft.getDrivePositionMeters(), getWheelPosition(frontLeft));
        SwerveModulePosition modulePositionFR =  new SwerveModulePosition(frontRight.getDrivePositionMeters(), getWheelPosition(frontRight));
        SwerveModulePosition modulePositionRL =  new SwerveModulePosition(rearLeft.getDrivePositionMeters(), getWheelPosition(rearLeft));
        SwerveModulePosition modulePositionRR =  new SwerveModulePosition(rearRight.getDrivePositionMeters(), getWheelPosition(rearRight));

        SwerveModulePosition[] modulePositions = new SwerveModulePosition[4];

        modulePositions[0] = modulePositionFL;
        modulePositions[1] = modulePositionFR;
        modulePositions[2] = modulePositionRL;
        modulePositions[3] = modulePositionRR;

        odometer.update(getRotation2d(), modulePositions);

        // Update SmartDashboard
        SmartDashboard.putNumber("Robot Heading", getHeading());
        SmartDashboard.putString("Robot Location", getPose().getTranslation().toString());

        // Update Shuffleboard
        frontLeft.setShuffleboardBrain();
        frontRight.setShuffleboardBrain();
        rearLeft.setShuffleboardBrain();
        rearRight.setShuffleboardBrain();
    }

    public void stopModules() {
        frontLeft.stop();
        frontRight.stop();
        rearLeft.stop();
        rearRight.stop();
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SmartDashboard.putString("05: Front Left Desired State", desiredStates[0].toString());
        SmartDashboard.putString("05: Front Right Desired State", desiredStates[1].toString());
        SmartDashboard.putString("05: Rear Left Desired State", desiredStates[2].toString());
        SmartDashboard.putString("05: Rear Right Desired State", desiredStates[3].toString());

        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, SwerveConstants.kPhysicalMaxSpeedMetersPerSecond);

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
        SmartDashboard.putString("07: xSpeed", "" + xSpeed);
        SmartDashboard.putString("07: ySpeed", "" + ySpeed);
        SmartDashboard.putString("07: turningSpeed", "" + turningSpeed);
        SmartDashboard.putString("07: fieldOriented", "" + fieldOriented);
        // Construct desired chassis speeds
        ChassisSpeeds chassisSpeeds;

        if (fieldOriented) {
            // Relative to field
            chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                    xSpeed, ySpeed, turningSpeed, getRotation2d());
        } else {
            // Relative to robot
            chassisSpeeds = new ChassisSpeeds(-xSpeed, ySpeed, -turningSpeed);
        }

        SmartDashboard.putString("06: Chassis Speeeds", chassisSpeeds.toString());
        // Convert chassis speeds to individual module states
        SwerveModuleState[] moduleStates = SwerveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

        // Output each module states to wheels
        setModuleStates(moduleStates);
    }

    // to convert (x,y) to angle relative to y-axis, use arctan(y/x)
    // preferably make a method to convert rotation speeds to angle
    // create new method taking in angle as a parameter instead of speeds

    // Drive to align the Robot to a detected line at the given yaw
    public void driveAlign(double targetYaw) {
        // Get the correction yaw needed to align the Robot with the target yaw
        double yaw = BotSensors.gyro.getYaw();
        double correction = targetYaw - yaw;
        if (correction > 180) correction = correction - 360;
        if (correction < -180) correction = correction + 360;
        Logger.info("SwerveDriver -> Gyro -> Target Yaw: " + targetYaw + "; Current Yaw: " + yaw + "; Correction: " + correction);

        // Get the rotation speed to align the Robot with the target gyro yaw
        double zRotation = (correction / 180) * 1.4;
        boolean isCloseEnough = Math.abs(correction) < 10;
        if (!isCloseEnough) {
            if (0 < zRotation && zRotation < 0.25) zRotation = 0.25;
            if (0 > zRotation && zRotation > -0.25) zRotation = -0.25;
        }
        setChassisSpeed(0, 0, zRotation);
    }

}   
