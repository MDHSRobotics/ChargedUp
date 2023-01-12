package frc.robot.devices;

import frc.robot.subsystems.utils.EncoderTranslator;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration; 
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.brains.SwerveDriverBrain;
import frc.robot.subsystems.constants.SwerveConstants;


public class DevSwerveModule {

    private final String m_name;
    private final DevTalonFX m_driveMotor;
    private final DevTalonFX m_turningMotor;

    private final PIDController m_turningPidController;

    private final boolean m_absoluteEncoderReversed;
    private final double m_absoluteEncoderOffsetRad;

    private final CANCoder m_canCoder;
    private final EncoderTranslator m_drivingEncoderTranslate;
    private final EncoderTranslator m_turningEncoderTranslate;

    public DevSwerveModule(String moduleName, DevTalonFX driveTalon, DevTalonFX steerTalon, CANCoder canCoder,
            boolean driveMotorReversed, boolean turningMotorReversed, double absoluteEncoderOffset, boolean absoluteEncoderReversed) {

        m_absoluteEncoderOffsetRad = absoluteEncoderOffset;
        m_absoluteEncoderReversed = absoluteEncoderReversed;

        m_name = moduleName;

        m_driveMotor = driveTalon;
        m_turningMotor = steerTalon;

        m_driveMotor.setInverted(driveMotorReversed);
        m_turningMotor.setInverted(turningMotorReversed);
        m_driveMotor.setNeutralMode(NeutralMode.Brake);

        m_driveMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 15, 15, 20));   
        m_turningMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 15, 15, 20));   

        m_driveMotor.setNeutralMode(NeutralMode.Brake);
        m_turningMotor.setNeutralMode(NeutralMode.Brake);

        m_driveMotor.configOpenloopRamp(SwerveConstants.kDriveRampTime);

        m_turningPidController = new PIDController(SwerveConstants.kPTurning, 0, 0);
        m_turningPidController.enableContinuousInput(-Math.PI, Math.PI);

        m_canCoder = canCoder;
        m_canCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180);
        m_canCoder.setPositionToAbsolute();

        m_drivingEncoderTranslate = new EncoderTranslator("TalonFX",
                                                   SwerveConstants.kWheelDiameterMeters,
                                                   SwerveConstants.kGearRatioDriving);

        m_turningEncoderTranslate = new EncoderTranslator("TalonFX",
                                                   SwerveConstants.kWheelDiameterMeters,
                                                   SwerveConstants.kGearRatioTurning);


        resetEncoders();

        // Group the contents of the module together in SmartDashboard
        SendableRegistry.setName(m_driveMotor, "Module " + m_name, "Drive Motor");
        SendableRegistry.setName(m_turningMotor, "Module " + m_name, "Turning Motor");
    }

    public double getDrivePositionMeters() {
        double ticks = m_driveMotor.getSelectedSensorPosition();
        double positionInMeters = m_drivingEncoderTranslate.ticks_to_distance(ticks);
        return positionInMeters;
    }

    public double getTurningPositionRadians() {
        double turnTicks = m_turningMotor.getSelectedSensorPosition();
        double turnRadians = m_turningEncoderTranslate.ticks_to_radians(turnTicks);
        return turnRadians;
    }

    public double getDriveVelocityMPS() {
        double driveVelocityTPHMS = m_driveMotor.getSelectedSensorVelocity();
        double driveVelocityMPS = m_drivingEncoderTranslate.ticksPerDecisecond_to_velocity(driveVelocityTPHMS);
        return driveVelocityMPS;
    }

    public double getTurningVelocityRadiansPS() {
        double turningVelocityTPHMS = m_turningMotor.getSelectedSensorVelocity();
        double turningVelocityRPS = m_turningEncoderTranslate.ticksPerDecisecond_to_RadiansPerSecond(turningVelocityTPHMS);
        return turningVelocityRPS;
    }

    public double getAbsoluteEncoderRadians() {
        // Get the angle in degrees of the current position recorded by the absolute encoder
        // Note that this will be -180 to +180, as configured in the constructor above
        double absEncoderPositionDegrees = m_canCoder.getAbsolutePosition();
       
        SmartDashboard.putNumber("Absolute Encoder Raw Angle (Degrees): " + m_name, absEncoderPositionDegrees);
        

        double absEncoderPositionRad = Units.degreesToRadians(absEncoderPositionDegrees);

        SmartDashboard.putNumber("Absolute Encoder Raw Angle (Radians): " + m_name, absEncoderPositionRad);


        // Apply the offset of the absolute encoder
        absEncoderPositionRad -= m_absoluteEncoderOffsetRad;

        SmartDashboard.putNumber("Absolute Encoder Angle Without Offset (Radians): " + m_name, absEncoderPositionRad);
        
        // Negate the angle if needed
        return absEncoderPositionRad * (m_absoluteEncoderReversed ? -1.0 : 1.0);
    }

    // Get array of encoder readings:
    // [0] - drive motor (ticks)
    // [1] - drive motor position (meters)
    // [2] - turning motor (ticks)
    // [3] - turning motor angle (degrees)
    public double[] getEncoderReadings() {
        double[] readings = new double[4];

        readings[0] = m_driveMotor.getSelectedSensorPosition();
        readings[1] = getDrivePositionMeters();
        readings[2] = m_turningMotor.getSelectedSensorPosition();
        readings[3] = getTurningPositionRadians() / Math.PI * 180.;

        return readings;
    }

    public void resetEncoders() {
        // Set drive motor encoder to Zero
        m_driveMotor.setSelectedSensorPosition(0.0);

        // Get the initial angle of the absolute encoder in radians
        double initialAbsoluteEncoderPositionRad = getAbsoluteEncoderRadians();

        // Convert angle to raw units (ticks)
        double initialAbsoluteEncoderPositionTicks = m_turningEncoderTranslate.radians_to_ticks(initialAbsoluteEncoderPositionRad);
        
        // Set the current position of the turning motor based on absolute encoder 
        m_turningMotor.setSelectedSensorPosition(initialAbsoluteEncoderPositionTicks);

        // Report absolute encoder info to SmartDashboard
        SmartDashboard.putNumber("Absolute Encoder Angle (Radians): " + m_name, initialAbsoluteEncoderPositionRad);
        SmartDashboard.putNumber("Absolute Encoder Angle (Ticks): " + m_name, initialAbsoluteEncoderPositionTicks);
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocityMPS(), new Rotation2d(getTurningPositionRadians()));
    }

    public void setDesiredState(SwerveModuleState state) {
        SmartDashboard.putString("04: Desaturated Desired State: " + m_name, state.toString());

        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();

            SmartDashboard.putString("03: Swerve State: " + m_name, "STOPPED");
            SmartDashboard.putString("03: Swerve Power: " + m_name, "STOPPED");

            // SmartDashboard.delete("02: Swerve Optimized State: " + m_name);
            // SmartDashboard.delete("01: Swerve Power: " + m_name);

            return;
        } else {
            // SmartDashboard.delete("03: Swerve State: " + m_name);
            // SmartDashboard.delete("03: Swerve Power: " + m_name);
        }

        state = SwerveModuleState.optimize(state, getState().angle);
        SmartDashboard.putString("02: Swerve Optimized State: " + m_name, state.toString());

        double drivePower = state.speedMetersPerSecond;
        double turningPower = m_turningPidController.calculate(getTurningPositionRadians(), state.angle.getRadians());

        SmartDashboard.putString("01: Swerve Power: " + m_name, String.format("Drive = %.2f; Turning = %.2f", drivePower, turningPower));

        SwerveDriverBrain.setModuleDrivePower(m_name, drivePower);
        SwerveDriverBrain.setModuleTurningPower(m_name, turningPower);

        m_driveMotor.set(drivePower);
        m_turningMotor.set(turningPower);
    }

    public void stop() {
        m_driveMotor.set(0);
        m_turningMotor.set(0);

        SwerveDriverBrain.setModuleDrivePower(m_name, 0.);
        SwerveDriverBrain.setModuleTurningPower(m_name, 0.);
    }

    public void setShuffleboardBrain() {
        SwerveDriverBrain.setModuleEncoderReadings(m_name, getEncoderReadings());
    }

}
